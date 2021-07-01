package com.pharmacy.pharmacy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pharmacy.pharmacy.entity.Medicines;
import com.pharmacy.pharmacy.services.MedicinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/search")
public class SearchMedicinesController {
  @Autowired
  private MedicinesService medicinesService;
  @Autowired
  private ObjectMapper objectMapper;

  @RequestMapping(value = "/byName", method = RequestMethod.GET)
  public String searchByName(@RequestParam(name = "name") String name, Model model) {
    UserRequestValidator.validateSearchByName(name);

    List<Medicines> medicines = medicinesService.searchByName(name);
    model.addAttribute("medicines", medicines);

    return "find_by";
  }

  @RequestMapping(value = "/bySymptom", method = GET)
  public String searchBySymptom(@RequestParam(name = "symptom") String symptom, Model model) {
    UserRequestValidator.validateSearchBySymptom(symptom);

    List<Medicines> medicines = medicinesService.searchBySymptom(symptom);
    model.addAttribute("medicines", medicines);

    return "find_by";
  }

  @RequestMapping(value = "/byGroup", method = GET)
  public String searchByGroup(@RequestParam(name = "group") String group, Model model) throws JsonProcessingException {
    UserRequestValidator.validateSearchByGroup(group);

    List<Medicines> medicines = medicinesService.searchByGroup(group);
    model.addAttribute("medicines", medicines);

    return "find_by";
  }

  @RequestMapping(value = "/all", method = RequestMethod.GET)
  public String showAllMedicines(Model model) {
    List<Medicines> medicines = medicinesService.showAllMedicines();
    model.addAttribute("medicines", medicines);

    return "all_medicines_handbook";
  }

  @RequestMapping(value = "/description", method = GET)
  public String showDescription(@RequestParam("name") String name, @RequestParam("dose") String dose, @RequestParam("form") String form, Model model) {
    Medicines medicine = medicinesService.findById(name, dose, form);
    model.addAttribute("medicine", medicine);

    return "description";
  }
}
