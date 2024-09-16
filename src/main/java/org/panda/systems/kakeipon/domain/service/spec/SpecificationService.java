package org.panda.systems.kakeipon.domain.service.spec;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import org.panda.systems.kakeipon.app.spec.SpecificationForm;
import org.panda.systems.kakeipon.domain.model.spec.Specification;
import org.panda.systems.kakeipon.domain.repository.spec.SpecificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service
public class SpecificationService implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Autowired
  private SpecificationRepository specificationRepository;
  @Autowired
  private EntityManager entityManager;

  public Specification findBySpecificationGroupIdAndSpecificationIdAndUserIdAndDeleted(
      Long specificationGroupId, Long specificationId, Long userId, Boolean deleted) {

    return specificationRepository.findBySpecificationGroupIdAndSpecificationIdAndUserIdAndDeleted(
        specificationGroupId, specificationId, userId, deleted);
  }

  public Long getMaxSpecificationId(Long specificationGroupId) {
    return specificationRepository.getMaxId(specificationGroupId);
  }

  public List<Specification> findAll() {
    return specificationRepository.findAll();
  }

  public List<Specification> findBySpecificationGroupIdAndUserIdAndDeleted(Long specificationGroupId, Long userId, Boolean deleted) {
    return specificationRepository.findBySpecificationGroupIdAndUserIdAndDeleted(specificationGroupId, userId, deleted);
  }

  public List<SpecificationForm> findBySpecificationGroupIdToForm(Long specificationGroupId, Long userId, Boolean deleted) {
    List<Specification> specs = specificationRepository.findBySpecificationGroupIdAndUserIdAndDeleted(specificationGroupId, userId, deleted);
    SpecificationForm specForm = new SpecificationForm();

    List<SpecificationForm> forms = new ArrayList<>();
    for (Specification specification : specs) {
      forms.add(
          specForm.setSpecificationToForm(specification));
    }
    return forms;
  }

  public Specification findBySpecificationGroupIdAndSpecificationId(Long specificationGroupId, Long specificationId, Long userId, Boolean deleted) {
    return specificationRepository.findBySpecificationGroupIdAndSpecificationIdAndUserIdAndDeleted(specificationGroupId, specificationId, userId, deleted);
  }

  @Lock(LockModeType.OPTIMISTIC)
  @Transactional
  public void saveAndFlushSpecification(Specification entity) {
    if (entity.getSpecificationId() == null) {
      entity.setDeleted(false);
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
  @Transactional
  public void saveAllSpecifications(List<Specification> entity) {
    for (Specification specification : entity) {
      this.saveAndFlushSpecification(specification);
    }
  }
}
