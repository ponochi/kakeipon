package org.panda.systems.kakeipon.domain.model.common;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "tbl_tax_rate")
@Data
public class TaxRate implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "tbl_tax_rate_seq", allocationSize = 1)
  @Column(name = "tax_rate_id")
  private Long taxRateId;
  private Long taxRate;
}
