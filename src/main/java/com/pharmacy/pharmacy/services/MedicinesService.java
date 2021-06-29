package com.pharmacy.pharmacy.services;

import com.pharmacy.pharmacy.entity.DosageForm;
import com.pharmacy.pharmacy.entity.GroupName;
import com.pharmacy.pharmacy.entity.Medicines;
import com.pharmacy.pharmacy.entity.SymptomsName;
import com.pharmacy.pharmacy.exception.NoSuchEnumValueException;
import com.pharmacy.pharmacy.exception.NoSuchMedicinesException;
import com.pharmacy.pharmacy.repository.MedicinesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MedicinesService {
  @Autowired
  private MedicinesRepository medicinesRepository;
  @Autowired
  private GroupOfMedicinesService groupOfMedicinesService;
  @Autowired
  private SymptomsService symptomsService;
  @Autowired
  private PharmacyWarehouseService pharmacyWarehouseService;

  public List<Medicines> searchByName(String name){
    List<Medicines> medicines = medicinesRepository.searchById_name(name);
    checkIfEmpty(medicines, "Даний лікарський засіб відсутній у довіднику.");

    return medicines;
  }

  @Transactional
  public Medicines addMedicinesToList(Medicines medicines){
    groupOfMedicinesService.addGroup(medicines.getGroups());
    symptomsService.addSympoms(medicines.getSymptoms());

    return medicinesRepository.save(medicines);
  }

  public List<Medicines> searchBySymptom(String symptom) {
    List<Medicines> medicines = medicinesRepository.searchBySymptoms_name(validateSymptomsName(symptom));
    checkIfEmpty(medicines, String.format("Лікарські засоби для усунення симптому - %s відсутні у довіднику.", symptom));

    return medicines;
  }

  public List<Medicines> searchByGroup(String group) {
    List<Medicines>medicines = medicinesRepository.searchByGroups_name(validateGroupName(group));
    checkIfEmpty(medicines, String.format("В довіднику немає лікарських засобів, що відносяться до групи - %s.", group));

    return medicines;
  }


  public List<Medicines> showAllMedicines() {
    List<Medicines> medicines = medicinesRepository.findByOrderById_nameAsc();
    checkIfEmpty(medicines, "Лікарські засоби у довіднику відсутні.");

    return medicines;


  }

  private void checkIfEmpty(List<Medicines> medicines, String s) {
    if (medicines.isEmpty()) {
      throw new NoSuchMedicinesException(s);
    }
  }

  private SymptomsName validateSymptomsName(String symptom) {
    SymptomsName symptomsName;
    try {
      symptomsName = SymptomsName.valueOf(symptom.toUpperCase());
    } catch (IllegalArgumentException ex){
      throw new NoSuchEnumValueException("Даного симптому нема у довіднику.");
    }
    return symptomsName;
  }

  private GroupName validateGroupName(String group) {
    GroupName groupName;
    try {
      groupName = GroupName.valueOf(group.toUpperCase());
    } catch (IllegalArgumentException ex){
      throw new NoSuchEnumValueException("Даної групи лікарських засобів нема у довіднику.");
    }
    return groupName;
  }

  public Medicines findById(String name, String dose, String form) {
    Medicines.MedicinesPK id = new Medicines.MedicinesPK(name, dose, DosageForm.valueOf(form));
    return medicinesRepository.findById(id);
  }
//  @Transactional
//  public Medicines addMedicinesToPharmacyWarehouse(Medicines medicines){
//    groupOfMedicinesService.addGroup(medicines.getGroups());
//    symptomsService.addSympoms(medicines.getSymptoms());
//    pharmacyWarehouseService.addMedicines(medicines);
//    return medicinesRepository.save(medicines);
//  }
}
