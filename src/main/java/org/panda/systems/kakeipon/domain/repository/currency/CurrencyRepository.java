package org.panda.systems.kakeipon.domain.repository.currency;

import org.panda.systems.kakeipon.domain.model.currency.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
  @SuppressWarnings({"NullableProblems"})
  @Override
  List<Currency> findAll();

  @SuppressWarnings({"NullableProblems"})
  @Override
  Currency getById(Long currencyId);

  @Override
  Currency saveAndFlush(Currency entity);
}
