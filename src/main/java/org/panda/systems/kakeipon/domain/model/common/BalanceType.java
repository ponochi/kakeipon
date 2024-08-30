package org.panda.systems.kakeipon.domain.model.common;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Table(name = "tbl_balance_type")
@Data
public class BalanceType {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "tbl_balance_type_seq", allocationSize = 1)
  @Column(name = "balance_type_id")
  private Long balanceTypeId;

  @NotEmpty
  @Column(name = "balance_type_name")
  private String balanceTypeName;
}
