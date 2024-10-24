package org.panda.systems.kakeipon.domain.service.spec;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import org.panda.systems.kakeipon.app.spec.SpecificationForm;
import org.panda.systems.kakeipon.domain.model.spec.Specification;
import org.panda.systems.kakeipon.domain.repository.spec.SpecificationRepository;
import org.panda.systems.kakeipon.domain.service.users.UsersDetailService;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service
public class SpecificationService implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  private final SpecificationRepository specificationRepository;
  private final EntityManager entityManager;
  private final SpecificationTransactionalService specificationTransactionalService;
  private final UsersDetailService usersDetailService;

  public SpecificationService(
      SpecificationRepository specificationRepository,
      SpecificationTransactionalService specificationTransactionalService,
      EntityManager entityManager, UsersDetailService usersDetailService
  ) {

    this.specificationRepository = specificationRepository;
    this.specificationTransactionalService = specificationTransactionalService;
    this.entityManager = entityManager;
    this.usersDetailService = usersDetailService;
  }

  public Long getMaxSpecificationId(Long specificationGroupId) {
    return specificationRepository.getNextId(specificationGroupId);
  }

  public List<Specification> findAll() {
    return specificationRepository.findAll();
  }

  public List<Specification> findBySpecificationGroupIdAndIdAndDeleted(
      Long specificationGroupId, Long userId, Boolean deleted) {
    return specificationRepository
        .findBySpecificationGroupIdAndUserIdAndDeletedOrderBySpecificationIdDesc(
            specificationGroupId,
            userId,
            deleted);
  }

  public List<Specification> findBySpecificationGroupIdAndUserIdAndDeleted(
      Long specificationGroupId, Long userId, Boolean deleted) {
    return specificationRepository
        .findBySpecificationGroupIdAndUserIdAndDeletedOrderBySpecificationIdDesc(
            specificationGroupId,
            userId,
            deleted);
  }

  public List<SpecificationForm> findBySpecificationGroupIdOrderBySpecificationIdDescToForm(
      Long specificationGroupId, Long userId, Boolean deleted) {
    List<Specification> specs
        = specificationRepository
        .findBySpecificationGroupIdAndUserIdAndDeletedOrderBySpecificationIdDesc(
            specificationGroupId,
            userId,
            deleted);
    SpecificationForm specForm = new SpecificationForm();

    List<SpecificationForm> forms = new ArrayList<>();
    for (Specification specification : specs) {
      forms.add(
          specForm.setSpecificationToForm(specification));
    }
    return forms;
  }

  public Specification findBySpecificationGroupIdAndSpecificationIdAndUsernameAndDeleted(
      Long specificationGroupId,
      Long specificationId,
      String username,
      Boolean deleted) {
    return specificationRepository
        .findBySpecificationGroupIdAndSpecificationIdAndUserIdAndDeleted(
            specificationGroupId,
            specificationId,
            usersDetailService.findByUsername(username).getUsers().getUserId(),
            deleted);
  }

  // Transactional
  @Lock(LockModeType.OPTIMISTIC)
  public void saveAndFlushSpecification(Specification entity) {
    try {
      specificationTransactionalService
          .saveAndFlushSpecification(entity);
    } catch (Exception e) {
      System.out.println("saveAndFlushSpecification exception");
    }
  }

  // Transactional
  @Lock(LockModeType.OPTIMISTIC)
  public void saveAndFlushSpecificationDelete(Specification entity) {
    try {
      entity.setDeleted(true);
      specificationTransactionalService
          .saveAndFlushSpecification(entity);
    } catch (Exception e) {
      System.out.println("saveAndFlushSpecificationDelete exception");
    }
  }

  // Transactional
  @Lock(LockModeType.OPTIMISTIC)
  public void saveAllSpecifications(List<Specification> entity) {
    try {
      specificationTransactionalService
          .saveAllSpecifications(entity);
    } catch (Exception e) {
      System.out.println("saveAllSpecifications exception");
    }
  }

  // Transactional
  @Lock(LockModeType.OPTIMISTIC)
  public void saveAllSpecificationsDelete(List<Specification> entity) {
    try {
      specificationTransactionalService
          .saveAllSpecificationsDelete(entity);
    } catch (Exception e) {
      System.out.println("saveAllSpecificationsDelete exception");
    }
  }
}
