package com.pharmacy.pharmacy.controller;

import com.pharmacy.pharmacy.entity.Medicines;
import com.pharmacy.pharmacy.services.MedicinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/add")
public class AddMedicinesController {

  @Autowired
  private MedicinesService medicinesService;

  @PostMapping(path = "/medicines", consumes = "application/json", produces = "application/json")
  public ResponseEntity<String> addMedicines(@RequestBody Medicines medicines) {

    medicinesService.addMedicinesToList(medicines);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping(path = "/save", consumes = "application/json", produces = "application/json")
  public ResponseEntity<String> save(@RequestBody String medicines) {
    Medicines m1 = new Medicines();
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
