package org.panda.systems.kakeipon.domain.model.spec;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import org.panda.systems.kakeipon.domain.model.common.AccountAndBalance;
import org.panda.systems.kakeipon.domain.model.common.Shop;
import org.panda.systems.kakeipon.domain.model.user.User;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "tbl_specification_group")
@SecondaryTable(name = "tbl_account_and_balance",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "account_and_balance_id"))
public class SpecificationGroup implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "tbl_specification_group_seq", allocationSize = 1)
  private Long specificationGroupId;

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "user_id",
      insertable = false, updatable = false)
  private User user;

  @ManyToOne
  @JoinColumn(name = "shop_id", referencedColumnName = "shop_id",
      insertable = false, updatable = false)
  private Shop shop;

  @PastOrPresent
  @Column
  private LocalDate receivingAndPaymentDate;

  @NotEmpty
  @Column
  private LocalTime receivingAndPaymentTime;

  @PositiveOrZero
  @Column
  private Long receivingAndPaymentType;

  @OneToOne
  @JoinColumns({
      @JoinColumn(name = "account_id", table = "tbl_account_and_balance"),
  })
  private AccountAndBalance accountAndBalanceSource;

  @OneToOne
  @JoinColumns({
      @JoinColumn(name = "account_id", table = "tbl_account_and_balance"),
  })
  private AccountAndBalance accountAndBalanceDestination;

  @Column
  private String memo;

  @PastOrPresent
  @Column(name = "entry_date")
  private LocalDateTime entryDate;

  @Column
  private LocalDateTime updateDate;

  // Default Constructor
  public SpecificationGroup() {
  }

  // Getters and Setters
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

//  public AccountAndBalance getAccountSource() {
//    return accountSource;
//  }
//
//  public void setAccountSource(AccountAndBalance accountSource) {
//    this.accountSource = accountSource;
//  }
//
//  public AccountAndBalance getAccountDestination() {
//    return accountDestination;
//  }
//
//  public void setAccountDestination(AccountAndBalance accountDestination) {
//    this.accountDestination = accountDestination;
//  }

  public String getMemo() {
    return memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }

  public LocalDateTime getEntryDate() {
    return entryDate;
  }

  public void setEntryDate(LocalDateTime entryDate) {
    this.entryDate = entryDate;
  }

  public LocalDateTime getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(LocalDateTime updateDate) {
    this.updateDate = updateDate;
  }

  // toString
  @Override
  public String toString() {
    return "SpecificationGroup{" +
        "specificationGroupId=" + specificationGroupId +
        ", user=" + user +
        ", shop=" + shop +
        ", receivingAndPaymentDate=" + receivingAndPaymentDate +
        ", receivingAndPaymentTime=" + receivingAndPaymentTime +
        ", receivingAndPaymentType=" + receivingAndPaymentType +
//        ", accountSource=" + accountSource +
//        ", accountDestination=" + accountDestination +
        ", memo='" + memo + '\'' +
        ", entryDate=" + entryDate +
        ", updateDate=" + updateDate +
        '}';
  }
}
