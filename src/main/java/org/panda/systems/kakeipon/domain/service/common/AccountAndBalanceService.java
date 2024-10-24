package org.panda.systems.kakeipon.domain.service.common;

import org.panda.systems.kakeipon.domain.model.common.AccountAndBalance;
import org.panda.systems.kakeipon.domain.repository.common.AccountAndBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Service
public class AccountAndBalanceService implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Autowired
  AccountAndBalanceRepository accountRepository;

  public Long getMaxAccountAndBalanceId() {
    return accountRepository.getMaxAccountAndBalanceId();
  }

  public List<AccountAndBalance> findAll() {
    return accountRepository.findAll();
  }

  public AccountAndBalance getById(Long accountAndBalanceId) {
    AccountAndBalance accountAndBalance = accountRepository.getById(accountAndBalanceId);
    if (accountAndBalance == null) {
      accountAndBalance = new AccountAndBalance();
      accountAndBalance.setAccountAndBalanceId(accountRepository.getMaxAccountAndBalanceId() + 1);
    }
    return accountAndBalance;
  }

  public AccountAndBalance saveAndFlush(AccountAndBalance entity) {
    return accountRepository.saveAndFlush(entity);
  }
}
