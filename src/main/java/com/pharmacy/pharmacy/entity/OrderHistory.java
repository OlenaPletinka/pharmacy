package com.pharmacy.pharmacy.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class OrderHistory {
  @Id
  @GeneratedValue
  private Integer id;

  @OneToMany()
  private List<ClientOrderItems> items = new ArrayList<>();

  @Column
  private Double sum;

  @Column
  private LocalDate date;
}
