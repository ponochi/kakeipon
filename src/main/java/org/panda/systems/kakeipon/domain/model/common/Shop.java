package org.panda.systems.kakeipon.domain.model.common;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table( name = "tbl_shop" )
public class Shop {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "tbl_shop_seq", allocationSize = 1)
  @Column
  private Long shopId;

  @NotEmpty
  @Column
  private String shopName;

  @NotEmpty
  @Column
  private String branchName;

  @Column
  private String shopUrl;

  @Column
  private String phoneNumber;

  @Column
  private String email;

  @Column
  private Date openShopDate;

  @Column
  private Date closeShopDate;

  @Column
  private String shopMemo;

  @Column
  private LocalDateTime entryDate;

  @Column
  private LocalDateTime updateDate;

  // Default Constructor
  public Shop() {
  }

  // Getters and Setters
  public Long getShopId() {
    return shopId;
  }

  public void setShopId(Long shopId) {
    this.shopId = shopId;
  }

  public String getShopName() {
    return shopName;
  }

  public void setShopName(String shopName) {
    this.shopName = shopName;
  }

  public String getBranchName() {
    return branchName;
  }

  public void setBranchName(String branchName) {
    this.branchName = branchName;
  }

  public String getShopUrl() {
    return shopUrl;
  }

  public void setShopUrl(String shopUrl) {
    this.shopUrl = shopUrl;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Date getOpenShopDate() {
    return openShopDate;
  }

  public void setOpenShopDate(Date openShopDate) {
    this.openShopDate = openShopDate;
  }

  public Date getCloseShopDate() {
    return closeShopDate;
  }

  public void setCloseShopDate(Date closeShopDate) {
    this.closeShopDate = closeShopDate;
  }

  public String getShopMemo() {
    return shopMemo;
  }

  public void setShopMemo(String shopMemo) {
    this.shopMemo = shopMemo;
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
    return "Shop{" +
        "shopId=" + shopId +
        ", shopName='" + shopName + '\'' +
        ", branchName='" + branchName + '\'' +
        ", shopUrl='" + shopUrl + '\'' +
        ", phoneNumber='" + phoneNumber + '\'' +
        ", email='" + email + '\'' +
        ", openShopDate=" + openShopDate +
        ", closeShopDate=" + closeShopDate +
        ", shopMemo='" + shopMemo + '\'' +
        ", entryDate=" + entryDate +
        ", updateDate=" + updateDate +
        '}';
  }
}

