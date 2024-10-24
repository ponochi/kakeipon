package org.panda.systems.kakeipon.app.spec;

import jakarta.persistence.*;
import lombok.Data;
import org.panda.systems.kakeipon.app.common.TaxRateForm;
import org.panda.systems.kakeipon.app.common.TaxTypeForm;
import org.panda.systems.kakeipon.app.common.UnitForm;
import org.panda.systems.kakeipon.app.currency.CurrencyListForm;
import org.panda.systems.kakeipon.domain.model.common.TaxRate;
import org.panda.systems.kakeipon.domain.model.common.TaxType;
import org.panda.systems.kakeipon.domain.model.common.Unit;
import org.panda.systems.kakeipon.domain.model.currency.CurrencyList;
import org.panda.systems.kakeipon.domain.model.spec.Specification;
import org.panda.systems.kakeipon.domain.service.common.TaxRateService;
import org.panda.systems.kakeipon.domain.service.common.TaxTypeService;
import org.panda.systems.kakeipon.domain.service.common.UnitService;
import org.panda.systems.kakeipon.domain.service.currency.CurrencyListService;
import org.panda.systems.kakeipon.domain.service.spec.SpecificationService;
import org.panda.systems.kakeipon.domain.service.users.UsersDetailService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Table(name = "specification")
@SecondaryTable(name = "specification_group",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "specification_group_id"))
@SecondaryTable(name = "users",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id"))
@SecondaryTable(name = "currency",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "currency_id"))
@SecondaryTable(name = "unit",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "unit_id"))
@SecondaryTable(name = "tax_type",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "tax_type_id"))
@SecondaryTable(name = "tax_rate",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "tax_rate_id"))
@Data
public class SpecificationForm implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Autowired
  UsersDetailService usersDetailService;
  @Autowired
  CurrencyListService currencyListService;
  @Autowired
  UnitService unitService;
  @Autowired
  TaxTypeService taxTypeService;
  @Autowired
  TaxRateService taxRateService;

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "specification_group_seq", allocationSize = 1)
  @Column(name = "specification_group_id")
  private Long specificationGroupId;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "specification_seq", allocationSize = 1)
  @Column(name = "specification_id")
  private Long specificationId;

  @Column(name = "user_id")
  private Long userId;

  @Column
  private String name;
  @Column
  private Long price;

  @Column(name = "currency_id")
  private Long currencyId;

  @ManyToOne
  @JoinColumn(name = "currency_id", table = "currency",
      insertable = false, updatable = false)
  @PrimaryKeyJoinColumn
  @Column(name = "currency_id")
  private CurrencyListForm currencyListForm;

  private Long unitId;

  @ManyToOne
  @JoinColumn(name = "unit_id", table = "unit",
      insertable = false, updatable = false)
  @PrimaryKeyJoinColumn
  @Column(name = "unit_id")
  private UnitForm unitForm;

  @Column
  private Long quantity;
  @Column(name = "tax_type_id")
  private Long taxTypeId;

  @ManyToOne
  @JoinColumn(name = "tax_type_id", table = "tax_type",
      insertable = false, updatable = false)
  @PrimaryKeyJoinColumn
  @Column(name = "tax_type_id")
  private TaxTypeForm taxTypeForm;

  @Column(name = "tax_rate_id")
  private Long taxRateId;

  @ManyToOne
  @JoinColumn(name = "tax_rate_id", table = "tax_rate",
      insertable = false, updatable = false)
  @PrimaryKeyJoinColumn
  @Column(name = "tax_rate_id")
  private TaxRateForm taxRateForm;

  @Column(name = "tax")
  private Long tax;

  @Column(name = "spec_memo")
  private String specMemo;

  @Column(name = "deleted")
  private Boolean deleted;

  @Column(name = "entry_date")
  private LocalDateTime entryDate;

  @Column(name = "update_date")
  private LocalDateTime updateDate;

  @Column
  private Long version;

  public SpecificationForm() {
  }

  public SpecificationForm(SpecificationService service,
                           Long specificationGroupId) {
    if (specificationGroupId == null) {
      this.setSpecificationGroupId(Long.parseLong("1"));
    } else {
      this.setSpecificationGroupId(specificationGroupId);
    }

    Specification specification
        = new Specification();

    this.setSpecificationId(specification.getSpecificationId());
    this.setUserId(specification.getUserId());
    this.setName(specification.getName());
    this.setPrice(0L);
    this.setCurrencyId(specification.getCurrencyId());
    this.setUnitId(specification.getUnitId());
    this.setQuantity(Long.parseLong("1"));
    this.setTaxTypeId(specification.getTaxTypeId());
    this.setTaxRateId(specification.getTaxRateId());
    this.setTax(0L);
    this.setSpecMemo(specification.getSpecMemo());
    this.setDeleted(specification.getDeleted());
    this.setEntryDate(specification.getEntryDate());
    this.setUpdateDate(specification.getUpdateDate());
    this.setVersion(0L);
  }

  public SpecificationForm setSpecificationToForm(Specification specification) {
    SpecificationForm form = new SpecificationForm();

    form.setSpecificationGroupId(
        specification.getSpecificationGroupId());
    form.setSpecificationId(specification.getSpecificationId());

    form.setUserId(specification.getUserId());

    form.setName(specification.getName());
    form.setPrice(specification.getPrice());
    form.setCurrencyId(specification.getCurrencyId());
    form.setUnitId(specification.getUnitId());
    form.setQuantity(specification.getQuantity());
    form.setTaxTypeId(specification.getTaxTypeId());
    form.setTaxRateId(specification.getTaxRateId());
    form.setTax(specification.getTax());
    form.setSpecMemo(specification.getSpecMemo());
    form.setDeleted(specification.getDeleted());
    form.setEntryDate(specification.getEntryDate());
    form.setUpdateDate(specification.getUpdateDate());
    form.setVersion(specification.getVersion());

    return form;
  }

  public Specification toEntity() {
    Specification specification = new Specification();

    specification.setSpecificationGroupId(this.getSpecificationGroupId());
    specification.setSpecificationId(this.getSpecificationId());
    specification.setUserId(this.getUserId());
    specification.setName(this.getName());
    specification.setPrice(this.getPrice());
    specification.setCurrencyId(this.getCurrencyId());
    specification.setUnitId(this.getUnitId());
    specification.setQuantity(this.getQuantity());
    specification.setTaxTypeId(this.getTaxTypeId());
    specification.setTaxRateId(this.getTaxRateId());
    specification.setTax(this.getTax());
    specification.setSpecMemo(this.getSpecMemo());
    specification.setDeleted(this.getDeleted());
    specification.setEntryDate(this.getEntryDate());
    specification.setUpdateDate(this.getUpdateDate());
    specification.setVersion(this.getVersion());

    return specification;
  }

  // Setter for Users
  public CurrencyListForm setCurrencyToForm(CurrencyList currencyList) {
    this.currencyListForm = new CurrencyListForm();

    this.currencyListForm.setCurrencyId(currencyList.getCurrencyId());
    this.currencyListForm.setCurrencyName(currencyList.getCurrencyName());
    return this.currencyListForm;
  }

  public UnitForm setUnitToForm(Unit unit) {
    this.unitForm = new UnitForm();

    this.unitForm.setUnitId(unit.getUnitId());
    this.unitForm.setUnitName(unit.getUnitName());
    return this.unitForm;
  }

  public TaxTypeForm setTaxTypeToForm(TaxType taxType) {
    this.taxTypeForm = new TaxTypeForm();

    this.taxTypeForm.setTaxTypeId(taxType.getTaxTypeId());
    this.taxTypeForm.setTaxTypeName(taxType.getTaxTypeName());
    return this.taxTypeForm;
  }

  public TaxRateForm setTaxRateToForm(TaxRate taxRate) {
    this.taxRateForm = new TaxRateForm();

    this.taxRateForm.setTaxRateId(taxRate.getTaxRateId());
    this.taxRateForm.setTaxRate(taxRate.getTaxRate());
    return this.taxRateForm;
  }
}
