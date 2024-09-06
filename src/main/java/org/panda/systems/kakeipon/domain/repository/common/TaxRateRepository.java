package org.panda.systems.kakeipon.domain.repository.common;

import org.panda.systems.kakeipon.domain.model.common.TaxRate;
import org.panda.systems.kakeipon.domain.model.common.TaxType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaxRateRepository extends JpaRepository<TaxRate, Long> {

    @Override
    List<TaxRate> findAll();

    @Override
    TaxRate getById(Long taxRateId);

    TaxRate saveAndFlush(TaxRate taxRate);
}