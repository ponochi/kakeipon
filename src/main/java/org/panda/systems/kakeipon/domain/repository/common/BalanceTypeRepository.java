package org.panda.systems.kakeipon.domain.repository.common;

import org.panda.systems.kakeipon.domain.model.common.BalanceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BalanceTypeRepository extends JpaRepository<BalanceType, Long> {
  @Override
  List<BalanceType> findAll();

  @Override
  BalanceType getById(Long balanceTypeId);
}
