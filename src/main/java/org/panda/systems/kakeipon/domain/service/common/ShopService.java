package org.panda.systems.kakeipon.domain.service.common;

import org.panda.systems.kakeipon.domain.model.common.Shop;
import org.panda.systems.kakeipon.domain.repository.common.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class ShopService implements Serializable {
  private static final long serialVersionUID = 1L;

  @Autowired
  private ShopRepository shopRepository;

  public List<Shop> findAll() {
    return shopRepository.findAll();
  }

  public Shop findById(Long id) {
    return shopRepository.findById(id).orElse(null);
  }

  public void saveAndFlush(Shop entity) {
    shopRepository.saveAndFlush(entity);
  }
}
