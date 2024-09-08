package org.panda.systems.kakeipon.app.common;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

@Table(name = "tbl_balance_type")
@Data
public class BalanceTypeForm implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "tbl_balance_type_seq", allocationSize = 1)
  @Column(name = "balance_type_id")
  private Long balanceTypeId;

  @NotEmpty
  @Column(name = "balance_type_name")
  private String balanceTypeName;

  // Default constructor
  public BalanceTypeForm() {
    this.setBalanceTypeId(Long.parseLong("1"));
  }
}
