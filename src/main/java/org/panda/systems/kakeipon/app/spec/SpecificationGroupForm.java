package org.panda.systems.kakeipon.app.spec;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.panda.systems.kakeipon.app.common.AccountAndBalanceForm;
import org.panda.systems.kakeipon.app.common.BalanceTypeForm;
import org.panda.systems.kakeipon.app.common.ShopForm;
import org.panda.systems.kakeipon.app.user.UserForm;
import org.panda.systems.kakeipon.domain.model.common.*;
import org.panda.systems.kakeipon.domain.model.spec.SpecificationGroup;
import org.panda.systems.kakeipon.domain.model.user.User;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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

  public static SpecificationGroup toEntity(SpecificationGroupForm specificationGroupForm) {
    SpecificationGroup specificationGroup = new SpecificationGroup();

    specificationGroup.setSpecificationGroupId(specificationGroupForm.getSpecificationGroupId());
    specificationGroup.setUserId(specificationGroupForm.getUserId());
    specificationGroup.setShopId(specificationGroupForm.getShopId());
    specificationGroup.setReceivingAndPaymentDate(specificationGroupForm.getReceivingAndPaymentDate());
    specificationGroup.setReceivingAndPaymentTime(specificationGroupForm.getReceivingAndPaymentTime());
    specificationGroup.setBalanceTypeId(specificationGroupForm.getBalanceTypeId());
    specificationGroup.setAccountAndBalanceId(specificationGroupForm.getAccountAndBalanceId());
    specificationGroup.setMemo(specificationGroupForm.getMemo());
    specificationGroup.setEntryDate(LocalDateTime.now());
    specificationGroup.setUpdateDate(specificationGroupForm.getUpdateDate());

    return specificationGroup;
  }

  // Setter for User
  public UserForm setUserToForm(User user) {
    this.userForm = new UserForm();

    this.userForm.setUserId(user.getUserId());
    this.userForm.setNickName(user.getNickName());
    this.userForm.setLastName(user.getLastName());
    this.userForm.setFirstName(user.getFirstName());
    this.userForm.setBirthDay(user.getBirthDay());
    this.userForm.setPassword(user.getPassword());
    this.userForm.setPhoneNumber(user.getPhoneNumber());
    this.userForm.setEmail(user.getEmail());
    this.userForm.setRole(new Role());
    this.userForm.getRole().setRoleId(user.getRole().getRoleId());
    this.userForm.getRole().setRoleName(user.getRole().getRoleName());
    this.userForm.setEntryDate(user.getEntryDate());
    this.userForm.setUpdateDate(user.getUpdateDate());

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
