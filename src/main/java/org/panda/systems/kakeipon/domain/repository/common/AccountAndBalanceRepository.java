package org.panda.systems.kakeipon.domain.repository.common;

import org.panda.systems.kakeipon.domain.model.common.AccountAndBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountAndBalanceRepository
    extends JpaRepository<AccountAndBalance, Long> {

  @SuppressWarnings("JpaQlInspection")
  @Query(nativeQuery = true,
      value = "SELECT" +
          "   MAX(taab.account_and_balance_id) AS account_and_balance_id" +
      " FROM" +
      "   account_and_balance taab")
  Long getMaxAccountAndBalanceId();

  @Override
  List<AccountAndBalance> findAll();

  AccountAndBalance getById(Long accountId);

  @Override
  AccountAndBalance saveAndFlush(AccountAndBalance entity);
}
