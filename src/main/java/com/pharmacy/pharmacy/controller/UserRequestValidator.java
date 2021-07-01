package com.pharmacy.pharmacy.controller;

import com.pharmacy.pharmacy.entity.PharmacyWarehouse;
import com.pharmacy.pharmacy.exception.EmptyRequestException;
import com.pharmacy.pharmacy.exception.InvalidDataForAddindToWharehouseException;
import com.pharmacy.pharmacy.exception.NotValidDateForReportException;
import org.apache.logging.log4j.util.Strings;

import java.time.LocalDate;
import java.util.Objects;

public class UserRequestValidator {
  public static void validateSearchByName(String name) {
    validate(name, "Будь ласка, введіть назву лікарського засобу для пошуку.");
  }

  public static void validateSearchBySymptom(String symptom) {
    validate(symptom, "Будь ласка, введіть симптом для пошуку лікарського засобу.");
  }

  public static void validateSearchByGroup(String group) {
    validate(group, "Будь ласка, введіть групу для пошуку лікарського засобу.");
  }

  public static void validaterequest(PharmacyWarehouse pharmacyWarehouse) {
    if (!Objects.nonNull(pharmacyWarehouse)) {
      throw new InvalidDataForAddindToWharehouseException("Введіть дані для додавання лікарських засобів на склад.");
    }else if (Objects.isNull(pharmacyWarehouse.getMedicine())) {
      throw new InvalidDataForAddindToWharehouseException("Введіть назву лікарського засобу, щоб додати його на склад аптеки.");
    }else if(Objects.isNull(pharmacyWarehouse.getPrice())){
      throw new InvalidDataForAddindToWharehouseException("Введіть ціну лікарського засобу, щоб додати його на склад аптеки.");
    }else if(Objects.isNull(pharmacyWarehouse.getWholesalePrice())){
      throw new InvalidDataForAddindToWharehouseException("Введіть оптову ціну лікарського засобу, щоб додати його на склад аптеки.");
    }else if(Objects.isNull(pharmacyWarehouse.getQuantity())||pharmacyWarehouse.getQuantity().equals(0)){
      throw new InvalidDataForAddindToWharehouseException("Введіть кількість лікарських засобів, щоб додати їх на склад аптеки.");
    }
  }

  private static void validate(String name, String s) {
    if (Strings.isBlank(name)) {
      throw new EmptyRequestException(s);
    }
  }

  public static void validateDate(LocalDate start, LocalDate to) {
    if (start.isAfter(to)){
      throw new NotValidDateForReportException("Введіть, будь ласка, коректні дати. Дата від якої буде генеруватись звіт, повинна бути перед датою, до якої буде генеруватись звіт.");
    }
  }
}
