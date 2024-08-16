package org.panda.systems.kakeipon.domain.service.spec;

import org.panda.systems.kakeipon.domain.model.spec.Specification;
import org.panda.systems.kakeipon.domain.repository.spec.SpecificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public class SpecificationService implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Autowired
  private SpecificationRepository specificationRepository;

  public List<Specification> findAll() {
    return specificationRepository.findAll();
  }

  public Optional<Specification> findById(Long specificationId) {
    return specificationRepository.findById(specificationId);
  }

  @Transactional
  public List<Specification> saveAndFlush(List<Specification> entities) {
    return specificationRepository.saveAndFlush(entities);
  }
}
