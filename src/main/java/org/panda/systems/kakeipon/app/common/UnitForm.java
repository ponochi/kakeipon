package org.panda.systems.kakeipon.app.common;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Table(name = "tbl_unit")
@Data
public class UnitForm implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
  @SequenceGenerator(name = "tbl_unit_seq", allocationSize = 1)
  @Column(name = "unit_id")
  private Long unitId;

  @Column(name = "unit_name")
  private String unitName;

  // Default constructor
  public UnitForm() {
    this.setUnitId(Long.parseLong("1"));
  }
}
