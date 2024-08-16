package org.panda.systems.kakeipon.domain.model.spec;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.panda.systems.kakeipon.domain.model.common.Shop;
import org.panda.systems.kakeipon.domain.model.user.User;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_specification_group")
public class SpecificationGroup implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "tbl_specification_group_seq", allocationSize = 1)
  private Long specificationGroupId;

  @ManyToOne
  @JoinColumn(name = "userId", referencedColumnName = "userId",
      insertable = false, updatable = false)
  private User user;

  @ManyToOne
  @JoinColumn(name = "shopId", referencedColumnName = "shopId",
      insertable = false, updatable = false)
  private Shop shop;

  @NotEmpty
  @Column
  private Date receivingAndPaymentDate;

  @Column
  private Time receivingAndPaymentTime;

  @NotEmpty
  @Column
  private Long receivingAndPaymentType;

  @NotEmpty
  @Column
  private Long accountSource;

  @Column
  private Long accountDestination;

  @Column
  private String memo;

  @Column
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

  public void setAccountSource(Long receivingAndPaymentSource) {
    this.accountSource = receivingAndPaymentSource;
  }

  public Long getAccountDestination() {
    return accountDestination;
  }

  public void setAccountDestination(Long receivingAndPaymentDestination) {
    this.accountDestination = receivingAndPaymentDestination;
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
    return "SpecificationGroup{" +
        "specificationGroupId=" + specificationGroupId +
        ", user=" + user +
        ", shop=" + shop +
        ", receivingAndPaymentDate=" + receivingAndPaymentDate +
        ", receivingAndPaymentTime=" + receivingAndPaymentTime +
        ", receivingAndPaymentType=" + receivingAndPaymentType +
        ", accountSource=" + accountSource +
        ", accountDestination=" + accountDestination +
        ", memo='" + memo + '\'' +
        ", entryDate=" + entryDate +
        ", updateDate=" + updateDate +
        '}';
  }
}
