package org.panda.systems.kakeipon.domain.service.spec;

import org.panda.systems.kakeipon.domain.model.spec.SpecificationGroup;
import org.panda.systems.kakeipon.domain.repository.spec.SpecificationGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("ALL")
@Service
public class SpecificationGroupService implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @SuppressWarnings("rawtypes")
  @Autowired
  private SpecificationGroupRepository specificationGroupRepository;

  @SuppressWarnings("rawtypes")
  public List<SpecificationGroup> findAll() {
    return specificationGroupRepository.findAll();
  }

  @SuppressWarnings("rawtypes")
  public Optional<SpecificationGroup> findById(Long specificationGroupId) {
    return specificationGroupRepository.findById(specificationGroupId);
  }

  @Transactional
  public SpecificationGroup saveAndFlush(SpecificationGroup entity) {
    return specificationGroupRepository.saveAndFlush(entity);
  }
}
