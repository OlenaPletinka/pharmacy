package com.pharmacy.pharmacy.controller;

import com.pharmacy.pharmacy.entity.GroupOfMedicines;
import com.pharmacy.pharmacy.entity.Medicines;
import com.pharmacy.pharmacy.entity.Symptoms;
import com.pharmacy.pharmacy.services.GroupOfMedicinesService;
import com.pharmacy.pharmacy.services.MedicinesService;
import com.pharmacy.pharmacy.services.PharmacyWarehouseService;
import com.pharmacy.pharmacy.services.SymptomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class WellcomeController {
  @Autowired
  private MedicinesService medicinesService;
  @Autowired
  private GroupOfMedicinesService groupOfMedicinesService;
  @Autowired
  private SymptomsService symptomsService;
  @Autowired
  private PharmacyWarehouseService pharmacyWarehouseService;

  @RequestMapping(value = "/", method = GET)
  public String startPage() {
    return "welcome";
  }

  @RequestMapping(value = "selectScenario", method = GET)
  public String profile(@RequestParam("value") String value, Model model) {
    String result;

    if (value.equals("ДОВІДНИК")) {
      List<Medicines> medicines = medicinesService.showAllMedicines();
      List<String> medicinesName = medicines.stream().map(medicines1 -> medicines1.getId().getName()).distinct().collect(Collectors.toList());
      List<GroupOfMedicines> groups = groupOfMedicinesService.showAllGroupOfMedicines();
      List<Symptoms> symptoms = symptomsService.showAllSymptoms();

      model.addAttribute("medicines", medicinesName);
      model.addAttribute("groups", groups);
      model.addAttribute("symptoms", symptoms);

      result = "handbook";
    } else if (value.equals("ПРОДАЖ ЛІКАРСЬКИХ ЗАСОБІВ")) {
      List<String> medicines = pharmacyWarehouseService.showAllMedicinesOnWarehouse().stream().map(pharmacyWarehouse -> pharmacyWarehouse.getMedicine().getId().getName()).distinct().collect(Collectors.toList());
      model.addAttribute("medicines", medicines);

      result = "select_medicines";
    } else {
      LocalDate date = LocalDate.now();
      model.addAttribute("toDate", date);

      result = "generate_Report";
    }
    return result;
  }
}
