package org.panda.systems.kakeipon.app.spec;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import org.panda.systems.kakeipon.app.common.AccountAndBalanceForm;
import org.panda.systems.kakeipon.app.common.BalanceTypeForm;
import org.panda.systems.kakeipon.app.shop.ShopForm;
import org.panda.systems.kakeipon.app.user.RoleForm;
import org.panda.systems.kakeipon.app.user.UserForm;
import org.panda.systems.kakeipon.domain.model.account.AccountDestination;
import org.panda.systems.kakeipon.domain.model.account.AccountSource;
import org.panda.systems.kakeipon.domain.model.common.*;
import org.panda.systems.kakeipon.domain.model.currency.CurrencyList;
import org.panda.systems.kakeipon.domain.model.spec.Specification;
import org.panda.systems.kakeipon.domain.model.spec.SpecificationGroup;
import org.panda.systems.kakeipon.domain.service.account.AccountDestinationService;
import org.panda.systems.kakeipon.domain.service.account.AccountSourceService;
import org.panda.systems.kakeipon.domain.service.common.*;
import org.panda.systems.kakeipon.domain.service.currency.CurrencyListService;
import org.panda.systems.kakeipon.domain.service.spec.SpecificationGroupService;
import org.panda.systems.kakeipon.domain.service.spec.SpecificationService;
import org.panda.systems.kakeipon.domain.service.user.RoleService;
import org.panda.systems.kakeipon.domain.service.user.UserService;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Table(name = "tbl_specification_group")
@SecondaryTable(name = "tbl_user",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id"))
@SecondaryTable(name = "tbl_shop",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "shop_id"))
@SecondaryTable(name = "tbl_balance_type",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "balance_type_id"))
@SecondaryTable(name = "tbl_account_and_balance",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "account_and_balance_id"))
@SecondaryTable(name = "tbl_account_info",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "account_source_id"))
@SecondaryTable(name = "tbl_account_info",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "account_destination_id"))
@Data
public class SpecificationGroupForm implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "tbl_specification_group_seq", allocationSize = 1)
  @Column(name = "specification_group_id")
  Long specificationGroupId;

  @Column(name = "user_id")
  Long userId;

  @ManyToOne
  @JoinColumn(name = "user_id", table = "tbl_user",
      insertable = false, updatable = false)
  @PrimaryKeyJoinColumn
  @Column(name = "user_id")
  UserForm userForm;

  @Column(name = "shop_id")
  Long shopId;

  @OneToOne
  @JoinColumn(name = "shop_id", table = "tbl_shop",
      insertable = false, updatable = false)
  @PrimaryKeyJoinColumn
  @Column(name = "shop_id")
  ShopForm shopForm;

  @PastOrPresent
  @Column
  LocalDate receivingAndPaymentDate;

  @Column
  LocalTime receivingAndPaymentTime;

  @Column(name = "balance_type_id")
  Long balanceTypeId;

  @JoinColumn(name = "balance_type_id", table = "tbl_balance_type",
      insertable = false, updatable = false)
  @PrimaryKeyJoinColumn
  @Column(name = "balance_type_id")
  BalanceTypeForm balanceTypeForm;

  @Column(name = "account_and_balance_id")
  Long accountAndBalanceId;

  @OneToOne
  @JoinColumn(name = "account_and_balance_id", table = "tbl_account_and_balance",
      insertable = false, updatable = false)
  @Column(name = "account_and_balance_id")
  AccountAndBalanceForm accountAndBalanceForm;

  @Column
  String memo;

  @PastOrPresent
  @Column(name = "entry_date")
  LocalDateTime entryDate;

  @Column(name = "update_date")
  LocalDateTime updateDate;

  // Default Constructor
  public SpecificationGroupForm() {

  }

  public SpecificationGroupForm(SpecificationGroupService specificationGroupService,
                                SpecificationService specificationService,
                                AccountAndBalanceService accountAndBalanceService,
                                AccountSourceService accountSourceService,
                                AccountDestinationService accountDestinationService,
                                UserService userService,
                                RoleService roleService,
                                ShopService shopService,
                                BalanceTypeService balanceTypeService,
                                CurrencyListService currencyListService,
                                UnitService unitService,
                                TaxTypeService taxTypeService,
                                TaxRateService taxRateService) {
//    SpecificationGroupForm form = new SpecificationGroupForm();

    AccountAndBalanceForm accountAndBalanceForm
        = new AccountAndBalanceForm(
        accountAndBalanceService,
        accountSourceService,
        accountDestinationService,
        null,
        Long.parseLong("1"),
        Long.parseLong("1"));
    UserForm userForm = new UserForm(
        userService,
        roleService,
        Long.parseLong("2"));
    this.setUserId(userForm.getUserId());
    this.setUserForm(userForm);
    this.setUserToForm(
        roleService,
        userForm.getUserId(),
        userForm.getNickName(),
        userForm.getLastName(),
        userForm.getFirstName(),
        userForm.getBirthDay(),
        userForm.getPassword(),
        userForm.getPhoneNumber(),
        userForm.getEmail(),
        userForm.getRoleForm().getRoleId(),
        userForm.getEntryDate(),
        userForm.getUpdateDate());

    this.getUserForm().setRoleId(
        this.getUserForm().getRoleForm().getRoleId());

    ShopForm shopForm = new ShopForm(shopService,
        Long.parseLong("1"));
    this.setShopId(shopForm.getShopId());
    this.setShopForm(shopForm);

    this.setReceivingAndPaymentDate(LocalDate.now());
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH");

    LocalTime localTime = LocalTime.now();
    String nowHour = formatter.format(localTime);

    if (nowHour.length() == 1) {
      nowHour = "0" + nowHour + ":00";
    } else {
      nowHour = nowHour + ":00";
    }
    this.setReceivingAndPaymentTime(LocalTime.parse(nowHour));

    this.setBalanceTypeId(Long.parseLong("1"));
    List<BalanceType> balanceTypes
        = balanceTypeService.findAll();
    this.setBalanceTypeToForm(balanceTypes.get(0));

    BalanceTypeForm balanceTypeForm = new BalanceTypeForm();
    balanceTypeForm.setBalanceTypeId(Long.parseLong("1"));
    this.setBalanceTypeForm(balanceTypeForm);

    AccountAndBalance accountAndBalance
        = accountAndBalanceService.getById(
        accountAndBalanceService.getMaxAccountAndBalanceId());
    this.setAccountAndBalanceId(
        accountAndBalance.getAccountAndBalanceId()
    );
    this.setAccountAndBalanceForm(
        accountAndBalanceForm.setAccountAndBalanceToForm(
            accountAndBalance));

    AccountSource accountSource
        = accountSourceService.findById(
        accountAndBalance.getAccountSourceId());
    this.getAccountAndBalanceForm().setAccountSourceForm(
        accountAndBalanceForm.setAccountSourceToForm(
            accountSource));

    AccountDestination accountDestination
        = accountDestinationService.findById(
        accountAndBalance.getAccountDestinationId());
    this.getAccountAndBalanceForm().setAccountDestinationForm(
        accountAndBalanceForm.setAccountDestinationToForm(
            accountDestination));

    accountAndBalanceService.saveAndFlush(
        this.getAccountAndBalanceForm().toEntity());

    // ToDo: Fix this
    specificationGroupService.saveAndFlush(this.toEntity());

    SpecificationForm specForm = new SpecificationForm();

    List<Specification> specifications
        = specificationService.findBySpecificationGroupId(
        specificationGroupService.getMaxGroupId());
    if (specifications.size() > 0) {
      Long count = 1L;
      for (Specification specification : specifications) {
        specForm.setSpecificationGroupId(
            specificationGroupService.getMaxGroupId());
        specForm.setSpecificationId(count);
        specForm.setUserId(this.getUserId());
        specForm.setName("");
        specForm.setPrice(Long.parseLong("0"));
        specForm.setCurrencyId(Long.parseLong("1"));
        specForm.setQuantity(Long.parseLong("1"));
        specForm.setUnitId(Long.parseLong("1"));
        specForm.setTaxTypeId(Long.parseLong("1"));
        specForm.setTaxRateId(Long.parseLong("1"));
        specForm.setTax(Long.parseLong("0"));
        specForm.setMemo("");
        specForm.setEntryDate(LocalDateTime.now());

        specificationService.saveAndFlush(specForm.toEntity());
        count++;
      }
    }
  }

  public SpecificationGroupForm(SpecificationGroupService service,
                                Long specificationGroupId) {
    if (specificationGroupId == null) {
      this.setSpecificationGroupId(Long.parseLong("0"));
    } else {
      this.setSpecificationGroupId(specificationGroupId);
    }

    SpecificationGroup group = service.findById(this.getSpecificationGroupId());
    this.setUserId(group.getUserId());
    this.setShopId(group.getShopId());
    this.setReceivingAndPaymentDate(group.getReceivingAndPaymentDate());
    this.setReceivingAndPaymentTime(group.getReceivingAndPaymentTime());
    this.setBalanceTypeId(group.getBalanceTypeId());
    this.setAccountAndBalanceId(group.getAccountAndBalanceId());
    this.setMemo(group.getMemo());
    if (group.getEntryDate() == null) {
      this.setEntryDate(LocalDateTime.now());
      this.setUpdateDate(group.getUpdateDate());
    } else {
      this.setEntryDate(group.getEntryDate());
      this.setUpdateDate(LocalDateTime.now());
    }
  }

  public SpecificationGroupForm setSpecificationGroupToForm(
      AccountAndBalanceService accountAndBalanceService,
      AccountSourceService accountSourceService,
      AccountDestinationService accountDestinationService,
      SpecificationGroup specificationGroup) {

    SpecificationGroupForm form = new SpecificationGroupForm();

    form.setSpecificationGroupId(
        specificationGroup.getSpecificationGroupId());

    form.setUserId(specificationGroup.getUserId());

    form.setShopId(specificationGroup.getShopId());
    form.setReceivingAndPaymentDate(
        specificationGroup.getReceivingAndPaymentDate());
    form.setReceivingAndPaymentTime(
        specificationGroup.getReceivingAndPaymentTime());
    form.setBalanceTypeId(specificationGroup.getBalanceTypeId());
    form.setAccountAndBalanceId(
        specificationGroup.getAccountAndBalanceId());
    AccountAndBalance accountAndBalance
        = accountAndBalanceService.getById(
        specificationGroup.getAccountAndBalanceId());
    accountAndBalanceForm = form.setAccountAndBalanceToForm(accountAndBalance);
    form.setAccountAndBalanceForm(accountAndBalanceForm);
    form.setMemo(specificationGroup.getMemo());
    if (specificationGroup.getEntryDate() == null) {
      form.setEntryDate(LocalDateTime.now());
      form.setUpdateDate(specificationGroup.getUpdateDate());
    } else {
      form.setEntryDate(specificationGroup.getEntryDate());
      form.setUpdateDate(LocalDateTime.now());
    }

    return form;
  }

  public SpecificationGroup toEntity() {
    SpecificationGroup specificationGroup = new SpecificationGroup();

    specificationGroup.setSpecificationGroupId(
        this.getSpecificationGroupId());
    specificationGroup.setUserId(this.getUserId());
    specificationGroup.setShopId(this.getShopId());
    specificationGroup.setReceivingAndPaymentDate(
        this.getReceivingAndPaymentDate());
    specificationGroup.setReceivingAndPaymentTime(
        this.getReceivingAndPaymentTime());
    specificationGroup.setBalanceTypeId(this.getBalanceTypeId());
    specificationGroup.setAccountAndBalanceId(
        this.getAccountAndBalanceId());
    specificationGroup.setMemo(this.getMemo());
    if (this.getEntryDate() == null) {
      specificationGroup.setEntryDate(LocalDateTime.now());
      specificationGroup.setUpdateDate(this.getUpdateDate());
    } else {
      specificationGroup.setEntryDate(this.getEntryDate());
      specificationGroup.setUpdateDate(LocalDateTime.now());
    }

    return specificationGroup;
  }

  // Setter for User
  public UserForm setUserToForm(RoleService service,
                                Long userId,
                                String nickName,
                                String lastName,
                                String firstName,
                                LocalDateTime birthDay,
                                String password,
                                String phoneNumber,
                                String email,
                                Long roleId,
//                                String roleName,
                                LocalDateTime entryDate,
                                LocalDateTime updateDate) {
    this.userForm = new UserForm();

    this.userForm.setUserId(userId);
    this.userForm.setNickName(nickName);
    this.userForm.setLastName(lastName);
    this.userForm.setFirstName(firstName);
    this.userForm.setBirthDay(birthDay);
    this.userForm.setPassword(password);
    this.userForm.setPhoneNumber(phoneNumber);
    this.userForm.setEmail(email);
    this.userForm.setRoleForm(new RoleForm(service, roleId));
    this.userForm.getRoleForm().setRoleId(roleId);
//    this.userForm.getRoleForm().setRoleName(roleName);
    if (entryDate == null) {
      this.userForm.setEntryDate(LocalDateTime.now());
      this.userForm.setUpdateDate(updateDate);
    } else {
      this.userForm.setEntryDate(entryDate);
      this.userForm.setUpdateDate(LocalDateTime.now());
    }

    return this.userForm;
  }

  public ShopForm setShopToForm(Shop shop) {
    this.shopForm = new ShopForm();

    this.shopForm.setShopId(shop.getShopId());
    this.shopForm.setShopName(shop.getShopName());
    this.shopForm.setBranchName(shop.getBranchName());
    this.shopForm.setPhoneNumber(shop.getPhoneNumber());
    this.shopForm.setEmail(shop.getEmail());
    this.shopForm.setShopUrl(shop.getShopUrl());
    this.shopForm.setOpenShopDate(shop.getOpenShopDate());
    this.shopForm.setCloseShopDate(shop.getCloseShopDate());
    this.shopForm.setShopMemo(shop.getShopMemo());
    if (shop.getEntryDate() == null) {
      this.shopForm.setEntryDate(LocalDateTime.now());
      this.shopForm.setUpdateDate(shop.getUpdateDate());
    } else {
      this.shopForm.setEntryDate(shop.getEntryDate());
      this.shopForm.setUpdateDate(LocalDateTime.now());
    }

    return this.shopForm;
  }

  public BalanceTypeForm setBalanceTypeToForm(BalanceType balanceType) {
    this.balanceTypeForm = new BalanceTypeForm();

    this.balanceTypeForm.setBalanceTypeId(balanceType.getBalanceTypeId());
    this.balanceTypeForm.setBalanceTypeName(balanceType.getBalanceTypeName());

    return this.balanceTypeForm;
  }

  public AccountAndBalanceForm setAccountAndBalanceToForm(
      AccountAndBalance accountAndBalance) {
    this.accountAndBalanceForm = new AccountAndBalanceForm();

    this.accountAndBalanceForm.setAccountAndBalanceId(accountAndBalance.getAccountAndBalanceId());
    this.accountAndBalanceForm.setAccountSourceId(accountAndBalance.getAccountSourceId());
    this.accountAndBalanceForm.setAccountDestinationId(accountAndBalance.getAccountDestinationId());
    if (accountAndBalance.getEntryDate() == null) {
      this.accountAndBalanceForm.setEntryDate(LocalDateTime.now());
      this.accountAndBalanceForm.setUpdateDate(accountAndBalance.getUpdateDate());
    } else {
      this.accountAndBalanceForm.setEntryDate(accountAndBalance.getEntryDate());
      this.accountAndBalanceForm.setUpdateDate(LocalDateTime.now());
    }

    return this.accountAndBalanceForm;
  }
}
