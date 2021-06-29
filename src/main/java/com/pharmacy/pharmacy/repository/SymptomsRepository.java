package com.pharmacy.pharmacy.repository;

import com.pharmacy.pharmacy.entity.Symptoms;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SymptomsRepository extends CrudRepository<Symptoms, Integer> {
  List<Symptoms> findAll();

}
