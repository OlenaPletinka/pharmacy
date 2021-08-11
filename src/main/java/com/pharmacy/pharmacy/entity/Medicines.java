package com.pharmacy.pharmacy.entity;

import com.pharmacy.pharmacy.enums.DosageForm;
import lombok.Data;
import lombok.NoArgsConstructor;

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
  @NoArgsConstructor
  public static class MedicinesPK implements Serializable {
    private String name;

    private String dose;

    @Enumerated(EnumType.STRING)
    private DosageForm form;

    public MedicinesPK(String name, String dose, DosageForm form) {
      this.name = name;
      this.dose = dose;
      this.form = form;
    }
  }
}
