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

    private final SpecificationRepository specificationRepository;
    private final EntityManager entityManager;

    public SpecificationService(SpecificationRepository specificationRepository, EntityManager entityManager) {
        this.specificationRepository = specificationRepository;
        this.entityManager = entityManager;
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
                .findBySpecificationGroupIdAndUserIdAndDeleted(
                        specificationGroupId,
                        userId,
                        deleted);
    }

    public List<Specification> findBySpecificationGroupIdAndUserIdAndDeleted(
            Long specificationGroupId, Long userId, Boolean deleted) {
        return specificationRepository
                .findBySpecificationGroupIdAndUserIdAndDeleted(
                        specificationGroupId,
                        userId,
                        deleted);
    }

    public List<SpecificationForm> findBySpecificationGroupIdToForm(
            Long specificationGroupId, Long userId, Boolean deleted) {
        List<Specification> specs
                = specificationRepository
                .findBySpecificationGroupIdAndUserIdAndDeleted(
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

    public Specification findBySpecificationGroupIdAndSpecificationIdAndIdAndDeleted(
            Long specificationGroupId,
            Long specificationId,
            String id,
            Boolean deleted) {
        return specificationRepository.findBySpecificationGroupIdAndSpecificationIdAndUserIdAndDeleted(specificationGroupId, specificationId, id, deleted);
    }

    @Lock(LockModeType.OPTIMISTIC)
//    @Transactional
    public void saveAndFlushSpecification(Specification entity) {
        entity.setDeleted(false);
        if (entity.getSpecificationId() == null) {
            specificationRepository.saveAndFlush(entity);
        } else {
//            specificationRepository.update(
//                    entity.getSpecificationGroupId(),
//                    entity.getSpecificationId(),
//                    entity.getUserId(),
//                    entity.getName(),
//                    entity.getPrice(),
//                    entity.getCurrencyId(),
//                    entity.getQuantity(),
//                    entity.getUnitId(),
//                    entity.getTaxTypeId(),
//                    entity.getTaxRateId(),
//                    entity.getTax(),
//                    entity.getSpecMemo(),
//                    entity.getDeleted(),
//                    entity.getEntryDate(),
//                    entity.getUpdateDate()
//            );
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
//    @Transactional
    public void saveAllSpecifications(List<Specification> entity) {
        for (Specification specification : entity) {
            this.saveAndFlushSpecification(specification);
        }
    }
}
