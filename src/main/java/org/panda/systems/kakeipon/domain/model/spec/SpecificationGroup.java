package org.panda.systems.kakeipon.domain.model.spec;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import org.panda.systems.kakeipon.domain.model.common.AccountAndBalance;
import org.panda.systems.kakeipon.domain.model.common.Balance;
import org.panda.systems.kakeipon.domain.model.common.Shop;
import org.panda.systems.kakeipon.domain.model.user.User;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "tbl_specification_group")
@SecondaryTable(name = "tbl_balance",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "balance_id"))
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

  @OneToOne
  @JoinColumns({
      @JoinColumn(name = "balance_id", table = "tbl_balance",
          referencedColumnName = "balance_id",
          insertable = false, updatable = false),
  })
  private Balance balance;

  @OneToOne
  @JoinColumns({
      @JoinColumn(name = "account_and_balance_id",
          table = "tbl_account_and_balance",
          referencedColumnName = "account_and_balance_id",
          insertable = false, updatable = false),
  })
  private AccountAndBalance accountAndBalance;

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

  public @PastOrPresent LocalDate getReceivingAndPaymentDate() {
    return receivingAndPaymentDate;
  }

  public void setReceivingAndPaymentDate(@PastOrPresent LocalDate receivingAndPaymentDate) {
    this.receivingAndPaymentDate = receivingAndPaymentDate;
  }

  public @NotEmpty LocalTime getReceivingAndPaymentTime() {
    return receivingAndPaymentTime;
  }

  public void setReceivingAndPaymentTime(@NotEmpty LocalTime receivingAndPaymentTime) {
    this.receivingAndPaymentTime = receivingAndPaymentTime;
  }

  public @PositiveOrZero Balance getBalance() {
    return balance;
  }

  public void setBalanceId(@PositiveOrZero Balance balance) {
    this.balance = balance;
  }

  public AccountAndBalance getAccountAndBalanceSource() {
    return accountAndBalance;
  }

  public void setAccountAndBalance(
      AccountAndBalance accountAndBalance) {
    this.accountAndBalance = accountAndBalance;
  }

  public String getMemo() {
    return memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }

  public @PastOrPresent LocalDateTime getEntryDate() {
    return entryDate;
  }

  public void setEntryDate(@PastOrPresent LocalDateTime entryDate) {
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
        ", balance=" + balance +
        ", accountAndBalance=" + accountAndBalance +
        ", memo='" + memo + '\'' +
        ", entryDate=" + entryDate +
        ", updateDate=" + updateDate +
        '}';
  }
}
