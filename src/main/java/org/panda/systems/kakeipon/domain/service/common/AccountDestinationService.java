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
  @Serial
  private static final long serialVersionUID = 1L;

  @Autowired
  private AccountDestinationRepository accountDestinationRepository;

  public List<AccountDestination> findAll() {
    return accountDestinationRepository.findAll();
  }

  public AccountDestination findById(Long accountDestinationId) {
    return accountDestinationRepository.findById(accountDestinationId).orElse(null);
  }

  public AccountDestination saveAndFlush(AccountDestination accountDestination) {
    return accountDestinationRepository.saveAndFlush(accountDestination);
  }
}
