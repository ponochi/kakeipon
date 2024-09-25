package org.panda.systems.kakeipon.domain.service.common;

import org.panda.systems.kakeipon.app.shop.ShopForm;
import org.panda.systems.kakeipon.domain.model.common.Shop;
import org.panda.systems.kakeipon.domain.repository.common.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Service
public class ShopService implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Autowired
  private ShopRepository shopRepository;

  public List<Shop> findAll() {
    return shopRepository.findAll();
  }

  public Shop findById(Long shopId) {
    return shopRepository.findById(shopId).orElse(null);
  }

  public ShopForm findByIdToForm(Long shopId) {
    ShopForm shopForm = new ShopForm();

    Shop shop = shopRepository.findById(shopId).orElse(null);

    shopForm.setShopId(shop.getShopId());
    shopForm.setShopName(shop.getShopName());
    shopForm.setBranchName(shop.getBranchName());
    shopForm.setShopUrl(shop.getShopUrl());
    shopForm.setPhoneNumber(shop.getPhoneNumber());
    shopForm.setEmail(shop.getEmail());
    shopForm.setOpenShopDate(shop.getOpenShopDate());
    shopForm.setCloseShopDate(shop.getCloseShopDate());
    shopForm.setShopMemo(shop.getShopMemo());
    shopForm.setEntryDate(shop.getEntryDate());
    shopForm.setUpdateDate(shop.getUpdateDate());

    return shopForm;
  }

  public void saveAndFlush(Shop entity)
  {
    shopRepository.saveAndFlush(entity);
  }
}
