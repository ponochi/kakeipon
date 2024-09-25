package org.panda.systems.kakeipon.domain.repository.account;

import org.panda.systems.kakeipon.domain.model.account.AccountSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountSourceRepository extends JpaRepository<AccountSource, Long> {

  @Override
  List<AccountSource> findAll();

  @Override
  Optional<AccountSource> findById(Long accountSourceId);

  @Override
  AccountSource saveAndFlush(AccountSource accountSource);
}
