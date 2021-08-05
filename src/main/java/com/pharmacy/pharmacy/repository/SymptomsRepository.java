package com.pharmacy.pharmacy.repository;

import com.pharmacy.pharmacy.entity.Symptoms;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SymptomsRepository extends CrudRepository<Symptoms, Integer> {
  List<Symptoms> findAll();
}
