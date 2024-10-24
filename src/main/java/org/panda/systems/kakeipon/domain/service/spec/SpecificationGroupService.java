package org.panda.systems.kakeipon.domain.service.spec;


import jakarta.persistence.EntityManager;
import org.panda.systems.kakeipon.app.spec.SpecificationGroupForm;
import org.panda.systems.kakeipon.domain.model.spec.SpecificationGroup;
import org.panda.systems.kakeipon.domain.repository.spec.SpecificationGroupRepository;
import org.panda.systems.kakeipon.domain.repository.spec.SpecificationRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service
public class SpecificationGroupService implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  public final SpecificationGroupRepository specificationGroupRepository;
  public final SpecificationRepository specificationRepository;
  public final EntityManager entityManager;

  public SpecificationGroupService(SpecificationGroupRepository specificationGroupRepository, SpecificationRepository specificationRepository, EntityManager entityManager) {
    this.specificationGroupRepository = specificationGroupRepository;
    this.specificationRepository = specificationRepository;
    this.entityManager = entityManager;
  }

  public Long getMaxGroupId() {
    return specificationGroupRepository.getMaxGroupId();
  }

  public Long getNextGroupId() {
    return specificationGroupRepository.getMaxGroupId() + 1;
  }

  public List<SpecificationGroup> findAll() {

    return specificationGroupRepository.findAll(
        Sort.by(Sort.Direction.DESC,
            "specificationGroupId")
    );
  }

  public SpecificationGroup findBySpecificationGroupIdAndUserIdAndDeleted(
      Long specificationGroupId, Long userId, Boolean deleted) {

    return specificationGroupRepository
        .findBySpecificationGroupIdAndUserIdAndDeleted(
            specificationGroupId,
            userId,
            deleted).orElse(null);
  }

  public List<SpecificationGroupForm> findAllToForm(Boolean deleted) {

    List<SpecificationGroup> specificationGroups
        = specificationGroupRepository.findAllByDeleted(
        deleted,
        Sort.by(
            Sort.Direction.DESC,
            "specificationGroupId")
    );

//        SpecificationGroupForm specificationGroupForm
//                = new SpecificationGroupForm();

    List<SpecificationGroupForm> groupForms = new ArrayList<>();
    for (SpecificationGroup specificationGroup : specificationGroups) {
      SpecificationGroupForm specificationGroupForm
          = new SpecificationGroupForm().setSpecificationGroupToForm(specificationGroup);
      if (specificationGroupForm != null) {
        groupForms.add(specificationGroupForm);
      }
    }
    return groupForms;
  }

  //    @Transactional
  public void saveAndFlushSpecificationGroup(SpecificationGroup entity) {
    specificationGroupRepository.saveAndFlush(entity);
  }

//  @Lock(LockModeType.OPTIMISTIC)
//  @Transactional
//  public void updateSpecificationGroup(SpecificationGroup entity) {
//    specificationGroupRepository.saveAndFlush(entity);
//  }
}
