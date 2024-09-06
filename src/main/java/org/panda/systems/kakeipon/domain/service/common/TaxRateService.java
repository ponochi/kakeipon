package org.panda.systems.kakeipon.domain.service.common;

import org.panda.systems.kakeipon.domain.model.common.TaxRate;
import org.panda.systems.kakeipon.domain.model.common.TaxType;
import org.panda.systems.kakeipon.domain.repository.common.TaxRateRepository;
import org.panda.systems.kakeipon.domain.repository.common.TaxTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Service
public class TaxRateService implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Autowired
  TaxRateRepository taxRateRepository;

  public List<TaxRate> findAll() {
    return taxRateRepository.findAll();
  }

  public TaxRate findById(Long taxRateId) {
    return taxRateRepository.getById(taxRateId);
  }

  public TaxRate saveAndFlush(TaxRate entity) {
    return taxRateRepository.saveAndFlush(entity);
  }
}
