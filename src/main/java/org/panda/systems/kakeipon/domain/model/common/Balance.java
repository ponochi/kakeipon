package org.panda.systems.kakeipon.domain.model.common;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Table(name = "tbl_balance")
@Data
public class Balance {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "tbl_balance_seq", allocationSize = 1)
  @EmbeddedId
  private BalancePkey balancePkey;

  @NotEmpty
  @Column(name = "balance_name")
  private String balanceName;
}
