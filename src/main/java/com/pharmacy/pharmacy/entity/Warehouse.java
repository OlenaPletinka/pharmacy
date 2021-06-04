package com.pharmacy.pharmacy.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class Warehouse {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Integer medicinesIdOnWarehouse;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumns({
            @JoinColumn(name = "medicines_id_name", referencedColumnName = "name", nullable = false),
            @JoinColumn(name = "medicines_id_dose", referencedColumnName = "dose", nullable = false),
            @JoinColumn(name = "medicines_id_form", referencedColumnName = "form", nullable = false)
  })
  @OrderBy("medicines_id_name")
  private Medicines medicine;

  @Column
  private Integer quantity;

  @Column
  private Double price;
}
