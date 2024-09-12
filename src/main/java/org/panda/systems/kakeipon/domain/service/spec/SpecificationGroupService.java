package org.panda.systems.kakeipon.domain.service.spec;

import org.panda.systems.kakeipon.app.spec.SpecificationForm;
import org.panda.systems.kakeipon.app.spec.SpecificationGroupForm;
import org.panda.systems.kakeipon.domain.model.spec.Specification;
import org.panda.systems.kakeipon.domain.model.spec.SpecificationGroup;
import org.panda.systems.kakeipon.domain.repository.spec.SpecificationGroupRepository;
import org.panda.systems.kakeipon.domain.service.account.AccountDestinationService;
import org.panda.systems.kakeipon.domain.service.account.AccountSourceService;
import org.panda.systems.kakeipon.domain.service.common.AccountAndBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
  public SpecificationGroup findById(Long specificationGroupId) {
    return specificationGroupRepository.findById(specificationGroupId).orElse(null);
  }

  public List<SpecificationGroupForm> findAllToForm() {
    List<SpecificationGroup> specificationGroups
        = specificationGroupRepository.findAll(
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
        specificationGroup.getMemo(),
        specificationGroup.getEntryDate(),
        specificationGroup.getUpdateDate());
  }
}
