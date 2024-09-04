package org.panda.systems.kakeipon.domain.service.currency;

import org.panda.systems.kakeipon.domain.model.currency.Currency;
import org.panda.systems.kakeipon.domain.repository.currency.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Service
public class CurrencyService implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  @Autowired
  CurrencyRepository currencyRepository;

  public List<Currency> findAll() {
    return currencyRepository.findAll();
  }

  public Currency findById(Long currencyId) {
    return currencyRepository.getById(currencyId);
  }

  public Currency saveAndFlush(Currency entity) {
    return currencyRepository.saveAndFlush(entity);
  }
}
