package org.panda.systems.kakeipon.app.common;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

public class ShopForm implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "tblShopSeq", allocationSize = 1)
  @Column
  private Long shopId;

  @NotEmpty
  private String shopName;

  @NotEmpty
  private String branchName;

  private String shopUrl;

  private String phoneNumber;

  private String email;

  private Date openShopDate;

  private Date closeShopDate;

  private String shopMemo;

  @NotEmpty
  private LocalDateTime entryDate;

  private LocalDateTime updateDate;

  // Default constructor
  public ShopForm() {
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

  @Override
  public String toString() {
    return "ShopForm{" +
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