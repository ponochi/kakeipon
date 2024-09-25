package org.panda.systems.kakeipon.domain.repository.account;

import org.panda.systems.kakeipon.domain.model.account.AccountDestination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountDestinationRepository
    extends JpaRepository<AccountDestination, Long> {
  @Override
  AccountDestination saveAndFlush(AccountDestination accountSource);

  Optional<AccountDestination> findById(Long accountId);

  @Override
  List<AccountDestination> findAll();
}
