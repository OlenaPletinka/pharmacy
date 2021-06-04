package com.pharmacy.pharmacy.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
public class PharmacyWarehouse extends Warehouse{
  @Column
  private Double wholesalePrice;

  @Column
  private Integer norm = 20;

  @Column
  private Integer critical_norm = 10;

}
