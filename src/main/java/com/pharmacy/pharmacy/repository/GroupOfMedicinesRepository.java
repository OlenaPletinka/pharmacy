package com.pharmacy.pharmacy.repository;

import com.pharmacy.pharmacy.entity.GroupOfMedicines;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupOfMedicinesRepository extends CrudRepository<GroupOfMedicines, Integer> {
  List<GroupOfMedicines> findAll();
}
