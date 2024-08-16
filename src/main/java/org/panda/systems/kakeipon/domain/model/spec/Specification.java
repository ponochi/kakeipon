package org.panda.systems.kakeipon.domain.model.spec;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.panda.systems.kakeipon.domain.model.user.User;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Currency;

@Entity
@Table( name = "tbl_specification" )
public class Specification implements Serializable {
  @NotEmpty
  @Column
  private Long specificationGroupId;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "tbl_specification_seq", allocationSize = 1)
  @Column
  private Long specificationId;

  @OneToOne
  @JoinColumn(name = "userId", referencedColumnName = "userId",
      insertable = false, updatable = false)
  private User user;

  @Column
  private String itemName;

  @Column
  private BigDecimal itemsJpyPrice;

  @Column
  @Size(max = 3, min = 3, message = "通貨の種類は3文字で入力してください (ex. JPY, USD, ...)")
  private String currencyName;

  @Column
  private BigDecimal itemsPrice;

  @Column
  private Integer itemCount;

  @Column
  @Size(max = 1000, message = "メモは1000文字以内で入力してください")
  private String memo;

  @Column
  private LocalDateTime entryDate;

  @Column
  private LocalDateTime updateDate;

  // Default Constructor
  public Specification() {
  }

  // Getters and Setters
  public Long getSpecificationGroupId() {
    return specificationGroupId;
  }

  public void setSpecificationGroupId(Long specificationGroupId) {
    this.specificationGroupId = specificationGroupId;
  }

  public Long getSpecificationId() {
    return specificationId;
  }

  public void setSpecificationId(Long specificationId) {
    this.specificationId = specificationId;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  public BigDecimal getItemsJpyPrice() {
    return itemsJpyPrice;
  }

  public void setItemsJpyPrice(BigDecimal itemsJpyPrice) {
    this.itemsJpyPrice = itemsJpyPrice;
  }

  public String getCurrencyName() {
    return currencyName;
  }

  public void setCurrencyName(String currencyName) {
    this.currencyName = currencyName;
  }

  public BigDecimal getItemsPrice() {
    return itemsPrice;
  }

  public void setItemsPrice(BigDecimal itemsPrice) {
    this.itemsPrice = itemsPrice;
  }

  public Integer getItemCount() {
    return itemCount;
  }

  public void setItemCount(Integer itemCount) {
    this.itemCount = itemCount;
  }

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
    return "Specification{" +
        "specificationGroupId=" + specificationGroupId +
        ", specificationId=" + specificationId +
        ", user=" + user +
        ", itemName='" + itemName + '\'' +
        ", itemsJpyPrice=" + itemsJpyPrice +
        ", currency='" + currencyName + '\'' +
        ", itemsPrice=" + itemsPrice +
        ", itemCount=" + itemCount +
        ", memo='" + memo + '\'' +
        ", entryDate=" + entryDate +
        ", updateDate=" + updateDate +
        '}';
  }
}
