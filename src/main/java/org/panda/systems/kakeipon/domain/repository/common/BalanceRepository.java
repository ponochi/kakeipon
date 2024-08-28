package org.panda.systems.kakeipon.domain.repository.common;

import org.panda.systems.kakeipon.domain.model.common.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {
  @SuppressWarnings({"NullableProblems"})
  @Override
  List<Balance> findAll();

  @SuppressWarnings({"NullableProblems"})
  @Override
  Balance getById(Long balanceId);
}
