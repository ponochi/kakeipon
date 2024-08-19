package org.panda.systems.kakeipon.app.spec;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import org.panda.systems.kakeipon.domain.model.common.AccountAndBalance;
import org.panda.systems.kakeipon.domain.model.common.Shop;
import org.panda.systems.kakeipon.domain.model.user.User;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class SpecificationGroupForm implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "tblSpecificationGroupSeq", allocationSize = 1)
  @Column(name = "specification_group_id")
  Long specificationGroupId;

  @OneToOne
  @JoinColumn(name = "user_id", table = "tbl_user",
      insertable = false, updatable = false)
  @PrimaryKeyJoinColumn
  @Column
  User user;

  @OneToOne
  @JoinColumn(name = "shop_id", table = "tbl_shop",
      insertable = false, updatable = false)
  @PrimaryKeyJoinColumn
  @Column
  Shop shop;

  @PastOrPresent
  @Column
  LocalDate receivingAndPaymentDate;

  @Column
  LocalTime receivingAndPaymentTime;

  @PositiveOrZero
  @Column
  Long receivingAndPaymentType;

  @OneToOne
  @JoinColumn(name = "account_source_id", table = "tbl_account",
      insertable = false, updatable = false)
  @PrimaryKeyJoinColumn
  @Column
  AccountAndBalance accountSource;

  @OneToOne
  @JoinColumn(name = "account_destination_id", table = "tbl_account",
      insertable = false, updatable = false)
  @PrimaryKeyJoinColumn
  @Column
  AccountAndBalance accountDestination;

  @Column
  String memo;

  @PastOrPresent
  @Column
  LocalDateTime entry_date;

  @Column
  LocalDateTime update_date;

  // Default constructor
  public SpecificationGroupForm() {
  }

  public Long getSpecificationGroupId() {
    return specificationGroupId;
  }

  public void setSpecificationGroupId(Long specificationGroupId) {
    this.specificationGroupId = specificationGroupId;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Shop getShop() {
    return shop;
  }

  public void setShop(Shop shop) {
    this.shop = shop;
  }

  public LocalDate getReceivingAndPaymentDate() {
    return receivingAndPaymentDate;
  }

  public void setReceivingAndPaymentDate(LocalDate receivingAndPaymentDate) {
    this.receivingAndPaymentDate = receivingAndPaymentDate;
  }

  public LocalTime getReceivingAndPaymentTime() {
    return receivingAndPaymentTime;
  }

  public void setReceivingAndPaymentTime(LocalTime receivingAndPaymentTime) {
    this.receivingAndPaymentTime = receivingAndPaymentTime;
  }

  public Long getReceivingAndPaymentType() {
    return receivingAndPaymentType;
  }

  public void setReceivingAndPaymentType(Long receivingAndPaymentType) {
    this.receivingAndPaymentType = receivingAndPaymentType;
  }

  public AccountAndBalance getAccountSource() {
    return accountSource;
  }

  public void setAccountSource(AccountAndBalance accountSource) {
    this.accountSource = accountSource;
  }

  public AccountAndBalance getAccountDestination() {
    return accountDestination;
  }

  public void setAccountDestination(AccountAndBalance accountDestination) {
    this.accountDestination = accountDestination;
  }

  public String getMemo() {
    return memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }

  public LocalDateTime getEntry_date() {
    return entry_date;
  }

  public void setEntry_date(LocalDateTime entry_date) {
    this.entry_date = entry_date;
  }

  public LocalDateTime getUpdate_date() {
    return update_date;
  }

  public void setUpdate_date(LocalDateTime update_date) {
    this.update_date = update_date;
  }

  @Override
  public String toString() {
    return "SpecificationGroupForm{" +
        "specificationGroupId=" + specificationGroupId +
        ", user=" + user +
        ", shop=" + shop +
        ", receivingAndPaymentDate=" + receivingAndPaymentDate +
        ", receivingAndPaymentTime=" + receivingAndPaymentTime +
        ", receivingAndPaymentType=" + receivingAndPaymentType +
        ", receivingAndPaymentSource=" + accountSource +
        ", receivingAndPaymentDestination=" + accountDestination +
        ", memo='" + memo + '\'' +
        ", entry_date=" + entry_date +
        ", update_date=" + update_date +
        '}';
  }
}
