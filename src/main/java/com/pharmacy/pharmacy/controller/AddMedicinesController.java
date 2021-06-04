package com.pharmacy.pharmacy.controller;

import com.pharmacy.pharmacy.entity.*;
import com.pharmacy.pharmacy.services.MedicinesService;
import com.pharmacy.pharmacy.services.PharmacyWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

    Medicines.MedicinesPK id = new Medicines.MedicinesPK();
    id.setName("супрастин");
    id.setDose("2мл");
    id.setForm(DosageForm.РОЗЧИН_ДЛЯ_ВНУТРІШНЬОГО_ЗАСТОСУВАННЯ);
    m1.setId(id);
    m1.setSignature("внутрішньом'язево");
    List<GroupOfMedicines> groups = new ArrayList<>();
    GroupOfMedicines g1 = new GroupOfMedicines();
    g1.setName(GroupName.АНТИГІСТАМІННІ);
    groups.add(g1);
    m1.setGroups(groups);
    List<Symptoms> symptoms = new ArrayList<>();
    Symptoms s1 = new Symptoms();
    s1.setName(SymptomsName.АЛЕРГІЯ);
    symptoms.add(s1);
    Symptoms s2 = new Symptoms();
    s2.setName(SymptomsName.СВЕРБІЖ);
    symptoms.add(s2);
    m1.setSymptoms(symptoms);
    medicinesService.addMedicinesToList(m1);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
