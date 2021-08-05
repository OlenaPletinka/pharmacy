package com.pharmacy.pharmacy.services;

import com.pharmacy.pharmacy.entity.ClientOrder;
import com.pharmacy.pharmacy.entity.ClientOrderItems;
import com.pharmacy.pharmacy.exception.BasketIsEmptyException;
import com.pharmacy.pharmacy.repository.ClientOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientOrderService {
  @Autowired
  private ClientOrderItemsService clientOrderItemsService;
  @Autowired
  private ClientOrderRepository clientOrderRepository;

  public void createOrder(String name, String dose, String form, Integer quantity) {
    ClientOrder clientOrder = new ClientOrder();
    ClientOrderItems orderItems = clientOrderItemsService.addItems(name, dose, form, quantity);
    clientOrder.setItems(orderItems);

    clientOrderRepository.save(clientOrder);
  }

  public List<ClientOrder> showAllItems() {
    List<ClientOrder> clientOrders = clientOrderRepository.findAll();
    if (clientOrders.isEmpty()) {
      throw new BasketIsEmptyException("Ваш кошик порожній! Продовжіть, будь ласка, покупки.");
    }
    return clientOrders;
  }

  public void cleanupClientOrder() {
    clientOrderRepository.deleteAll();
  }
}
