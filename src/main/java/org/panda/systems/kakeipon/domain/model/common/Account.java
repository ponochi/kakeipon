package org.panda.systems.kakeipon.domain.model.common;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_account")
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "tbl_account_seq", allocationSize = 1)
  @Column
  private Long accountId;

  @NotEmpty
  @Column
  private String accountName;

  @Column
  private String branchName;

  @NotEmpty
  @Column
  private LocalDateTime entryDate;

  @Column
  private LocalDateTime updateDate;

  // Default Constructor
  public Account() {
  }

  // Getters and Setters
  public Long getAccountId() {
    if (accountId == null) {
      return 0L;
    } else {
      return accountId;
    }
  }

  public void setAccountId(Long shopId) {
    this.accountId = accountId;
  }

  public String getAccountName() {
    return accountName;
  }

  public void setAccountName(String shopName) {
    this.accountName = shopName;
  }

  public String getBranchName() {
    return branchName;
  }

  public void setBranchName(String branchName) {
    this.branchName = branchName;
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
    return "Account{" +
        "shopId=" + accountId +
        ", shopName='" + accountName + '\'' +
        ", branchName='" + branchName + '\'' +
        ", entryDate=" + entryDate +
        ", updateDate=" + updateDate +
        '}';
  }
}
