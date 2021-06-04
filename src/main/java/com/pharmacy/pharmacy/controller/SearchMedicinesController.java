package com.pharmacy.pharmacy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pharmacy.pharmacy.entity.Medicines;
import com.pharmacy.pharmacy.services.MedicinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchMedicinesController {
  @Autowired
  private MedicinesService medicinesService;
  @Autowired
  private ObjectMapper objectMapper;

  @GetMapping(value = "/byName", produces = "application/json")
  public ResponseEntity<String> searchByName(@RequestParam(name = "name") String name) throws JsonProcessingException {
    UserRequestValidator.validateSearchByName(name);

    return ResponseEntity.ok(objectMapper.writeValueAsString(medicinesService.searchByName(name)));
  }

  @GetMapping(value = "/bySymptom", produces = "application/json")
  public ResponseEntity<String> searchBySymptom(@RequestParam(name = "symptom") String symptom) throws JsonProcessingException {
    UserRequestValidator.validateSearchBySymptom(symptom);

    return ResponseEntity.ok(objectMapper.writeValueAsString(medicinesService.searchBySymptom(symptom)));
  }

  @GetMapping(value = "/byGroup", produces = "application/json")
  public ResponseEntity<String> searchByGroup(@RequestParam(name = "group") String group) throws JsonProcessingException {
    UserRequestValidator.validateSearchByGroup(group);

    return ResponseEntity.ok(objectMapper.writeValueAsString(medicinesService.searchByGroup(group)));
  }

  @GetMapping(value = "/all", produces = "application/json")
  public List<Medicines> showAllMedicines(){
    return medicinesService.showAllMedicines();
  }


}
