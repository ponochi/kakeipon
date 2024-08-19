package org.panda.systems.kakeipon.domain.repository.common;

import org.panda.systems.kakeipon.domain.model.common.AccountDestination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountDestinationRepository
    extends JpaRepository<AccountDestination, Long> {
  @SuppressWarnings({"unchecked", "NullableProblems"})
  @Override
  AccountDestination saveAndFlush(AccountDestination accountSource);

  @SuppressWarnings("NullableProblems")
  AccountDestination getById(Long accountId);

  @SuppressWarnings("NullableProblems")
  @Override
  List<AccountDestination> findAll();
}
