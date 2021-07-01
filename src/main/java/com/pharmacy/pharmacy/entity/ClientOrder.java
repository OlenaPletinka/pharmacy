package com.pharmacy.pharmacy.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ClientOrder {
  @Id
  @GeneratedValue
  private Integer id;

  @OneToOne()
  @JoinColumn(name = "client_order_item_id", referencedColumnName = "id")
  private ClientOrderItems items;
}
