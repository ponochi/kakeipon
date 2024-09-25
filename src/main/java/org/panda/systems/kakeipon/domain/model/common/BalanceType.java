package org.panda.systems.kakeipon.domain.model.common;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "balance_type")
@Data
public class BalanceType implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "balance_type_seq", allocationSize = 1)
  @Column(name = "balance_type_id")
  private Long balanceTypeId;

  @NotEmpty
  @Column(name = "balance_type_name")
  private String balanceTypeName;
}
