package org.panda.systems.kakeipon.domain.model.common;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_account_and_balance")
public class AccountAndBalance {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "tbl_account_and_balance_seq", allocationSize = 1)
  @Column(name = "account_and_balance_id")
  private Long accountAndBalanceId;

  @Column(name = "account_source_id")
  private Long accountSourceId;

  @Column(name = "account_destination_id")
  private Long accountDestinationId;

  @Column
  private LocalDateTime entryDate;

  @Column
  private LocalDateTime updateDate;

  // Default Constructor
  public AccountAndBalance() {
  }

  // Getters and Setters
  public Long getAccountAndBalanceId() {
    return accountAndBalanceId;
  }

  public void setAccountAndBalanceId(Long accountAndBalanceId) {
    this.accountAndBalanceId = accountAndBalanceId;
  }

  public Long getAccountSourceId() {
    return accountSourceId;
  }

  public void setAccountSourceId(Long accountSourceId) {
    this.accountSourceId = accountSourceId;
  }

  public Long getAccountDestinationId() {
    return accountDestinationId;
  }

  public void setAccountDestinationId(Long accountDestinationId) {
    this.accountDestinationId = accountDestinationId;
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
    return "AccountAndBalance{" +
        "accountAndBalanceId=" + accountAndBalanceId +
        ", accountSourceId=" + accountSourceId +
        ", accountDestinationId=" + accountDestinationId +
        ", entryDate=" + entryDate +
        ", updateDate=" + updateDate +
        '}';
  }
}
