package com.pharmacy.pharmacy.services;

import com.pharmacy.pharmacy.entity.PharmacyWarehouse;
import com.pharmacy.pharmacy.repository.PharmacyWarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PharmacyWarehouseService {
  @Autowired
  private PharmacyWarehouseRepository pharmacyWarehouseRepository;

  public void addMedicines(PharmacyWarehouse pharmacyWarehouse) {
    PharmacyWarehouse byMedicinesId = pharmacyWarehouseRepository.findByMedicine_id(pharmacyWarehouse.getMedicine().getId());
    if (Objects.isNull(byMedicinesId)){
      pharmacyWarehouseRepository.save(pharmacyWarehouse);
    }else {
      Integer newQuantity = byMedicinesId.getQuantity() + pharmacyWarehouse.getQuantity();
      pharmacyWarehouse.setQuantity(newQuantity);
      pharmacyWarehouseRepository.save(pharmacyWarehouse);
    }
  }


}
