package org.panda.systems.kakeipon.domain.service.spec;

import org.panda.systems.kakeipon.app.spec.SpecificationForm;
import org.panda.systems.kakeipon.app.spec.SpecificationGroupForm;
import org.panda.systems.kakeipon.domain.model.spec.Specification;
import org.panda.systems.kakeipon.domain.model.spec.SpecificationGroup;
import org.panda.systems.kakeipon.domain.repository.spec.SpecificationGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
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

  public Long getMaxGroupId() {
    return specificationGroupRepository.getMaxGroupId();
  }

  @SuppressWarnings("rawtypes")
  public List<SpecificationGroup> findAll() {
    return specificationGroupRepository.findAll();
  }

  @SuppressWarnings("rawtypes")
  public SpecificationGroup findById(Long specificationGroupId) {
    return specificationGroupRepository.findById(specificationGroupId).orElse(null);
  }

  public List<SpecificationGroupForm> findAllToForm() {
    List<SpecificationGroup> specificationGroups
        = specificationGroupRepository.findAll();
    SpecificationGroupForm specificationGroupForm
        = new SpecificationGroupForm();

    List<SpecificationGroupForm> groupForms = new ArrayList<>();
    for (SpecificationGroup specificationGroup : specificationGroups) {
      groupForms.add(
          specificationGroupForm.setSpecificationGroupToForm(specificationGroup));
    }
    return groupForms;
  }

  @Transactional
  public SpecificationGroup saveAndFlush(SpecificationGroup entity) {
    return specificationGroupRepository.saveAndFlush(entity);
  }
}
