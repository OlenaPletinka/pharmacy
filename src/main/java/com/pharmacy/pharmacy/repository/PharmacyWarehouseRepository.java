package com.pharmacy.pharmacy.repository;

import com.pharmacy.pharmacy.entity.Medicines;
import com.pharmacy.pharmacy.entity.PharmacyWarehouse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PharmacyWarehouseRepository extends CrudRepository<PharmacyWarehouse, Integer> {

  PharmacyWarehouse findByMedicine_id(Medicines.MedicinesPK id);

  List<PharmacyWarehouse> findByMedicine_idName(String name);

  List<PharmacyWarehouse> findByOrderByMedicine_idNameAsc();
}

