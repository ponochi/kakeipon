package org.panda.systems.kakeipon.domain.service.spec;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import org.panda.systems.kakeipon.domain.model.spec.Specification;
import org.panda.systems.kakeipon.domain.repository.spec.SpecificationRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class SpecificationTransactionalService implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  private final SpecificationRepository specificationRepository;
  private final EntityManager entityManager;

  public SpecificationTransactionalService(
      SpecificationRepository specificationRepository,
      EntityManager entityManager) {

    this.specificationRepository = specificationRepository;
    this.entityManager = entityManager;
  }

  public void saveAndFlushSpecification(Specification entity) {

    if (entity.getSpecificationId() == null) {
      specificationRepository.saveAndFlush(entity);
    } else {
      Specification saveAndFlushSpecification
          = entityManager.find(
          Specification.class, entity.getSpecificationId());
      entityManager.lock(
          saveAndFlushSpecification, LockModeType.OPTIMISTIC);
      saveAndFlushSpecification.setSpecificationGroupId(
          entity.getSpecificationGroupId());
      saveAndFlushSpecification.setUserId(
          entity.getUserId());
      saveAndFlushSpecification.setName(
          entity.getName());
      saveAndFlushSpecification.setPrice(
          entity.getPrice());
      saveAndFlushSpecification.setCurrencyId(
          entity.getCurrencyId());
      saveAndFlushSpecification.setQuantity(
          entity.getQuantity());
      saveAndFlushSpecification.setUnitId(
          entity.getUnitId());
      saveAndFlushSpecification.setTaxTypeId(
          entity.getTaxTypeId());
      saveAndFlushSpecification.setTaxRateId(
          entity.getTaxRateId());
      saveAndFlushSpecification.setTax(
          entity.getTax());
      saveAndFlushSpecification.setSpecMemo(
          entity.getSpecMemo());
      saveAndFlushSpecification.setDeleted(
          entity.getDeleted());
      saveAndFlushSpecification.setEntryDate(
          entity.getEntryDate());
      saveAndFlushSpecification.setUpdateDate(
          entity.getUpdateDate());
      saveAndFlushSpecification.setVersion(
          entity.getVersion());

      entityManager.merge(saveAndFlushSpecification);
    }
  }

  @Lock(LockModeType.OPTIMISTIC)
  public void saveAllSpecifications(List<Specification> entity) {
    for (Specification spec : entity) {
      specificationRepository.saveAndFlush(spec);
    }
  }

  @Lock(LockModeType.OPTIMISTIC)
  public void saveAllSpecificationsDelete(List<Specification> entity) {
    for (Specification spec : entity) {
      spec.setDeleted(true);
      specificationRepository.saveAndFlush(spec);
    }
  }
}
