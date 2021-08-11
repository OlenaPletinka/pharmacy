package com.pharmacy.pharmacy.services;

import com.pharmacy.pharmacy.entity.ClientOrderItems;
import com.pharmacy.pharmacy.entity.Medicines;
import com.pharmacy.pharmacy.entity.PharmacyWarehouse;
import com.pharmacy.pharmacy.exception.NotEnoughMedicinesOnPharmacyWareHouseException;
import com.pharmacy.pharmacy.repository.ClientOrderItemsRepository;
import com.pharmacy.pharmacy.repository.ClientOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientOrderItemsService {
  @Autowired
  private ClientOrderRepository clientOrderRepository;
  @Autowired
  private MedicinesService medicinesService;
  @Autowired
  private PharmacyWarehouseService pharmacyWarehouseService;
  @Autowired
  private ClientOrderItemsRepository clientOrderItemsRepository;

  public ClientOrderItems addItems(String name, String dose, String form, Integer quantity) {
    ClientOrderItems orderItems = createOrderItems(name, dose, form, quantity);

    ClientOrderItems savedItems = clientOrderItemsRepository.save(orderItems);

    return savedItems;
  }

  private ClientOrderItems createOrderItems(String name, String dose, String form, Integer quantity) {
    Medicines byId = medicinesService.findById(name, dose, form);
    PharmacyWarehouse pharmacyWarehouse = pharmacyWarehouseService.findByMedicines_id(byId);

    if (pharmacyWarehouse.getQuantity() < quantity) {
      throw new NotEnoughMedicinesOnPharmacyWareHouseException(String.format("На складі недостатньо ліків, максимальна кількість для замовлення - %d.", quantity));
    }

    ClientOrderItems orderItems = new ClientOrderItems();
    orderItems.setMedicine(byId);
    Double pricePerUnit = pharmacyWarehouse.getPrice();
    orderItems.setPricePerUnit(pricePerUnit);
    orderItems.setCost(pricePerUnit * quantity);
    orderItems.setQuantity(quantity);

    return orderItems;
  }
}
