package org.panda.systems.kakeipon.app.spec;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import org.panda.systems.kakeipon.app.common.AccountAndBalanceForm;
import org.panda.systems.kakeipon.app.common.BalanceTypeForm;
import org.panda.systems.kakeipon.app.shop.ShopForm;
import org.panda.systems.kakeipon.app.user.RoleForm;
import org.panda.systems.kakeipon.app.user.UserForm;
import org.panda.systems.kakeipon.domain.model.common.*;
import org.panda.systems.kakeipon.domain.model.spec.SpecificationGroup;
import org.panda.systems.kakeipon.domain.service.spec.SpecificationGroupService;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

  public SpecificationGroupForm(SpecificationGroupService service,
                                Long specificationGroupId) {
    if (specificationGroupId == null) {
      this.setSpecificationGroupId(Long.parseLong("0"));
    } else {
      this.setSpecificationGroupId(specificationGroupId);
    }
    Optional<SpecificationGroup> spec = service.findById(this.getSpecificationGroupId());
    if (spec.isEmpty()) {
      this.setUserId(Long.parseLong("2"));
      this.setShopId(Long.parseLong("1"));
      this.setReceivingAndPaymentDate(LocalDate.now());
      this.setReceivingAndPaymentTime(LocalTime.now());
      this.setBalanceTypeId(Long.parseLong("1"));
      this.setAccountAndBalanceId(Long.parseLong("1"));
      this.setMemo("");
      this.setEntryDate(LocalDateTime.now());
      this.setUpdateDate(LocalDateTime.now());
    } else {
      SpecificationGroup specificationGroup = spec.get();
      this.setUserId(specificationGroup.getUserId());
      this.setShopId(specificationGroup.getShopId());
      this.setReceivingAndPaymentDate(specificationGroup.getReceivingAndPaymentDate());
      this.setReceivingAndPaymentTime(specificationGroup.getReceivingAndPaymentTime());
      this.setBalanceTypeId(specificationGroup.getBalanceTypeId());
      this.setAccountAndBalanceId(specificationGroup.getAccountAndBalanceId());
      this.setMemo(specificationGroup.getMemo());
      this.setEntryDate(specificationGroup.getEntryDate());
      this.setUpdateDate(specificationGroup.getUpdateDate());
    }
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
    specificationGroup.setEntryDate(LocalDateTime.now());
    specificationGroup.setUpdateDate(this.getUpdateDate());

    return specificationGroup;
  }

  // Setter for User
  public UserForm setUserToForm(Long userId,
                                String nickName,
                                String lastName,
                                String firstName,
                                LocalDateTime birthDay,
                                String password,
                                String phoneNumber,
                                String email,
                                Long roleId,
                                String roleName,
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
    this.userForm.setRoleForm(new RoleForm());
    this.userForm.getRoleForm().setRoleId(roleId);
    this.userForm.getRoleForm().setRoleName(roleName);
    this.userForm.setEntryDate(entryDate);
    this.userForm.setUpdateDate(updateDate);

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
    this.shopForm.setEntryDate(shop.getEntryDate());
    this.shopForm.setUpdateDate(shop.getUpdateDate());

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
    this.accountAndBalanceForm.setEntryDate(accountAndBalance.getEntryDate());
    this.accountAndBalanceForm.setUpdateDate(accountAndBalance.getUpdateDate());

    return this.accountAndBalanceForm;
  }
}
