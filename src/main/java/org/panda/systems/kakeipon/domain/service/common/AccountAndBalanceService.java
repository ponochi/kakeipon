package org.panda.systems.kakeipon.domain.service.common;

import org.panda.systems.kakeipon.domain.model.common.AccountAndBalance;
import org.panda.systems.kakeipon.domain.repository.common.AccountAndBalancePkeyRepository;
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
  AccountAndBalancePkeyRepository accountRepository;

  public AccountAndBalance saveAndFlush(AccountAndBalance entity) {
    return accountRepository.saveAndFlush(entity);
  }

  public AccountAndBalance getById(Long accountAndBalanceId) {
    return accountRepository.getById(accountAndBalanceId);
  }

  public List<AccountAndBalance> findAll() {
    return accountRepository.findAll();
  }
}
