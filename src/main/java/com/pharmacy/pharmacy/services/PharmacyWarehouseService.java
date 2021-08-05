package com.pharmacy.pharmacy.services;

import com.pharmacy.pharmacy.entity.Medicines;
import com.pharmacy.pharmacy.entity.PharmacyWarehouse;
import com.pharmacy.pharmacy.repository.PharmacyWarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PharmacyWarehouseService {
  @Autowired
  private PharmacyWarehouseRepository pharmacyWarehouseRepository;

  public List<PharmacyWarehouse> searchByName(String name) {

    return pharmacyWarehouseRepository.findByMedicine_idName(name);
  }

  public List<PharmacyWarehouse> showAllMedicinesOnWarehouse() {

    return pharmacyWarehouseRepository.findByOrderByMedicine_idNameAsc();
  }

  public void reduceMedicinesQuantity(Medicines medicine, Integer quantity) {
    PharmacyWarehouse byMedicine_id = pharmacyWarehouseRepository.findByMedicine_id(medicine.getId());
    Integer updatedQuantity = byMedicine_id.getQuantity() - quantity;
    byMedicine_id.setQuantity(updatedQuantity);
  }

  public PharmacyWarehouse findByMedicines_id(Medicines medicines) {
    return pharmacyWarehouseRepository.findByMedicine_id(medicines.getId());
  }
}
