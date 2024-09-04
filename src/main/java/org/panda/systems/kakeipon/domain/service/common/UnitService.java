package org.panda.systems.kakeipon.domain.service.common;

import org.panda.systems.kakeipon.domain.model.common.Unit;
import org.panda.systems.kakeipon.domain.repository.common.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Service
public class UnitService implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  @Autowired
  UnitRepository unitRepository;

  public List<Unit> findAll() {
    return unitRepository.findAll();
  }

  public Unit findById(Long unitId) {
    return unitRepository.getById(unitId);
  }

  public Unit saveAndFlush(Unit entity) {
    return unitRepository.saveAndFlush(entity);
  }
}
