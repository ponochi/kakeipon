package org.panda.systems.kakeipon.domain.service.common;

import org.panda.systems.kakeipon.domain.model.common.AccountSource;
import org.panda.systems.kakeipon.domain.repository.common.AccountSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class AccountSourceService implements Serializable {
  private static final long serialVersionUID = 1L;

  @Autowired
  private AccountSourceRepository accountSourceRepository;

  public AccountSource saveAndFlush(AccountSource accountSource) {
    return accountSourceRepository.saveAndFlush(accountSource);
  }

  public AccountSource getById(Long accountId)
  {
    return accountSourceRepository.getById(accountId);
  }

  public List<AccountSource> findAll()
  {
    return accountSourceRepository.findAll();
  }
}
