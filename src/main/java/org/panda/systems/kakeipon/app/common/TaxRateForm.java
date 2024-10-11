package org.panda.systems.kakeipon.app.common;

import jakarta.persistence.*;
import lombok.Data;
import org.panda.systems.kakeipon.domain.model.common.TaxRate;
import org.panda.systems.kakeipon.domain.service.common.TaxRateService;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Table(name = "tax_rate")
@Data
public class TaxRateForm implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  private final TaxRateService taxRateService;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "tax_rate_seq", allocationSize = 1)
  @Column(name = "tax_rate_id")
  private Long taxRateId;

  @Column(name = "tax_rate")
  private Long taxRate;

  // Default constructor
  public TaxRateForm() {

    this.taxRateService = null;
  }

  public TaxRateForm(TaxRateService taxRateService) {

    this.taxRateService = taxRateService;
  }

  public TaxRateForm setTaxRateFormByDB(Long taxRateId) {

    TaxRate taxRate = taxRateService.findById(taxRateId);

    this.setTaxRateId(taxRate.getTaxRateId());
    this.setTaxRate(taxRate.getTaxRate());

    return this;
  }
}
