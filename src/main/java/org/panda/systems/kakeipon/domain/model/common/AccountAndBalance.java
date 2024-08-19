package org.panda.systems.kakeipon.domain.model.common;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
public class AccountAndBalance {
  @EmbeddedId
  private AccountAndBalancePkey accountAndBalancePkey;


  private LocalDateTime entryDate;
  private LocalDateTime updateDate;

  // Default Constructor
  public AccountAndBalance() {
  }

  // Getters and Setters
  public AccountAndBalancePkey getAccountAndBalancePkey() {
    return accountAndBalancePkey;
  }

  public void setAccountAndBalancePkey(AccountAndBalancePkey accountAndBalancePkey) {
    this.accountAndBalancePkey = accountAndBalancePkey;
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

  @Override
  public String toString() {
    return "AccountAndBalance{" +
        "accountAndBalancePkey=" + accountAndBalancePkey +
        ", entryDate=" + entryDate +
        ", updateDate=" + updateDate +
        '}';
  }
}
