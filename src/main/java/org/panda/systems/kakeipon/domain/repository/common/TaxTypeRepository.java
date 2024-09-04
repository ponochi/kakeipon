package org.panda.systems.kakeipon.domain.repository.common;

import org.panda.systems.kakeipon.domain.model.common.TaxType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaxTypeRepository extends JpaRepository<TaxType, Long> {

    @Override
    List<TaxType> findAll();

    @Override
    TaxType getById(Long taxTypeId);

    TaxType saveAndFlush(TaxType taxType);
}