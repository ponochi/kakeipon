package org.panda.systems.kakeipon.domain.repository.common;

import org.panda.systems.kakeipon.domain.model.common.AccountSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountSourceRepository
    extends JpaRepository<AccountSource, Long> {
  @SuppressWarnings({"unchecked", "NullableProblems"})
  @Override
  AccountSource saveAndFlush(AccountSource accountSource);

  @SuppressWarnings("NullableProblems")
  @Override
  AccountSource getById(Long accountSourceId);

  @SuppressWarnings("NullableProblems")
  @Override
  List<AccountSource> findAll();
}
