package com.pharmacy.pharmacy.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class ClientOrder {
  @Id
  @GeneratedValue
  private Integer id;

  @OneToMany(mappedBy = "clientOrder")
  private List<ClientOrderItems> items = new ArrayList<>();

  @Column
  private Double sum;

  @Column
  private LocalDateTime time;
}
