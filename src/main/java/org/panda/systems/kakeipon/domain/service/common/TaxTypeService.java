package org.panda.systems.kakeipon.domain.service.common;

import org.panda.systems.kakeipon.domain.model.common.TaxType;
import org.panda.systems.kakeipon.domain.repository.common.TaxTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Service
public class TaxTypeService implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Autowired
  TaxTypeRepository taxTypeRepository;

  public List<TaxType> findAll() {
    return taxTypeRepository.findAll();
  }

  public TaxType findById(Long taxTypeId) {
    return taxTypeRepository.getById(taxTypeId);
  }

  public TaxType saveAndFlush(TaxType entity) {
    return taxTypeRepository.saveAndFlush(entity);
  }
}
