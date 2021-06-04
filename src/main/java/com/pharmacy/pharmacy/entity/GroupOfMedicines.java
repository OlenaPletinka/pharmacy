package com.pharmacy.pharmacy.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class GroupOfMedicines {
  @Id
  @Enumerated(EnumType.STRING)
  @OrderBy("name")
  @Column(nullable = false)
  private GroupName name;
}
