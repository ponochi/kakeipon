package org.panda.systems.kakeipon.domain.model.common;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_balance")
public class Balance {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "tbl_balance_seq", allocationSize = 1)
  @Column(name = "balance_id")
  private Long balanceId;

  @NotEmpty
  @Column(name = "balance_name")
  private String balanceName;

  @PastOrPresent
  @Column
  private LocalDateTime entryDate;

  @Column
  private LocalDateTime updateDate;

  // Default Constructor
  public Balance() {
  }

  // Getters and Setters
  public Long getBalanceId() {
    if (balanceId == null) {
      return 1L;
    } else {
      return balanceId;
    }
  }

  public void setBalanceId(Long balanceId) {
    this.balanceId = balanceId;
  }

  public String getBalanceName() {
    return balanceName;
  }

  public void setBalanceName(String balanceName) {
    this.balanceName = balanceName;
  }

  public LocalDateTime getEntryDate() {
    return entryDate;
  }

  public void setEntryDate(LocalDateTime entryDate) {
    this.entryDate = entryDate;
  }

  public LocalDateTime getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(LocalDateTime updateDate) {
    this.updateDate = updateDate;
  }

  // toString
  @Override
  public String toString() {
    return "Balance{" +
        "balanceId=" + balanceId +
        ", balanceName='" + balanceName + '\'' +
        ", entryDate=" + entryDate +
        ", updateDate=" + updateDate +
        '}';
  }
}
