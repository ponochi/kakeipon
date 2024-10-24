package org.panda.systems.kakeipon.app.spec;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import org.panda.systems.kakeipon.app.common.AccountAndBalanceForm;
import org.panda.systems.kakeipon.app.common.BalanceTypeForm;
import org.panda.systems.kakeipon.app.shop.ShopForm;
import org.panda.systems.kakeipon.app.users.UsersExtForm;
import org.panda.systems.kakeipon.app.users.UsersForm;
import org.panda.systems.kakeipon.domain.model.account.AccountDestination;
import org.panda.systems.kakeipon.domain.model.account.AccountSource;
import org.panda.systems.kakeipon.domain.model.common.AccountAndBalance;
import org.panda.systems.kakeipon.domain.model.common.BalanceType;
import org.panda.systems.kakeipon.domain.model.common.Shop;
import org.panda.systems.kakeipon.domain.model.spec.Specification;
import org.panda.systems.kakeipon.domain.model.spec.SpecificationGroup;
import org.panda.systems.kakeipon.domain.model.users.Users;
import org.panda.systems.kakeipon.domain.model.users.UsersExt;
import org.panda.systems.kakeipon.domain.service.account.AccountDestinationService;
import org.panda.systems.kakeipon.domain.service.account.AccountSourceService;
import org.panda.systems.kakeipon.domain.service.common.*;
import org.panda.systems.kakeipon.domain.service.currency.CurrencyListService;
import org.panda.systems.kakeipon.domain.service.spec.SpecificationGroupService;
import org.panda.systems.kakeipon.domain.service.spec.SpecificationService;
import org.panda.systems.kakeipon.domain.service.users.UsersDetail;
import org.panda.systems.kakeipon.domain.service.users.UsersDetailService;
import org.panda.systems.kakeipon.domain.service.users.UsersExtService;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Table(name = "specification_group")
@SecondaryTable(name = "users",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id"))
@SecondaryTable(name = "usersExt",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "username"))
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

  private final SpecificationGroupService specificationGroupService;
  private final SpecificationService specificationService;
  private final AccountAndBalanceService accountAndBalanceService;
  private final AccountSourceService accountSourceService;
  private final AccountDestinationService accountDestinationService;
  private final UsersDetailService usersDetailService;
  private final UsersExtService usersExtService;
  private final ShopService shopService;
  private final BalanceTypeService balanceTypeService;
  private final CurrencyListService currencyListService;
  private final UnitService unitService;
  private final TaxTypeService taxTypeService;
  private final TaxRateService taxRateService;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "specification_group_seq", allocationSize = 1)
  @Column(name = "specification_group_id")
  Long specificationGroupId;

  @Column(name = "user_id")
  Long userId;

  @ManyToOne
  @JoinColumn(name = "user_id", table = "users_ext",
      insertable = false, updatable = false)
  @PrimaryKeyJoinColumn
  @Column(name = "user_id")
  UsersExtForm usersExtForm;

  @Column(name = "username")
  String username;

  @OneToOne
  @JoinColumn(name = "username", table = "users",
      insertable = false, updatable = false)
  @PrimaryKeyJoinColumn
  @Column(name = "username")
  UsersDetail usersDetail;

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

    this.specificationGroupService = null;
    this.specificationService = null;
    this.accountAndBalanceService = null;
    this.accountSourceService = null;
    this.accountDestinationService = null;
    this.usersDetailService = null;
    this.usersExtService = null;
    this.shopService = null;
    this.balanceTypeService = null;
    this.currencyListService = null;
    this.unitService = null;
    this.taxTypeService = null;
    this.taxRateService = null;
  }

  public SpecificationGroupForm(SpecificationGroupService specificationGroupService,
                                SpecificationService specificationService,
                                AccountAndBalanceService accountAndBalanceService,
                                AccountSourceService accountSourceService,
                                AccountDestinationService accountDestinationService,
                                UsersDetailService usersDetailService,
                                UsersExtService usersExtService,
                                ShopService shopService,
                                BalanceTypeService balanceTypeService,
                                CurrencyListService currencyListService,
                                UnitService unitService,
                                TaxTypeService taxTypeService,
                                TaxRateService taxRateService) {

    this.specificationGroupService = specificationGroupService;
    this.specificationService = specificationService;
    this.accountAndBalanceService = accountAndBalanceService;
    this.accountSourceService = accountSourceService;
    this.accountDestinationService = accountDestinationService;
    this.usersDetailService = usersDetailService;
    this.usersExtService = usersExtService;
    this.shopService = shopService;
    this.balanceTypeService = balanceTypeService;
    this.currencyListService = currencyListService;
    this.unitService = unitService;
    this.taxTypeService = taxTypeService;
    this.taxRateService = taxRateService;
  }

  public SpecificationGroupForm setDefaultValuesOfSpecificationGroupForm(String id) {

    AccountAndBalanceForm accountAndBalanceForm
        = new AccountAndBalanceForm(
        accountAndBalanceService,
        accountSourceService,
        accountDestinationService)
        .setAccountAndBalanceFormAndAccountSourceFormAndAccountDestinationForm(
            null,
            Long.parseLong("1"),
            Long.parseLong("1"));
    if (usersDetailService != null) {
      Users users = usersDetailService.findByUserId(userId);
    }
    this.setUsername(id);
    this.setUsersToForm(
        usersDetailService,
        usersDetail.getUsers());

    UsersExt usersExt = null;
    if (usersExtService != null) {
      usersExt = usersExtService.findByUserId(userId);
    }
    if (usersExt != null) {
      this.setUserExtToForm(usersExt);
    }

    ShopForm shopForm = new ShopForm(shopService);
    shopForm.setShopId(Long.parseLong("1"));
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

    if (accountAndBalanceService != null) {
      accountAndBalanceService.saveAndFlush(
          this.getAccountAndBalanceForm().toEntity());
    }

    this.setGroupMemo("");
    this.setDeleted(false);

    if (specificationGroupService != null) {
      specificationGroupService
          .saveAndFlushSpecificationGroup(
              this.toEntity());
    }

    SpecificationForm specForm = new SpecificationForm();

    if (usersDetailService != null) {
      Users usersTemporary = usersDetailService.findByUserId(userId);
      List<Specification> specifications = null;
      if (specificationService != null) {
        if (specificationGroupService != null) {
          specifications = specificationService
          .findBySpecificationGroupIdAndUserIdAndDeleted(
              specificationGroupService.getMaxGroupId(),
              usersTemporary.getUserId(),
              false);
        }
      }

      if (specifications != null && !specifications.isEmpty()) {
        Long count = Long.parseLong("1");
        for (Specification specification : specifications) {
          if (specificationGroupService != null) {
            specForm.setSpecificationGroupId(
                specificationGroupService.getMaxGroupId());
            specForm.setSpecificationId(count);
            specForm.setUserId(this.getUserId());
            specForm.setName("");
            specForm.setPrice(0L);
            specForm.setCurrencyId(Long.parseLong("1"));
            specForm.setQuantity(Long.parseLong("1"));
            specForm.setUnitId(Long.parseLong("1"));
            specForm.setTaxTypeId(Long.parseLong("1"));
            specForm.setTaxRateId(Long.parseLong("1"));
            specForm.setTax(0L);
            specForm.setSpecMemo("");
            specForm.setDeleted(false);
            specForm.setEntryDate(LocalDateTime.now());
            specForm.setVersion(0L);
          }
        }

        if (specificationService != null) {
          specificationService.saveAndFlushSpecification(specForm.toEntity());
        }

        count++;
      }
    }

    return this;
  }

  public SpecificationGroupForm setSpecificationGroupFormByDB(
      Long specificationGroupId,
      Long userId,
      Boolean deleted) {

    if (specificationGroupId == null) {
      this.setSpecificationGroupId(null);
      this.setUsername(username);
      this.setShopId(Long.parseLong("1"));
      this.setBalanceTypeId(Long.parseLong("1"));
      this.setDeleted(false);
      this.setEntryDate(LocalDateTime.now());
    } else {
      this.setSpecificationGroupId(specificationGroupId);

      SpecificationGroup group
          = null;
      if (specificationGroupService != null) {
        group = specificationGroupService
            .findBySpecificationGroupIdAndUserIdAndDeleted(
        specificationGroupId, userId, false);

        this.setUserId(group.getUserId());
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

    return this;
  }

  public SpecificationGroupForm setSpecificationGroupToForm(
      SpecificationGroup specificationGroup) {

    SpecificationGroupForm groupForm = new SpecificationGroupForm(
        specificationGroupService,
        specificationService,
        accountAndBalanceService,
        accountSourceService,
        accountDestinationService,
        usersDetailService,
        usersExtService,
        shopService,
        balanceTypeService,
        currencyListService,
        unitService,
        taxTypeService,
        taxRateService);

    groupForm.setSpecificationGroupId(
        specificationGroup.getSpecificationGroupId());

    groupForm.setUserId(specificationGroup.getUserId());
    Users users
        = null;
    if (usersDetailService != null) {
      users = usersDetailService
          .findByUserId(specificationGroup.getUserId());
    }
    UsersDetail usersDetail
        = new UsersDetail(users);
    if (users != null) {
      usersDetail.getUsers().setUsername(users.getUsername());
      usersDetail.getUsers().setPassword(users.getPassword());
      usersDetail.getUsers().setUserId(users.getUserId());
      usersDetail.getUsers().setUsername(users.getUsername());
      usersDetail.getUsers().setAccountNonExpired(true);
      usersDetail.getUsers().setAccountNonLocked(true);
      usersDetail.getUsers().setCredentialsNonExpired(true);
      usersDetail.getUsers().setEnabled(true);
      groupForm.setUsersDetail(usersDetail);
    }

    UsersExt usersExt = new UsersExt();
    if (users != null) {
      usersExt.setUserId(users.getUserId());
      usersExt.setLastName(usersExt.getLastName());
      usersExt.setFirstName(usersExt.getFirstName());
      usersExt.setBirthDay(usersExt.getBirthDay());
      usersExt.setPhoneNumber(usersExt.getPhoneNumber());
      usersExt.setEmail(usersExt.getEmail());
      usersExt.setEntryDate(usersExt.getEntryDate());
      usersExt.setUpdateDate(usersExt.getUpdateDate());
      UsersExtForm usersExtForm = groupForm.setUserExtToForm(usersExt);
      groupForm.setUsersExtForm(usersExtForm);
    }

    groupForm.setShopId(specificationGroup.getShopId());
    Shop shop = null;
    if (shopService != null) {
      shop = shopService.findById(specificationGroup.getShopId());
      shopForm = groupForm.setShopToForm(shop);
      groupForm.setShopForm(shopForm);
    }

    groupForm.setReceivingAndPaymentDate(
        specificationGroup.getReceivingAndPaymentDate());
    groupForm.setReceivingAndPaymentTime(
        specificationGroup.getReceivingAndPaymentTime());

    groupForm.setBalanceTypeId(specificationGroup.getBalanceTypeId());
    BalanceType balanceType
        = null;
    if (balanceTypeService != null) {
      balanceType = balanceTypeService.findById(
          specificationGroup.getBalanceTypeId());
      balanceTypeForm = groupForm.setBalanceTypeToForm(balanceType);
      groupForm.setBalanceTypeForm(balanceTypeForm);
    }

    AccountAndBalanceForm accountAndBalanceForm
        = new AccountAndBalanceForm(
        accountAndBalanceService,
        accountSourceService,
        accountDestinationService);
    groupForm.setAccountAndBalanceForm(accountAndBalanceForm);

    groupForm.setAccountAndBalanceId(
        specificationGroup.getAccountAndBalanceId());
    AccountAndBalance accountAndBalance
        = null;
    if (accountAndBalanceService != null) {
      accountAndBalance = accountAndBalanceService.getById(
          specificationGroup.getAccountAndBalanceId());
      accountAndBalanceForm = groupForm.setAccountAndBalanceToForm(accountAndBalance);
      groupForm.setAccountAndBalanceForm(accountAndBalanceForm);
    }

    groupForm.setGroupMemo(specificationGroup.getGroupMemo());

    groupForm.setDeleted(specificationGroup.getDeleted());

    if (specificationGroup.getEntryDate() == null) {
      groupForm.setEntryDate(LocalDateTime.now());
      groupForm.setUpdateDate(specificationGroup.getUpdateDate());
    } else {
      groupForm.setEntryDate(specificationGroup.getEntryDate());
      groupForm.setUpdateDate(LocalDateTime.now());
    }
    groupForm.setVersion(specificationGroup.getVersion());

    return groupForm;
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

  public UsersForm setUsersToForm(
      UsersDetailService usersDetailService,
      Users users) {
    UsersForm usersForm = new UsersForm(
        usersDetailService
    );

    usersForm.setUserId(users.getUserId());
    usersForm.setUsername(users.getUsername());
    usersForm.setPassword(users.getPassword());
    usersForm.setEnabled(true);
    usersForm.setAccountNonExpired(true);
    usersForm.setAccountNonLocked(true);
    usersForm.setCredentialsNonExpired(true);

    return usersForm;
  }

  public UsersExtForm setUserExtToForm(UsersExt usersExt) {

    UsersExtForm usersExtForm = new UsersExtForm(usersExtService);

    usersExtForm.setUserId(usersExt.getUserId());
    usersExtForm.setLastName(usersExt.getLastName());
    usersExtForm.setFirstName(usersExt.getFirstName());
    usersExtForm.setBirthDay(usersExt.getBirthDay());
    usersExtForm.setPhoneNumber(usersExt.getPhoneNumber());
    usersExtForm.setEmail(usersExt.getEmail());
    if (entryDate == null) {
      usersExtForm.setEntryDate(LocalDateTime.now());
      usersExtForm.setUpdateDate(usersExt.getUpdateDate());
    } else {
      usersExtForm.setEntryDate(usersExt.getEntryDate());
      usersExtForm.setUpdateDate(LocalDateTime.now());
    }

    return usersExtForm;
  }

  public ShopForm setShopToForm(Shop shop) {
    this.shopForm = new ShopForm(shopService);

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
