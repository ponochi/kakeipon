package org.panda.systems.kakeipon.app.common;

import org.panda.systems.kakeipon.app.spec.SpecificationGroupForm;
import org.panda.systems.kakeipon.domain.model.common.Shop;
import org.panda.systems.kakeipon.domain.service.common.ShopService;
import org.panda.systems.kakeipon.domain.service.spec.SpecificationGroupService;
import org.panda.systems.kakeipon.domain.service.spec.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping( "" )
public class ShopController {
  @Autowired
  SpecificationGroupService specificationGroupService;
  @Autowired
  SpecificationService specificationService;
  @Autowired
  ShopService shopService;

  @GetMapping( "setShop" )
  String setShopToSpecificationGroup(
      @ModelAttribute SpecificationGroupForm groupForm,
      @ModelAttribute ShopForm shopForm,
      Model model ) {
    Shop shop = shopService.findById( shopForm.getShopId() );

    model.addAttribute( "shopForm", shop );
    model.addAttribute( "groupForm", groupForm );

    return "/spec/createGroup";
  }
}
