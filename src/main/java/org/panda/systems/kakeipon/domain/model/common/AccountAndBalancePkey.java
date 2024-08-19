package org.panda.systems.kakeipon.domain.model.common;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class AccountAndBalancePkey implements Serializable {
  @Column
  private Long accountAndBalanceId;
  @Column
  private Long accountId;
  @Column
  private Long balanceId;

  // Default Constructor
  public AccountAndBalancePkey() {
  }

  // Constructor
  public AccountAndBalancePkey(
      Long accountAntBalanceId, Long accountId, Long balanceId) {
    this.accountAndBalanceId = accountAntBalanceId;
    this.accountId = accountId;
    this.balanceId = balanceId;
  }

  // Getters and Setters
  public Long getAccountAndBalanceId() {
    return accountAndBalanceId;
  }

  public void setAccountAndBalanceId(Long accountAndBalanceId) {
    this.accountAndBalanceId = accountAndBalanceId;
  }

  public Long getAccountId() {
    return accountId;
  }

  public void setAccountId(Long accountId) {
    this.accountId = accountId;
  }

  public Long getBalanceId() {
    return balanceId;
  }

  public void setBalanceId(Long balanceId) {
    this.balanceId = balanceId;
  }

  // toString
  @Override
  public String toString() {
    return "AccountAndBalancePkey{" +
        "accountAndBalanceId=" + accountAndBalanceId +
        ", accountId=" + accountId +
        ", balanceId=" + balanceId +
        '}';
  }

  // equals
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    AccountAndBalancePkey that = (AccountAndBalancePkey) o;

    if (accountId != null ? !accountId.equals(that.accountId) : that.accountId != null) return false;
    return balanceId != null ? balanceId.equals(that.balanceId) : that.balanceId == null;
  }

  // hashCode
  @Override
  public int hashCode() {
    int result = accountId != null ? accountId.hashCode() : 0;
    result = 31 * result + (balanceId != null ? balanceId.hashCode() : 0);
    return result;
  }
}
