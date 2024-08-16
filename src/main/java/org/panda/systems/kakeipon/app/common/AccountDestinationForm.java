package org.panda.systems.kakeipon.app.common;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class AccountDestinationForm implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "tblAccountSeq", allocationSize = 1)
  @PositiveOrZero
  private Long accountId;

  @NotEmpty
  private String accountName;

  @NotEmpty
  private String branchName;

  @NotEmpty
  private LocalDateTime entryDate;

  private LocalDateTime updateDate;

  // Default constructor
  public AccountDestinationForm() {
  }

  // Parameterized constructor
  public AccountDestinationForm(Long accountId) {
    this.accountId = accountId;
  }

  // Getters and Setters
  public Long getAccountId() {
    return accountId;
  }

  public void setAccountId(Long accountId) {
    this.accountId = accountId;
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

  @Override
  public String toString() {
    return "ShopForm{" +
        "shopId=" + accountId +
        ", shopName='" + accountName + '\'' +
        ", branchName='" + branchName + '\'' +
        ", entryDate=" + entryDate +
        ", updateDate=" + updateDate +
        '}';
  }
}
