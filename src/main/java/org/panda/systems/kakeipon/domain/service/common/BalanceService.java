package org.panda.systems.kakeipon.domain.service.common;

import org.panda.systems.kakeipon.domain.model.common.Balance;
import org.panda.systems.kakeipon.domain.repository.common.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class BalanceService implements Serializable {
  private static final long serialVersionUID = 1L;

  @Autowired
  private BalanceRepository balanceRepository;

  public BalanceService() {
    super();
  }

  public List<Balance> findAll() {
    return balanceRepository.findAll();
  }

  public Balance getById(Long balanceId) {
    return balanceRepository.getById(balanceId);
  }

}
