package org.panda.systems.kakeipon.app.shop;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import org.panda.systems.kakeipon.domain.model.common.Shop;
import org.panda.systems.kakeipon.domain.service.common.ShopService;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

@Table(name = "shop")
@Data
public class ShopForm implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  private final ShopService shopService;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "shop_seq", allocationSize = 1)
  @Column(name = "shop_id")
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

  @PastOrPresent
  @Column
  private LocalDateTime entryDate;

  @Column
  private LocalDateTime updateDate;

  // Default constructor
  public ShopForm() {

    this.shopService = null;
  }

  public ShopForm(ShopService shopService) {

    this.shopService = shopService;
  }

  public ShopForm setShopFormByDB(Long shopId) {

    if (shopId == null) {
      shopId = Long.parseLong("1");
    } else {
      this.setShopId(shopId);
    }
    Shop shop = shopService.findById(this.getShopId());
    this.setShopName(shop.getShopName());
    this.setBranchName(shop.getBranchName());
    this.setShopUrl(shop.getShopUrl());
    this.setPhoneNumber(shop.getPhoneNumber());
    this.setEmail(shop.getEmail());
    this.setOpenShopDate(shop.getOpenShopDate());
    this.setCloseShopDate(shop.getCloseShopDate());
    this.setShopMemo(shop.getShopMemo());
    this.setEntryDate(shop.getEntryDate());
    this.setUpdateDate(shop.getUpdateDate());

    return this;
  }

  public ShopForm setShopToForm(Shop shop) {
    ShopForm form = new ShopForm(shopService);

    form.setShopId(shop.getShopId());
    form.setShopName(shop.getShopName());
    form.setBranchName(shop.getBranchName());
    form.setShopUrl(shop.getShopUrl());
    form.setPhoneNumber(shop.getPhoneNumber());
    form.setEmail(shop.getEmail());
    form.setOpenShopDate(shop.getOpenShopDate());
    form.setCloseShopDate(shop.getCloseShopDate());
    form.setShopMemo(shop.getShopMemo());
    form.setEntryDate(shop.getEntryDate());
    form.setUpdateDate(shop.getUpdateDate());

    return form;
  }
}
