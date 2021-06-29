package com.pharmacy.pharmacy.services;

import com.pharmacy.pharmacy.entity.GroupOfMedicines;
import com.pharmacy.pharmacy.repository.GroupOfMedicinesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupOfMedicinesService {
  @Autowired
  private GroupOfMedicinesRepository groupOfMedicinesRepository;

  public void addGroup(List<GroupOfMedicines> groupOfMedicines){
    groupOfMedicinesRepository.saveAll(groupOfMedicines);
  }

  public List<GroupOfMedicines> showAllGroupOfMedicines() {
    return groupOfMedicinesRepository.findAll();
  }
}
