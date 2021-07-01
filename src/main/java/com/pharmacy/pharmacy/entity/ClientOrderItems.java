package com.pharmacy.pharmacy.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ClientOrderItems {

  @Id
  @GeneratedValue
  private Integer id;

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
  private Double pricePerUnit;

  @Column
  private Double cost;
}
