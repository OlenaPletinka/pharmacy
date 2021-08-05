package com.pharmacy.pharmacy.repository;

import com.pharmacy.pharmacy.entity.Medicines;
import com.pharmacy.pharmacy.enums.GroupName;
import com.pharmacy.pharmacy.enums.SymptomsName;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicinesRepository extends CrudRepository<Medicines, Integer> {
  List<Medicines> searchById_name(String name);

  List<Medicines> searchBySymptoms_name(SymptomsName symptom);

  List<Medicines> searchByGroups_name(GroupName groupName);

  List<Medicines> findByOrderById_nameAsc();

  Medicines findById(Medicines.MedicinesPK id);
}
