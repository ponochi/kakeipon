package org.panda.systems.kakeipon.domain.service.common;

import org.panda.systems.kakeipon.domain.model.common.AccountDestination;
import org.panda.systems.kakeipon.domain.repository.common.AccountDestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Service
public class AccountDestinationService implements Serializable {
  @Autowired
  private AccountDestinationRepository accountDestinationRepository;

  @Serial
  private static final long serialVersionUID = 1L;

  public AccountDestination saveAndFlush(AccountDestination accountDestination)
  {
    return accountDestinationRepository.saveAndFlush(accountDestination);
  }

  public AccountDestination getById(Long accountId)

  {
    return accountDestinationRepository.getById(accountId);
  }

  public List<AccountDestination> findAll()

  {
    return accountDestinationRepository.findAll();
  }
}
