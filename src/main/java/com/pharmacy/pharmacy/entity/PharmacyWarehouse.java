package com.pharmacy.pharmacy.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
public class PharmacyWarehouse extends Warehouse {
  @Column
  private Double wholesalePrice;

  @Column(columnDefinition = "integer default 20")
  private Integer norm;

  @Column(columnDefinition = "integer default 10")
  private Integer critical_norm;
}
