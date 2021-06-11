package com.pharmacy.pharmacy.entity;

public enum DosageForm {
  ТАБЛЕТКИ("таблетки"),
  КАПСУЛИ("капсули"),
  ПОРОШОК("порошок"),
  РОЗЧИН_ДЛЯ_ВНУТРІШНЬОГО_ЗАСТОСУВАННЯ("розчин для внутрішнього застосування"),
  РОЗЧИН_ДЛЯ_ЗОВНІШНЬОГО_ЗАСТОСУВАННЯ("розчин для зовнішнього застосування"),
  СУСПЕНЗІЯ("суспензія"),
  МАЗЬ("мазь"),
  ГЕЛЬ("гель"),
  ГРАНУЛИ("гранули"),
  АЕРОЗОЛЬ("аерозоль");

  public final String name;

   DosageForm(String name){
    this.name = name;
  }
}
