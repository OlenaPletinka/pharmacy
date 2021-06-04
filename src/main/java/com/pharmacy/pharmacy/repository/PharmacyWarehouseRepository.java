package com.pharmacy.pharmacy.repository;

import com.pharmacy.pharmacy.entity.Medicines;
import com.pharmacy.pharmacy.entity.PharmacyWarehouse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacyWarehouseRepository extends CrudRepository<PharmacyWarehouse, Integer> {

  PharmacyWarehouse findByMedicine_id(Medicines.MedicinesPK id);
}

