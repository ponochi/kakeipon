package org.panda.systems.kakeipon.domain.service.spec;

import org.panda.systems.kakeipon.app.spec.SpecificationGroupForm;
import org.panda.systems.kakeipon.domain.model.spec.SpecificationGroup;
import org.panda.systems.kakeipon.domain.repository.spec.SpecificationGroupRepository;
import org.panda.systems.kakeipon.domain.service.account.AccountDestinationService;
import org.panda.systems.kakeipon.domain.service.account.AccountSourceService;
import org.panda.systems.kakeipon.domain.service.common.AccountAndBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
  private AccountAndBalanceService accountAndBalanceService;
  @Autowired
  private AccountSourceService accountSourceService;
  @Autowired
  private AccountDestinationService accountDestinationService;

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
  public SpecificationGroup saveAndFlush(SpecificationGroup entity) {
    return specificationGroupRepository.saveAndFlush(entity);
  }

  @Transactional
  public void update(
      SpecificationGroup specificationGroup) {
    specificationGroupRepository.update(
        specificationGroup.getSpecificationGroupId(),
        specificationGroup.getUserId(),
        specificationGroup.getShopId(),
        specificationGroup.getReceivingAndPaymentDate(),
        specificationGroup.getReceivingAndPaymentTime(),
        specificationGroup.getBalanceTypeId(),
        specificationGroup.getAccountAndBalanceId(),
        specificationGroup.getGroupMemo(),
        specificationGroup.getDeleted(),
        specificationGroup.getEntryDate(),
        specificationGroup.getUpdateDate());
  }
}
