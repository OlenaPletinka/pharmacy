package com.pharmacy.pharmacy.services;

import com.pharmacy.pharmacy.entity.Symptoms;
import com.pharmacy.pharmacy.repository.SymptomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SymptomsService {
  @Autowired
  private SymptomsRepository symptomsRepository;

  public void addSympoms(List<Symptoms> symptoms) {
    symptomsRepository.saveAll(symptoms);
  }

  public List<Symptoms> showAllSymptoms() {
    return symptomsRepository.findAll();
  }
}
