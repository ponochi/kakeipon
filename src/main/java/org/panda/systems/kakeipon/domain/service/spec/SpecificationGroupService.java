package org.panda.systems.kakeipon.domain.service.spec;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import org.panda.systems.kakeipon.app.spec.SpecificationGroupForm;
import org.panda.systems.kakeipon.app.user.UserForm;
import org.panda.systems.kakeipon.domain.model.spec.Specification;
import org.panda.systems.kakeipon.domain.model.spec.SpecificationGroup;
import org.panda.systems.kakeipon.domain.repository.spec.SpecificationGroupRepository;
import org.panda.systems.kakeipon.domain.service.account.AccountDestinationService;
import org.panda.systems.kakeipon.domain.service.account.AccountSourceService;
import org.panda.systems.kakeipon.domain.service.common.AccountAndBalanceService;
import org.panda.systems.kakeipon.domain.service.common.BalanceTypeService;
import org.panda.systems.kakeipon.domain.service.common.ShopService;
import org.panda.systems.kakeipon.domain.service.user.KakeiPonUsersDetailsService;
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
  private KakeiPonUsersDetailsService kakeiPonUsersDetailsService;
  @Autowired
  private EntityManager entityManager;
  @Autowired
  private ShopService shopService;
  @Autowired
  private BalanceTypeService balanceTypeService;

  public Long getMaxGroupId() {
    return specificationGroupRepository.getMaxGroupId();
  }

  @SuppressWarnings("rawtypes")
  public List<SpecificationGroup> findAll() {
    return specificationGroupRepository.findAll(
        Sort.by(Sort.Direction.DESC, "specificationGroupId"));
  }

  public SpecificationGroup findBySpecificationGroupIdAndUsernameAndDeleted(
      Long specificationGroupId, String username, Boolean deleted) {
    System.out.println("username : " + username);
    System.out.println("id : " + kakeiPonUsersDetailsService.convertUsernameToId(username));
    return specificationGroupRepository
        .findBySpecificationGroupIdAndIdAndDeleted(
            specificationGroupId,
            kakeiPonUsersDetailsService.convertUsernameToId(username),
            deleted).orElse(null);
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
              kakeiPonUsersDetailsService,
              shopService,
              balanceTypeService,
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
    UserForm userForm = kakeiPonUsersDetailsService.findByIdToForm(entity.getId());
    List<Specification> specifications
        = specificationService.findBySpecificationGroupIdAndUsernameAndDeleted(
            entity.getSpecificationGroupId(), userForm.getUsername(), false);
    specificationService.saveAllSpecifications(specifications);

    SpecificationGroup updateSpecGroup
        = entityManager.find(
            SpecificationGroup.class, entity.getSpecificationGroupId());
    entityManager.lock(
        updateSpecGroup, LockModeType.OPTIMISTIC);
    updateSpecGroup.setSpecificationGroupId(
        entity.getSpecificationGroupId());
    updateSpecGroup.setId(
        entity.getId());
    updateSpecGroup.setShopId(
        entity.getShopId());
    updateSpecGroup.setReceivingAndPaymentDate(
        entity.getReceivingAndPaymentDate());
    updateSpecGroup.setReceivingAndPaymentTime(
        entity.getReceivingAndPaymentTime());
    updateSpecGroup.setBalanceTypeId(
        entity.getBalanceTypeId());
    updateSpecGroup.setAccountAndBalanceId(
        entity.getAccountAndBalanceId());
    updateSpecGroup.setGroupMemo(
        entity.getGroupMemo());
    updateSpecGroup.setDeleted(
        entity.getDeleted());
    updateSpecGroup.setEntryDate(
        entity.getEntryDate());
    updateSpecGroup.setUpdateDate(
        entity.getUpdateDate());
    updateSpecGroup.setVersion(
        entity.getVersion());

    entityManager.merge(updateSpecGroup);
  }
}
