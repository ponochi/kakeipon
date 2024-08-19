package org.panda.systems.kakeipon.domain.model.common;

import java.io.Serial;
import java.io.Serializable;


public class AccountPkey implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  private Long accountId;

  public AccountPkey() {
  }

  public AccountPkey(Long accountId) {
    this.accountId = accountId;
  }

  public Long getAccountId() {
    return accountId;
  }

  public void setAccountId(Long accountId) {
    this.accountId = accountId;
  }

  @Override
  public String toString() {
    return "AccountId{" +
        "accountId=" + accountId +
        '}';
  }
}
