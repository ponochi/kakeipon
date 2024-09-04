package org.panda.systems.kakeipon.domain.model.common;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "tbl_tax_type")
@Data
public class TaxType implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "tbl_tax_type_seq", allocationSize = 1)
  @Column(name = "tax_type_id")
  private Long taxTypeId;
  private String taxTypeName;
  private BigDecimal taxRate;
}
