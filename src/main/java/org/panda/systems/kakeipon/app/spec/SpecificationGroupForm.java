package org.panda.systems.kakeipon.app.spec;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.panda.systems.kakeipon.domain.model.common.Shop;
import org.panda.systems.kakeipon.domain.model.user.User;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;

public class SpecificationGroupForm implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "tblSpecificationGroupSeq", allocationSize = 1)
  Long specificationGroupId;

  @NotEmpty
  @OneToOne
  @JoinColumn(name = "user_id", table = "tbl_user",
      insertable = false, updatable = false)
  @PrimaryKeyJoinColumn
  User user;

  @NotEmpty
  @OneToOne
  @JoinColumn(name = "shop_id", table = "tbl_shop",
      insertable = false, updatable = false)
  @PrimaryKeyJoinColumn
  Shop shop;

  Date receivingAndPaymentDate;

  @NotEmpty
  Time receivingAndPaymentTime;

  @NotEmpty
  Long receivingAndPaymentType;

  Long accountSource;

  Long accountDestination;

  String memo;

  @NotEmpty
  LocalDateTime entry_date;

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

  public Date getReceivingAndPaymentDate() {
    return receivingAndPaymentDate;
  }

  public void setReceivingAndPaymentDate(Date receivingAndPaymentDate) {
    this.receivingAndPaymentDate = receivingAndPaymentDate;
  }

  public Time getReceivingAndPaymentTime() {
    return receivingAndPaymentTime;
  }

  public void setReceivingAndPaymentTime(Time receivingAndPaymentTime) {
    this.receivingAndPaymentTime = receivingAndPaymentTime;
  }

  public Long getReceivingAndPaymentType() {
    return receivingAndPaymentType;
  }

  public void setReceivingAndPaymentType(Long receivingAndPaymentType) {
    this.receivingAndPaymentType = receivingAndPaymentType;
  }

  public Long getAccountSource() {
    return accountSource;
  }

  public void setAccountSource(Long accountSource) {
    this.accountSource = accountSource;
  }

  public Long getAccountDestination() {
    return accountDestination;
  }

  public void setAccountDestination(Long accountDestination) {
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
