package com.pharmacy.pharmacy.controller;

import com.pharmacy.pharmacy.exception.EmptyRequestException;
import com.pharmacy.pharmacy.exception.NotValidDateForReportException;
import org.apache.logging.log4j.util.Strings;

import java.time.LocalDate;

public class UserRequestValidator {
    public static void validateName(String name) {
        validate(name, "Будь ласка, введіть назву лікарського засобу для пошуку.");
    }

    public static void validateSymptom(String symptom) {
        validate(symptom, "Будь ласка, введіть симптом для пошуку лікарського засобу.");
    }

    public static void validateGroup(String group) {
        validate(group, "Будь ласка, введіть групу для пошуку лікарського засобу.");
    }

    public static void validateDate(LocalDate start, LocalDate to) {
        if (start.isAfter(to)) {
            throw new NotValidDateForReportException("Введіть, будь ласка, коректні дати. Дата від якої буде генеруватись звіт, повинна бути перед датою, до якої буде генеруватись звіт.");
        }
    }

    private static void validate(String name, String message) {
        if (Strings.isBlank(name)) {
            throw new EmptyRequestException(message);
        }
    }
}
