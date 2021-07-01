package com.pharmacy.pharmacy.services;

import com.pharmacy.pharmacy.entity.Medicines;
import com.pharmacy.pharmacy.entity.PharmacyWarehouse;
import com.pharmacy.pharmacy.repository.PharmacyWarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PharmacyWarehouseService {
  @Autowired
  private PharmacyWarehouseRepository pharmacyWarehouseRepository;

  public void addMedicines(PharmacyWarehouse pharmacyWarehouse) {
    PharmacyWarehouse byMedicinesId = pharmacyWarehouseRepository.findByMedicine_id(pharmacyWarehouse.getMedicine().getId());
    if (Objects.isNull(byMedicinesId)) {
      pharmacyWarehouseRepository.save(pharmacyWarehouse);
    } else {
      Integer newQuantity = byMedicinesId.getQuantity() + pharmacyWarehouse.getQuantity();
      pharmacyWarehouse.setQuantity(newQuantity);
      pharmacyWarehouseRepository.save(pharmacyWarehouse);
    }
  }


  public List<PharmacyWarehouse> searchByName(String name) {

    return pharmacyWarehouseRepository.findByMedicine_idName(name);
  }

  public List<PharmacyWarehouse> showAllMedicinesOnWarehouse() {

    return pharmacyWarehouseRepository.findByOrderByMedicine_idNameAsc();
  }

  public Double searchPriceByMedicines(Medicines byId) {

    return findByMedicines_id(byId).getPrice();
  }

  public void reduceMedicinesQuantity(Medicines medicine, Integer quantity) {
    PharmacyWarehouse byMedicine_id = pharmacyWarehouseRepository.findByMedicine_id(medicine.getId());
    Integer updatedQuantity = byMedicine_id.getQuantity() - quantity;
    byMedicine_id.setQuantity(updatedQuantity);
  }

  public PharmacyWarehouse findByMedicines_id(Medicines byId) {
    return pharmacyWarehouseRepository.findByMedicine_id(byId.getId());
  }
}
