package org.panda.systems.kakeipon.app.spec;

import jakarta.persistence.*;
import lombok.Data;
import org.panda.systems.kakeipon.app.currency.CurrencyForm;
import org.panda.systems.kakeipon.app.common.TaxTypeForm;
import org.panda.systems.kakeipon.app.common.UnitForm;
import org.panda.systems.kakeipon.app.user.RoleForm;
import org.panda.systems.kakeipon.app.user.UserForm;
import org.panda.systems.kakeipon.domain.model.currency.Currency;
import org.panda.systems.kakeipon.domain.model.common.TaxType;
import org.panda.systems.kakeipon.domain.model.common.Unit;
import org.panda.systems.kakeipon.domain.model.spec.Specification;
import org.panda.systems.kakeipon.domain.service.currency.CurrencyService;
import org.panda.systems.kakeipon.domain.service.common.TaxTypeService;
import org.panda.systems.kakeipon.domain.model.user.User;
import org.panda.systems.kakeipon.domain.service.common.UnitService;
import org.panda.systems.kakeipon.domain.service.user.RoleService;
import org.panda.systems.kakeipon.domain.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
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
@Data
public class SpecificationForm implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Autowired
  UserService userService;
  @Autowired
  RoleService roleService;
  @Autowired
  CurrencyService currencyService;
  @Autowired
  UnitService unitService;
  @Autowired
  TaxTypeService taxTypeService;

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

  private String name;
  private BigDecimal price;

  private Long currencyId;

  @ManyToOne
  @JoinColumn(name = "currency_id", table = "tbl_currency",
      insertable = false, updatable = false)
  @PrimaryKeyJoinColumn
  @Column(name = "currency_id")
  CurrencyForm currencyForm;

  private Long unitId;

  @ManyToOne
  @JoinColumn(name = "unit_id", table = "tbl_unit",
      insertable = false, updatable = false)
  @PrimaryKeyJoinColumn
  @Column(name = "unit_id")
  UnitForm unitForm;

  private Long quantity;
  private Long taxTypeId;

  @ManyToOne
  @JoinColumn(name = "tax_type_id", table = "tbl_tax_type",
      insertable = false, updatable = false)
  @PrimaryKeyJoinColumn
  @Column(name = "tax_type_id")
  TaxTypeForm taxTypeForm;

  private String taxRate;
  private BigDecimal tax;
  private String memo;
  private LocalDateTime entryDate;
  private LocalDateTime updateDate;

  public SpecificationForm() {
  }

  public SpecificationForm setSpecificationToForm(Specification specification) {
    SpecificationForm form = new SpecificationForm();

    form.specificationGroupId
        = specification.getSpecificationGroupId();
    form.specificationId = specification.getSpecificationId();

    form.userId = specification.getUserId();

    form.name = specification.getName();
    form.price = specification.getPrice();
    form.currencyId = specification.getCurrencyId();
    Currency currency = currencyService.findById(specification.getCurrencyId());
    form.currencyForm = setCurrencyToForm(currency);
    form.unitId = specification.getUnitId();
    Unit unit = unitService.findById(specification.getUnitId());
    form.unitForm = setUnitToForm(unit);
    form.quantity = specification.getQuantity();
    form.taxTypeId = specification.getTaxTypeId();
    TaxType taxType
        = taxTypeService.findById(specification.getTaxTypeId());
    form.taxTypeForm = setTaxTypeToForm(taxType);
    form.tax = specification.getTax();
    form.memo = memo;
    form.entryDate = entryDate;
    form.updateDate = updateDate;

    return form;
  }

  public Specification toEntity(SpecificationForm specificationForm) {
    Specification specification = new Specification();
    specification.setSpecificationGroupId(specificationForm.getSpecificationGroupId());
    specification.setSpecificationId(specificationForm.getSpecificationId());
    specification.setUserId(specificationForm.getUserId());
    specification.setName(specificationForm.getName());
    specification.setPrice(specificationForm.getPrice());
    specification.setCurrencyId(specificationForm.getCurrencyId());
    specification.setUnitId(specificationForm.getUnitId());
    specification.setQuantity(specificationForm.getQuantity());
    specification.setTaxTypeId(specificationForm.getTaxTypeId());
    specification.setTaxTypeId(specificationForm.getTaxTypeId());
    specification.setMemo(specificationForm.getMemo());
    specification.setEntryDate(specificationForm.getEntryDate());
    specification.setUpdateDate(specificationForm.getUpdateDate());
    return specification;
  }

  // Setter for User
  public CurrencyForm setCurrencyToForm(Currency currency) {
    this.currencyForm = new CurrencyForm();

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
    this.taxTypeForm.setTaxRate(taxType.getTaxRate());
    return this.taxTypeForm;
  }
}
