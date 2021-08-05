package com.pharmacy.pharmacy.repository;

import com.pharmacy.pharmacy.entity.GroupOfMedicines;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupOfMedicinesRepository extends CrudRepository<GroupOfMedicines, Integer> {
  List<GroupOfMedicines> findAll();
}
