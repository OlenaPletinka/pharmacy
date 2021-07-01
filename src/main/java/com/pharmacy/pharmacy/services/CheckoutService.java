package com.pharmacy.pharmacy.services;

import com.pharmacy.pharmacy.entity.ClientOrder;
import com.pharmacy.pharmacy.entity.ClientOrderItems;
import com.pharmacy.pharmacy.entity.Medicines;
import com.pharmacy.pharmacy.entity.OrderHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CheckoutService {
  @Autowired
  private PharmacyWarehouseService pharmacyWarehouseService;
  @Autowired
  private ClientOrderService clientOrderService;
  @Autowired
  private OrderHistoryService orderHistoryService;

  @Transactional
  public void dispatch() {
    List<ClientOrder> orderList = clientOrderService.showAllItems();
    List<ClientOrderItems> items = orderList.stream().map(ClientOrder::getItems).collect(Collectors.toList());

    fillInOrderHistory(items);

    reduceMedicinesQuantity(items);

    clientOrderService.cleanupClientOrder();
  }

  public Double calculateTotalSum(List<ClientOrder> orderList) {
    Optional<Double> optional = orderList.stream().map(clientOrder -> clientOrder.getItems().getCost()).reduce((aDouble, aDouble2) -> aDouble + aDouble2);

    return optional.orElseThrow(IllegalStateException::new);
  }

  private void reduceMedicinesQuantity(List<ClientOrderItems> items) {
    for (ClientOrderItems item : items) {
      Medicines medicine = item.getMedicine();
      Integer quantity = item.getQuantity();
      pharmacyWarehouseService.reduceMedicinesQuantity(medicine, quantity);
    }
  }

  private void fillInOrderHistory(List<ClientOrderItems> items) {
    Optional<Double> optional = items.stream().map(clientOrderItems -> clientOrderItems.getCost()).reduce((aDouble, aDouble2) -> aDouble + aDouble2);
    Double sum = optional.orElseThrow(IllegalStateException::new);

    orderHistoryService.save(createOrderHistory(items, sum));
  }

  private OrderHistory createOrderHistory(List<ClientOrderItems> items, Double sum) {
    OrderHistory orderHistory = new OrderHistory();
    orderHistory.setItems(items);
    orderHistory.setSum(sum);
    orderHistory.setDate(LocalDate.now());

    return orderHistory;
  }
}
