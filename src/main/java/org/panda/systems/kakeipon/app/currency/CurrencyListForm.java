package org.panda.systems.kakeipon.app.currency;

import jakarta.persistence.*;
import lombok.Data;
import org.panda.systems.kakeipon.domain.model.currency.CurrencyList;
import org.panda.systems.kakeipon.domain.service.currency.CurrencyListService;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;

@Component
@Table(name = "currency")
@Data
public class CurrencyListForm implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  private final CurrencyListService currencyListService;

  @Id
  @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
  @SequenceGenerator(name = "currency_seq", allocationSize = 1)
  @Column(name = "currency_id")
  private Long currencyId;

  @Column(name = "currency_name")
  private String currencyName;

  public CurrencyListForm() {

    this.currencyListService = null;
  }

  public CurrencyListForm(CurrencyListService currencyListService) {

    this.currencyListService = currencyListService;
  }

  public CurrencyListForm(CurrencyListService currencyListService,
                          Long currencyId) {
    this.currencyListService = currencyListService;
    CurrencyList currency = currencyListService.findById(currencyId);

    this.setCurrencyId(currency.getCurrencyId());
    this.setCurrencyName(currency.getCurrencyName());
  }

  public CurrencyListForm setCurrencyListToForm(CurrencyList currency) {
    this.setCurrencyId(currency.getCurrencyId());
    this.setCurrencyName(currency.getCurrencyName());

    return this;
  }
}
