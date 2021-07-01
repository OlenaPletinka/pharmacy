package com.pharmacy.pharmacy.controller;

import com.pharmacy.pharmacy.entity.PharmacyWarehouse;
import com.pharmacy.pharmacy.services.ClientOrderService;
import com.pharmacy.pharmacy.services.PharmacyWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PharmacyWareHouseController {
  @Autowired
  private PharmacyWarehouseService pharmacyWarehouseService;
  @Autowired
  private ClientOrderService clientOrderService;

  @PostMapping(path = "/addMedicinesToPharmacyWareHouse", consumes = "application/json", produces = "application/json")
  public ResponseEntity<String> addMedicinesToPharmacyWarehouse(@RequestBody PharmacyWarehouse pharmacyWarehouse){
    UserRequestValidator.validaterequest(pharmacyWarehouse);
    pharmacyWarehouseService.addMedicines(pharmacyWarehouse);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @RequestMapping(path="/showMedicinesFromPharmacyWareHouse", method = RequestMethod.GET)
  public String showMedicinesFromPharmacyWareHouse(@RequestParam(name = "name") String name, Model model) {
    UserRequestValidator.validateSearchByName(name);

    List<PharmacyWarehouse> medicines = pharmacyWarehouseService.searchByName(name);
    model.addAttribute("medicines", medicines);

    return "add_order_items";
  }

  @RequestMapping(path = "/addOrderItems", method = RequestMethod.GET)
  public String buyMedicines(@RequestParam("name") String name, @RequestParam("dose") String dose, @RequestParam("form") String form, @RequestParam("quantity") String quantity, Model model) {
    Integer number = Integer.parseInt(quantity);
    if (number>0){
    clientOrderService.createOrder(name, dose, form, number);
    }
    List<PharmacyWarehouse> medicines = pharmacyWarehouseService.showAllMedicinesOnWarehouse();
    model.addAttribute("medicines", medicines);

    return "select_medicines";
  }
}
