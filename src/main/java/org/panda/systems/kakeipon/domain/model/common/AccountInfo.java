package org.panda.systems.kakeipon.domain.model.common;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_account_info")
public class AccountInfo {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "tbl_account_info_seq", allocationSize = 1)
  @EmbeddedId
  private AccountPkey accountPkey;

  @NotEmpty
  @Column(name = "account_name")
  private String accountName;

  @Column(name = "branch_name")
  private String branchName;

  @PastOrPresent
  @Column
  private LocalDateTime entryDate;

  @Column
  private LocalDateTime updateDate;

  // Default Constructor
  public AccountInfo() {
  }

  // Getters and Setters
  public AccountPkey getAccountPkey() {
    return accountPkey;
  }

  public void setAccountPkey(AccountPkey accountId) {
    this.accountPkey = accountId;
  }

  public String getAccountName() {
    return accountName;
  }

  public void setAccountName(String accountName) {
    this.accountName = accountName;
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
    return "AccountInfo{" +
        "accountId=" + accountPkey.getAccountId() +
        ", accountName='" + accountName + '\'' +
        ", branchName='" + branchName + '\'' +
        ", entryDate=" + entryDate +
        ", updateDate=" + updateDate +
        '}';
  }
}
