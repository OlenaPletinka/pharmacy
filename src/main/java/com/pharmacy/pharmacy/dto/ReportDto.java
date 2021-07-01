package com.pharmacy.pharmacy.dto;

import com.pharmacy.pharmacy.entity.Medicines;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReportDto {
  public Medicines medicines;

  public Integer quantity;

  public Double totalPrice;

  public ReportDto(Medicines medicines, Integer quantity, Double totalPrice) {
    this.medicines = medicines;
    this.quantity = quantity;
    this.totalPrice = totalPrice;
  }
}
