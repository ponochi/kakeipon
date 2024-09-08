package org.panda.systems.kakeipon.app.spec;

import jakarta.persistence.*;
import lombok.Data;
import org.panda.systems.kakeipon.app.common.TaxRateForm;
import org.panda.systems.kakeipon.app.currency.CurrencyListForm;
import org.panda.systems.kakeipon.app.common.TaxTypeForm;
import org.panda.systems.kakeipon.app.common.UnitForm;
import org.panda.systems.kakeipon.domain.model.common.TaxRate;
import org.panda.systems.kakeipon.domain.model.currency.CurrencyList;
import org.panda.systems.kakeipon.domain.model.common.TaxType;
import org.panda.systems.kakeipon.domain.model.common.Unit;
import org.panda.systems.kakeipon.domain.model.spec.Specification;
import org.panda.systems.kakeipon.domain.service.common.TaxRateService;
import org.panda.systems.kakeipon.domain.service.common.TaxTypeService;
import org.panda.systems.kakeipon.domain.service.common.UnitService;
import org.panda.systems.kakeipon.domain.service.currency.CurrencyListService;
import org.panda.systems.kakeipon.domain.service.user.RoleService;
import org.panda.systems.kakeipon.domain.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Table(name = "tbl_specification")
@SecondaryTable(name = "tbl_specification_group",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "specification_group_id"))
@SecondaryTable(name = "tbl_user",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id"))
@SecondaryTable(name = "tbl_currency",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "currency_id"))
@SecondaryTable(name = "tbl_unit",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "unit_id"))
@SecondaryTable(name = "tbl_tax_type",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "tax_type_id"))
@SecondaryTable(name = "tbl_tax_rate",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "tax_rate_id"))
@Data
public class SpecificationForm implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Autowired
  UserService userService;
  @Autowired
  RoleService roleService;
  @Autowired
  CurrencyListService currencyListService;
  @Autowired
  UnitService unitService;
  @Autowired
  TaxTypeService taxTypeService;
  @Autowired
  TaxRateService taxRateService;

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "tbl_specification_group_seq", allocationSize = 1)
  @Column(name = "specification_group_id")
  private Long specificationGroupId;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "tbl_specification_seq", allocationSize = 1)
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
  @JoinColumn(name = "currency_id", table = "tbl_currency",
      insertable = false, updatable = false)
  @PrimaryKeyJoinColumn
  @Column(name = "currency_id")
  CurrencyListForm currencyForm;

  private Long unitId;

  @ManyToOne
  @JoinColumn(name = "unit_id", table = "tbl_unit",
      insertable = false, updatable = false)
  @PrimaryKeyJoinColumn
  @Column(name = "unit_id")
  UnitForm unitForm;

  @Column
  private Long quantity;
  @Column(name = "tax_type_id")
  private Long taxTypeId;

  @ManyToOne
  @JoinColumn(name = "tax_type_id", table = "tbl_tax_type",
      insertable = false, updatable = false)
  @PrimaryKeyJoinColumn
  @Column(name = "tax_type_id")
  TaxTypeForm taxTypeForm;

  @Column(name = "tax_rate_id")
  private Long taxRateId;

  @ManyToOne
  @JoinColumn(name = "tax_rate_id", table = "tbl_tax_rate",
      insertable = false, updatable = false)
  @PrimaryKeyJoinColumn
  @Column(name = "tax_rate_id")
  TaxRateForm taxRateForm;

  @Column
  private Long tax;
  @Column
  private String memo;
  @Column(name = "entry_date")
  private LocalDateTime entryDate;
  @Column(name = "update_date")
  private LocalDateTime updateDate;

  public SpecificationForm() {
  }

  public SpecificationForm setSpecificationToForm(org.panda.systems.kakeipon.domain.model.spec.Specification specification) {
    SpecificationForm form = new SpecificationForm();

    form.specificationGroupId
        = specification.getSpecificationGroupId();
    form.specificationId = specification.getSpecificationId();

    form.userId = specification.getUserId();

    form.name = specification.getName();
    form.price = specification.getPrice();
    form.currencyId = specification.getCurrencyId();
//    CurrencyList currencyList = currencyListService.findById(
//        specification.getCurrencyId());
//    form.currencyForm = setCurrencyToForm(currencyList);
    form.unitId = specification.getUnitId();
//    Unit unit = unitService.findById(specification.getUnitId());
//    form.unitForm = setUnitToForm(unit);
    form.quantity = specification.getQuantity();
    form.taxTypeId = specification.getTaxTypeId();
//    TaxType taxType
//        = taxTypeService.findById(specification.getTaxTypeId());
//    form.taxTypeForm = setTaxTypeToForm(taxType);
    form.taxRateId = specification.getTaxRateId();
    form.tax = specification.getTax();
    form.memo = memo;
    form.entryDate = entryDate;
    form.updateDate = updateDate;

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
    specification.setMemo(this.getMemo());
    specification.setEntryDate(this.getEntryDate());
    specification.setUpdateDate(this.getUpdateDate());
    return specification;
  }

  // Setter for User
  public CurrencyListForm setCurrencyToForm(CurrencyList currency) {
    this.currencyForm = new CurrencyListForm();

    this.currencyForm.setCurrencyId(currency.getCurrencyId());
    this.currencyForm.setCurrencyName(currency.getCurrencyName());
    return this.currencyForm;
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
