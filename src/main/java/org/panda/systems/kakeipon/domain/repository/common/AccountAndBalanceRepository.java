package org.panda.systems.kakeipon.domain.repository.common;

import org.panda.systems.kakeipon.domain.model.common.AccountAndBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountAndBalanceRepository
    extends JpaRepository<AccountAndBalance, Long> {

  @Query("SELECT MAX(accountAndBalanceId) accountAndBalanceId FROM AccountAndBalance")
  Long getMaxAccountAndBalanceId();

  @Override
  List<AccountAndBalance> findAll();

  AccountAndBalance getById(Long accountId);

  @Override
  AccountAndBalance saveAndFlush(AccountAndBalance entity);
}
