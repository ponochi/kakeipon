package org.panda.systems.kakeipon.domain.service.common;

import org.panda.systems.kakeipon.domain.model.common.BalanceType;
import org.panda.systems.kakeipon.domain.repository.common.BalanceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class BalanceTypeService implements Serializable {
  private static final long serialVersionUID = 1L;

  @Autowired
  private BalanceTypeRepository balanceTypeRepository;

  public BalanceTypeService() {
    super();
  }

  public List<BalanceType> findAll() {
    return balanceTypeRepository.findAll();
  }

  public BalanceType findById(Long balanceId) {
    return balanceTypeRepository.getById(balanceId);
  }

}
