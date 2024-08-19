package org.panda.systems.kakeipon.domain.repository.common;

import org.panda.systems.kakeipon.domain.model.common.AccountAndBalance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountAndBalancePkeyRepository
    extends JpaRepository<AccountAndBalance, Long> {
  @SuppressWarnings({"unchecked", "NullableProblems"})
  @Override
  AccountAndBalance saveAndFlush(AccountAndBalance entity);

  @SuppressWarnings("NullableProblems")
  AccountAndBalance getById(Long accountId);

  @SuppressWarnings("NullableProblems")
  @Override
  List<AccountAndBalance> findAll();
}
