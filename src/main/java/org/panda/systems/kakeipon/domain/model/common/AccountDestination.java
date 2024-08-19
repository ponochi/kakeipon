package org.panda.systems.kakeipon.domain.model.common;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_account_info")
public class AccountDestination {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "tbl_account_info_seq", allocationSize = 1)
  @Column(name = "account_id")
  private Long accountDestinationId;

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
  public AccountDestination() {
  }

  // Getters and Setters

  public Long getAccountDestinationId() {
    return accountDestinationId;
  }

  public void setAccountDestinationId(Long accountDestinationId) {
    this.accountDestinationId = accountDestinationId;
  }

  public @NotEmpty String getAccountName() {
    return accountName;
  }

  public void setAccountName(@NotEmpty String accountName) {
    this.accountName = accountName;
  }

  public String getBranchName() {
    return branchName;
  }

  public void setBranchName(String branchName) {
    this.branchName = branchName;
  }

  public @PastOrPresent LocalDateTime getEntryDate() {
    return entryDate;
  }

  public void setEntryDate(@PastOrPresent LocalDateTime entryDate) {
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
    return "AccountDestination{" +
        "accountDestinationId=" + accountDestinationId +
        ", accountName='" + accountName + '\'' +
        ", branchName='" + branchName + '\'' +
        ", entryDate=" + entryDate +
        ", updateDate=" + updateDate +
        '}';
  }
}
