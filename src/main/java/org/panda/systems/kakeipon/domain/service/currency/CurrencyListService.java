package org.panda.systems.kakeipon.domain.service.currency;

import org.panda.systems.kakeipon.domain.model.currency.CurrencyList;
import org.panda.systems.kakeipon.domain.repository.currency.CurrencyListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Service
public class CurrencyListService implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Autowired
  CurrencyListRepository currencyListRepository;

  public List<CurrencyList> findAll() {
    return currencyListRepository.findAll();
  }

  public CurrencyList findById(Long currencyId) {
    return currencyListRepository.getById(currencyId);
  }

  public CurrencyList saveAndFlush(CurrencyList entity) {
    return currencyListRepository.saveAndFlush(entity);
  }
}
