package org.panda.systems.kakeipon.domain.repository.currency;

import org.panda.systems.kakeipon.domain.model.currency.CurrencyList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CurrencyListRepository extends JpaRepository<CurrencyList, Long> {
  @Override
  List<CurrencyList> findAll();

  @SuppressWarnings("NullableProblems")
  @Override
  Optional<CurrencyList> findById(Long id);

  @Override
  CurrencyList saveAndFlush(CurrencyList entity);
}
