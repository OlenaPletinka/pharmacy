package com.pharmacy.pharmacy.controller;

import com.pharmacy.pharmacy.entity.PharmacyWarehouse;
import com.pharmacy.pharmacy.services.PharmacyWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PharmacyWareHouseController {
  @Autowired
  private PharmacyWarehouseService pharmacyWarehouseService;

  @PostMapping(path = "/addMedicinesToPharmacyWareHouse", consumes = "application/json", produces = "application/json")
  public ResponseEntity<String> addMedicinesToPharmacyWarehouse(@RequestBody PharmacyWarehouse pharmacyWarehouse){
    UserRequestValidator.validaterequest(pharmacyWarehouse);
    pharmacyWarehouseService.addMedicines(pharmacyWarehouse);
    return new ResponseEntity<>(HttpStatus.OK);
  }

//  @GetMapping(@GetMapping(value = "/byGroup", produces = "application/json"))
}
