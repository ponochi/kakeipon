package org.panda.systems.kakeipon.domain.service.account;

import org.panda.systems.kakeipon.domain.model.account.AccountSource;
import org.panda.systems.kakeipon.domain.repository.account.AccountSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Service
public class AccountSourceService implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Autowired
  private AccountSourceRepository accountSourceRepository;

  public List<AccountSource> findAll() {
    return accountSourceRepository.findAll();
  }

  public AccountSource findById(Long accountSourceId) {
    return accountSourceRepository.findById(accountSourceId).orElse(null);
  }

  public AccountSource saveAndFlush(AccountSource accountSource) {
    return accountSourceRepository.saveAndFlush(accountSource);
  }
}
