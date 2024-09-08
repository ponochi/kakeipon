package org.panda.systems.kakeipon.app.common;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Table(name = "tbl_tax_type")
@Data
public class TaxTypeForm implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
  @SequenceGenerator(name = "tbl_tax_type_seq", allocationSize = 1)
  @Column(name = "tax_type_id")
  private Long taxTypeId;

  @Column(name = "tax_type_name")
  private String taxTypeName;

  // Default constructor
  public TaxTypeForm() {
    this.setTaxTypeId(Long.parseLong("1"));
  }
}
