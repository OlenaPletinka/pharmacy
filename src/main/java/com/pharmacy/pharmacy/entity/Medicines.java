package com.pharmacy.pharmacy.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Medicines {
 @EmbeddedId
 @OrderBy("name")
 private MedicinesPK id;

 @Column(nullable = false)
 private Integer quantity;

 @Column
 private String signature;


 @ManyToMany(fetch = FetchType.LAZY)
 @CollectionTable(name = "medicines_group")
 @JoinColumns({
           @JoinColumn(name = "medicines_id_name"),
           @JoinColumn(name = "medicines_id_dose"),
           @JoinColumn(name = "medicines_id_form")
 })
 @Column(nullable = false, unique = false)
 private List<GroupOfMedicines> groups = new ArrayList<>();

 @ManyToMany(fetch = FetchType.LAZY)
 @CollectionTable(name = "medicines_symptoms")
 @JoinColumns({
           @JoinColumn(name = "medicines_id_name"),
           @JoinColumn(name = "medicines_id_dose"),
           @JoinColumn(name = "medicines_id_form")
 })
 @Column(nullable = false, unique = false)
 private List<Symptoms> symptoms = new ArrayList<>();

 @Embeddable
 @Data
 public static class MedicinesPK implements Serializable {
  private static final long serialVersionUID = 1L;

  private String name;

  private String dose;

  @Enumerated(EnumType.STRING)
  private DosageForm form;

 }
}
