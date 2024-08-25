package org.panda.systems.kakeipon.domain.model.common;

import jakarta.persistence.Column;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class BalancePkey implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Column(name = "balance_id")
  private Long balanceId;

  // Default Constructor
  public BalancePkey() {
  }
}
