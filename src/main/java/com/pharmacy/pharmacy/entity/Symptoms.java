package com.pharmacy.pharmacy.entity;

import com.pharmacy.pharmacy.enums.SymptomsName;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Symptoms {
  @Id
  @Enumerated(EnumType.STRING)
  @OrderBy("name")
  @Column(nullable = false)
  private SymptomsName name;
}
