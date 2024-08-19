package org.panda.systems.kakeipon.domain.model.common;

import jakarta.persistence.Column;

import java.io.Serial;
import java.io.Serializable;

public class BalancePkey implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Column(name = "balance_id")
  private Long balanceId;

  // Default Constructor
  public BalancePkey() {
  }

  // Constructor
  public BalancePkey(Long balanceId) {
    this.balanceId = balanceId;
  }

  // Getters and Setters
  public Long getBalanceId() {
    return balanceId;
  }

  public void setBalanceId(Long balanceId) {
    this.balanceId = balanceId;
  }

  // toString
  @Override
  public String toString() {
    return "BalanceId{" +
        "balanceId=" + balanceId +
        '}';
  }
}
