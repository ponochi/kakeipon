package org.panda.systems.kakeipon.domain.service.spec;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import org.panda.systems.kakeipon.app.spec.SpecificationGroupForm;
import org.panda.systems.kakeipon.domain.model.spec.Specification;
import org.panda.systems.kakeipon.domain.model.spec.SpecificationGroup;
import org.panda.systems.kakeipon.domain.repository.spec.SpecificationGroupRepository;
import org.panda.systems.kakeipon.domain.service.account.AccountDestinationService;
import org.panda.systems.kakeipon.domain.service.account.AccountSourceService;
import org.panda.systems.kakeipon.domain.service.common.AccountAndBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
@Service
public class SpecificationGroupService implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @SuppressWarnings("rawtypes")
  @Autowired
  private SpecificationGroupRepository specificationGroupRepository;
  @Autowired
  private SpecificationService specificationService;
  @Autowired
  private AccountAndBalanceService accountAndBalanceService;
  @Autowired
  private AccountSourceService accountSourceService;
  @Autowired
  private AccountDestinationService accountDestinationService;
  @Autowired
  private EntityManager entityManager;

  public Long getMaxGroupId() {
    return specificationGroupRepository.getMaxGroupId();
  }

  @SuppressWarnings("rawtypes")
  public List<SpecificationGroup> findAll() {
    return specificationGroupRepository.findAll(
        Sort.by(Sort.Direction.DESC, "specificationGroupId"));
  }

  @SuppressWarnings("rawtypes")
  public SpecificationGroup findById(
      Long specificationGroupId, Boolean deleted) {
    return specificationGroupRepository
        .findBySpecificationGroupIdAndDeleted(
            specificationGroupId, deleted).orElse(null);
  }

  public List<SpecificationGroupForm> findAllToForm(Boolean deleted) {
    List<SpecificationGroup> specificationGroups
        = specificationGroupRepository.findAllByDeleted(
            deleted,
            Sort.by(
                Sort.Direction.DESC,
                "specificationGroupId"));

    SpecificationGroupForm specificationGroupForm
        = new SpecificationGroupForm();

    List<SpecificationGroupForm> groupForms = new ArrayList<>();
    for (SpecificationGroup specificationGroup : specificationGroups) {
      groupForms.add(
          specificationGroupForm.setSpecificationGroupToForm(
              accountAndBalanceService,
              accountSourceService,
              accountDestinationService,
              specificationGroup));
    }
    return groupForms;
  }

  @Transactional
  public SpecificationGroup saveAndFlushSpecificationGroup(SpecificationGroup entity) {
    return specificationGroupRepository.saveAndFlush(entity);
  }

  @Lock(LockModeType.OPTIMISTIC)
  @Transactional
  public void updateSpecificationGroup(SpecificationGroup entity) {
    List<Specification> specifications
        = specificationService.findBySpecificationGroupIdAndUserIdAndDeleted(
            entity.getSpecificationGroupId(), entity.getUserId(), false);
    specificationService.saveAllSpecifications(specifications);

    SpecificationGroup saveAndFlushSpecificationGroup
        = entityManager.find(
            SpecificationGroup.class, entity.getSpecificationGroupId());
    entityManager.lock(
        saveAndFlushSpecificationGroup, LockModeType.OPTIMISTIC);
    saveAndFlushSpecificationGroup.setSpecificationGroupId(
        entity.getSpecificationGroupId());
    saveAndFlushSpecificationGroup.setUserId(
        entity.getUserId());
    saveAndFlushSpecificationGroup.setShopId(
        entity.getShopId());
    saveAndFlushSpecificationGroup.setReceivingAndPaymentDate(
        entity.getReceivingAndPaymentDate());
    saveAndFlushSpecificationGroup.setReceivingAndPaymentTime(
        entity.getReceivingAndPaymentTime());
    saveAndFlushSpecificationGroup.setBalanceTypeId(
        entity.getBalanceTypeId());
    saveAndFlushSpecificationGroup.setAccountAndBalanceId(
        entity.getAccountAndBalanceId());
    saveAndFlushSpecificationGroup.setGroupMemo(
        entity.getGroupMemo());
    saveAndFlushSpecificationGroup.setDeleted(
        entity.getDeleted());
    saveAndFlushSpecificationGroup.setEntryDate(
        entity.getEntryDate());
    saveAndFlushSpecificationGroup.setUpdateDate(
        entity.getUpdateDate());
    saveAndFlushSpecificationGroup.setVersion(
        entity.getVersion());

    entityManager.merge(saveAndFlushSpecificationGroup);
  }
}
