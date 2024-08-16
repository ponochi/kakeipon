package org.panda.systems.kakeipon.domain.service.common;

import org.panda.systems.kakeipon.domain.model.common.Account;
import org.panda.systems.kakeipon.domain.repository.common.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class AccountService implements Serializable {
  private static final long serialVersionUID = 1L;

  @Autowired
  AccountRepository accountRepository;

  public AccountService() {
  }

  public List<Account> findAll() {
    return accountRepository.findAll();
  }

  public Account findById(Long id) {
    return accountRepository.findById(id).orElse(null);
  }

  public Account saveAndFlush(Account entity) {
    return accountRepository.saveAndFlush(entity);
  }
}
