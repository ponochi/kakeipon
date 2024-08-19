package org.panda.systems.kakeipon.domain.model.common;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_balance")
public class Balance {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "tbl_balance_seq", allocationSize = 1)
  @EmbeddedId
  private BalancePkey balancePkey;

  @NotEmpty
  @Column(name = "balance_name")
  private String balanceName;

  // Default Constructor
  public Balance() {
  }

  // Getters and Setters
  public BalancePkey getBalancePkey() {
    return balancePkey;
  }

  public void setBalancePkey(BalancePkey balancePkey) {
    this.balancePkey = balancePkey;
  }

  public String getBalanceName() {
    return balanceName;
  }

  public void setBalanceName(String balanceName) {
    this.balanceName = balanceName;
  }

  // toString
  @Override
  public String toString() {
    return "Balance{" +
        "balancePkey=" + balancePkey +
        ", balanceName='" + balanceName + '\'' +
        '}';
  }
}
