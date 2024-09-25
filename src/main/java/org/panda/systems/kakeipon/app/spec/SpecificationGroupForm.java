package org.panda.systems.kakeipon.app.spec;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import org.panda.systems.kakeipon.app.common.AccountAndBalanceForm;
import org.panda.systems.kakeipon.app.common.BalanceTypeForm;
import org.panda.systems.kakeipon.app.shop.ShopForm;
import org.panda.systems.kakeipon.app.user.UserExtForm;
import org.panda.systems.kakeipon.app.user.UserForm;
import org.panda.systems.kakeipon.domain.model.account.AccountDestination;
import org.panda.systems.kakeipon.domain.model.account.AccountSource;
import org.panda.systems.kakeipon.domain.model.common.*;
import org.panda.systems.kakeipon.domain.model.spec.Specification;
import org.panda.systems.kakeipon.domain.model.spec.SpecificationGroup;
import org.panda.systems.kakeipon.domain.model.user.User;
import org.panda.systems.kakeipon.domain.model.user.UserExt;
import org.panda.systems.kakeipon.domain.service.account.AccountDestinationService;
import org.panda.systems.kakeipon.domain.service.account.AccountSourceService;
import org.panda.systems.kakeipon.domain.service.common.*;
import org.panda.systems.kakeipon.domain.service.currency.CurrencyListService;
import org.panda.systems.kakeipon.domain.service.spec.SpecificationGroupService;
import org.panda.systems.kakeipon.domain.service.spec.SpecificationService;
import org.panda.systems.kakeipon.domain.service.user.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Table(name = "specification_group")
@SecondaryTable(name = "users",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "id"))
@SecondaryTable(name = "usersExt",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "id"))
@SecondaryTable(name = "shop",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "shop_id"))
@SecondaryTable(name = "balance_type",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "balance_type_id"))
@SecondaryTable(name = "account_and_balance",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "account_and_balance_id"))
@SecondaryTable(name = "account_info",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "account_source_id"))
@SecondaryTable(name = "account_info",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "account_destination_id"))
@Data
public class SpecificationGroupForm implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "specification_group_seq", allocationSize = 1)
  @Column(name = "specification_group_id")
  Long specificationGroupId;

  @Column(name = "id")
  Integer id;

  @ManyToOne
  @JoinColumn(name = "id", table = "users_ext",
      insertable = false, updatable = false)
  @PrimaryKeyJoinColumn
  @Column(name = "id")
  UserExtForm userExtForm;

  @Column(name = "username")
  String username;

  @OneToOne
  @JoinColumn(name = "username", table = "users",
      insertable = false, updatable = false)
  @PrimaryKeyJoinColumn
  @Column(name = "username")
  KakeiPonUsersDetails kakeiPonUsersDetails;

  @Column(name = "shop_id")
  Long shopId;

  @OneToOne
  @JoinColumn(name = "shop_id", table = "shop",
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

  @JoinColumn(name = "balance_type_id", table = "balance_type",
      insertable = false, updatable = false)
  @PrimaryKeyJoinColumn
  @Column(name = "balance_type_id")
  BalanceTypeForm balanceTypeForm;

  @Column(name = "account_and_balance_id")
  Long accountAndBalanceId;

  @OneToOne
  @JoinColumn(name = "account_and_balance_id", table = "account_and_balance",
      insertable = false, updatable = false)
  @Column(name = "account_and_balance_id")
  AccountAndBalanceForm accountAndBalanceForm;

  @Column(name = "group_memo")
  String groupMemo;

  @Column(name = "deleted")
  Boolean deleted;

  @PastOrPresent
  @Column(name = "entry_date")
  LocalDateTime entryDate;

  @Column(name = "update_date")
  LocalDateTime updateDate;

  @Version
  @Column(name = "version")
  Long version;

  // Default Constructor
  public SpecificationGroupForm() {

  }

  public SpecificationGroupForm(SpecificationGroupService specificationGroupService,
                                SpecificationService specificationService,
                                AccountAndBalanceService accountAndBalanceService,
                                AccountSourceService accountSourceService,
                                AccountDestinationService accountDestinationService,
                                KakeiPonUsersDetailsService kakeiPonUsersService,
                                AuthoritiesService authoritiesService,
                                UserExtService userExtService,
                                ShopService shopService,
                                BalanceTypeService balanceTypeService,
                                CurrencyListService currencyListService,
                                UnitService unitService,
                                TaxTypeService taxTypeService,
                                TaxRateService taxRateService,
                                Integer id) {

    AccountAndBalanceForm accountAndBalanceForm
        = new AccountAndBalanceForm(
        accountAndBalanceService,
        accountSourceService,
        accountDestinationService,
        null,
        Long.parseLong("1"),
        Long.parseLong("1"));
    User user = kakeiPonUsersService.findById(id);
    this.setId(id);
    this.setUsersToForm(
        kakeiPonUsersService,
        authoritiesService,
        user);

    UserExt userExt = userExtService.findById(id);
    this.setUserExtToForm(userExt);

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

    this.setGroupMemo("");
    this.setDeleted(false);

    specificationGroupService
        .saveAndFlushSpecificationGroup(
            this.toEntity());

    SpecificationForm specForm = new SpecificationForm();

    User userTemporary = kakeiPonUsersService.findById(id);
    List<Specification> specifications
        = specificationService
        .findBySpecificationGroupIdAndUsernameAndDeleted(
            specificationGroupService.getMaxGroupId(),
            userTemporary.getUsername(),
            false);
    if (specifications.size() > 0) {
      Long count = Long.parseLong("1");
      for (Specification specification : specifications) {
        specForm.setSpecificationGroupId(
            specificationGroupService.getMaxGroupId());
        specForm.setSpecificationId(count);
        specForm.setId(this.getId());
        specForm.setName("");
        specForm.setPrice(Long.parseLong("0"));
        specForm.setCurrencyId(Long.parseLong("1"));
        specForm.setQuantity(Long.parseLong("1"));
        specForm.setUnitId(Long.parseLong("1"));
        specForm.setTaxTypeId(Long.parseLong("1"));
        specForm.setTaxRateId(Long.parseLong("1"));
        specForm.setTax(Long.parseLong("0"));
        specForm.setSpecMemo("");
        specForm.setDeleted(false);
        specForm.setEntryDate(LocalDateTime.now());
        specForm.setVersion(Long.parseLong("0"));

        specificationService.saveAndFlushSpecification(specForm.toEntity());

        count++;
      }
    }
  }

  public SpecificationGroupForm(SpecificationGroupService service,
                                Long specificationGroupId,
                                String username,
//                                Integer id,
                                Boolean deleted) {
    if (specificationGroupId == null) {
      this.setSpecificationGroupId(null);
      this.setId(id);
      this.setShopId(Long.parseLong("1"));
      this.setBalanceTypeId(Long.parseLong("1"));
      this.setDeleted(false);
      this.setEntryDate(LocalDateTime.now());
    } else {
      this.setSpecificationGroupId(specificationGroupId);

      SpecificationGroup group
          = service.findBySpecificationGroupIdAndUsernameAndDeleted(
              specificationGroupId, username, false);

      this.setId(group.getId());
      this.setShopId(group.getShopId());
      this.setReceivingAndPaymentDate(group.getReceivingAndPaymentDate());
      this.setReceivingAndPaymentTime(group.getReceivingAndPaymentTime());
      this.setBalanceTypeId(group.getBalanceTypeId());
      this.setAccountAndBalanceId(group.getAccountAndBalanceId());
      this.setGroupMemo(group.getGroupMemo());
      this.setDeleted(group.getDeleted());
      if (group.getEntryDate() == null) {
        this.setEntryDate(LocalDateTime.now());
        this.setUpdateDate(group.getUpdateDate());
      } else {
        this.setEntryDate(group.getEntryDate());
        this.setUpdateDate(LocalDateTime.now());
      }
      this.setVersion(group.getVersion());
    }
  }

  public SpecificationGroupForm setSpecificationGroupToForm(
      KakeiPonUsersDetailsService kakeiPonUserDetailsService,
      ShopService shopService,
      BalanceTypeService balanceTypeService,
      AccountAndBalanceService accountAndBalanceService,
      AccountSourceService accountSourceService,
      AccountDestinationService accountDestinationService,
      SpecificationGroup specificationGroup) {

    SpecificationGroupForm form = new SpecificationGroupForm();

    form.setSpecificationGroupId(
        specificationGroup.getSpecificationGroupId());

    form.setId(specificationGroup.getId());
    User user
        = kakeiPonUserDetailsService
        .findById(specificationGroup.getId());
    KakeiPonUsersDetails kakeiPonUserDetails
        = new KakeiPonUsersDetails();
    kakeiPonUserDetails.setId(user.getId());
    kakeiPonUserDetails.setPassword(user.getPassword());
    kakeiPonUserDetails.setUsername(user.getUsername());
    kakeiPonUserDetails.setAccountNonExpired(true);
    kakeiPonUserDetails.setAccountNonLocked(true);
    kakeiPonUserDetails.setCredentialsNonExpired(true);
    kakeiPonUserDetails.setEnabled(true);
    form.setKakeiPonUsersDetails(kakeiPonUserDetails);

    UserExt userExt = new UserExt();
    userExt.setId(user.getId());
    userExt.setLastName(userExt.getLastName());
    userExt.setFirstName(userExt.getFirstName());
    userExt.setBirthDay(userExt.getBirthDay());
    userExt.setPhoneNumber(userExt.getPhoneNumber());
    userExt.setEmail(userExt.getEmail());
    userExt.setEntryDate(userExt.getEntryDate());
    userExt.setUpdateDate(userExt.getUpdateDate());
    UserExtForm userExtForm = form.setUserExtToForm(userExt);
    form.setUserExtForm(userExtForm);

    form.setShopId(specificationGroup.getShopId());
    Shop shop = shopService.findById(specificationGroup.getShopId());
    shopForm = form.setShopToForm(shop);
    form.setShopForm(shopForm);

    form.setReceivingAndPaymentDate(
        specificationGroup.getReceivingAndPaymentDate());
    form.setReceivingAndPaymentTime(
        specificationGroup.getReceivingAndPaymentTime());

    form.setBalanceTypeId(specificationGroup.getBalanceTypeId());
    BalanceType balanceType
        = balanceTypeService.findById(
        specificationGroup.getBalanceTypeId());
    balanceTypeForm = form.setBalanceTypeToForm(balanceType);
    form.setBalanceTypeForm(balanceTypeForm);

    form.setAccountAndBalanceId(
        specificationGroup.getAccountAndBalanceId());
    AccountAndBalance accountAndBalance
        = accountAndBalanceService.getById(
        specificationGroup.getAccountAndBalanceId());
    accountAndBalanceForm = form.setAccountAndBalanceToForm(accountAndBalance);
    form.setAccountAndBalanceForm(accountAndBalanceForm);

    form.setGroupMemo(specificationGroup.getGroupMemo());

    form.setDeleted(specificationGroup.getDeleted());

    if (specificationGroup.getEntryDate() == null) {
      form.setEntryDate(LocalDateTime.now());
      form.setUpdateDate(specificationGroup.getUpdateDate());
    } else {
      form.setEntryDate(specificationGroup.getEntryDate());
      form.setUpdateDate(LocalDateTime.now());
    }
    form.setVersion(specificationGroup.getVersion());

    return form;
  }

  public SpecificationGroup toEntity() {
    SpecificationGroup specificationGroup = new SpecificationGroup();

    specificationGroup.setSpecificationGroupId(
        this.getSpecificationGroupId());
    specificationGroup.setId(this.getId());
    specificationGroup.setShopId(this.getShopId());
    specificationGroup.setReceivingAndPaymentDate(
        this.getReceivingAndPaymentDate());
    specificationGroup.setReceivingAndPaymentTime(
        this.getReceivingAndPaymentTime());
    specificationGroup.setBalanceTypeId(this.getBalanceTypeId());
    specificationGroup.setAccountAndBalanceId(
        this.getAccountAndBalanceId());
    specificationGroup.setGroupMemo(this.getGroupMemo());
    specificationGroup.setDeleted(this.getDeleted());
    if (this.getEntryDate() == null) {
      specificationGroup.setEntryDate(LocalDateTime.now());
      specificationGroup.setUpdateDate(this.getUpdateDate());
    } else {
      specificationGroup.setEntryDate(this.getEntryDate());
      specificationGroup.setUpdateDate(LocalDateTime.now());
    }
    specificationGroup.setVersion(this.getVersion());

    return specificationGroup;
  }

  public UserForm setUsersToForm(
      KakeiPonUsersDetailsService kakeiPonUsersService,
      AuthoritiesService authoritiesService,
      User user) {
    UserForm userForm = new UserForm(
        kakeiPonUsersService,
        authoritiesService
    );

    userForm.setId(user.getId());
    userForm.setUsername(user.getUsername());
    userForm.setPassword(user.getPassword());
    userForm.setEnabled(true);
    userForm.setAccountNonExpired(true);
    userForm.setAccountNonLocked(true);
    userForm.setCredentialsNonExpired(true);


    return userForm;
  }

  public UserExtForm setUserExtToForm(UserExt userExt) {
    UserExtForm userExtForm = new UserExtForm();

    userExtForm.setId(userExt.getId());
    userExtForm.setLastName(userExt.getLastName());
    userExtForm.setFirstName(userExt.getFirstName());
    userExtForm.setBirthDay(userExt.getBirthDay());
    userExtForm.setPhoneNumber(userExt.getPhoneNumber());
    userExtForm.setEmail(userExt.getEmail());
    if (entryDate == null) {
      userExtForm.setEntryDate(LocalDateTime.now());
      userExtForm.setUpdateDate(userExt.getUpdateDate());
    } else {
      userExtForm.setEntryDate(userExt.getEntryDate());
      userExtForm.setUpdateDate(LocalDateTime.now());
    }

    return userExtForm;
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
