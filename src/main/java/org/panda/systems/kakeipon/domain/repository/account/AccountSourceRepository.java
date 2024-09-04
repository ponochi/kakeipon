package org.panda.systems.kakeipon.domain.repository.account;

import org.panda.systems.kakeipon.domain.model.account.AccountSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountSourceRepository extends JpaRepository<AccountSource, Long> {

  @SuppressWarnings("NullableProblems")
  @Override
  List<AccountSource> findAll();

  @SuppressWarnings("NullableProblems")
  @Override
  Optional<AccountSource> findById(Long accountSourceId);

  @SuppressWarnings({"unchecked", "NullableProblems"})
  @Override
  AccountSource saveAndFlush(AccountSource accountSource);
}
