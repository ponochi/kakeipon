package org.panda.systems.kakeipon.app.common;

import org.panda.systems.kakeipon.domain.service.common.AccountAndBalanceService;
import org.panda.systems.kakeipon.domain.service.common.ShopService;
import org.panda.systems.kakeipon.domain.service.spec.SpecificationGroupService;
import org.panda.systems.kakeipon.domain.service.spec.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class ShopController {
  @Autowired
  SpecificationGroupService specificationGroupService;
  @Autowired
  SpecificationService specificationService;
  @Autowired
  ShopService shopService;
  @Autowired
  AccountAndBalanceService accountAndBalanceService;

//  @GetMapping("setShop")
//  String setShopToSpecificationGroup(
//      @ModelAttribute SpecificationGroupForm groupForm,
//      Model model) {
//    model.addAttribute("groupForm", groupForm);
//    return "/spec/createGroup";
//  }

//  @GetMapping("searchShop")
//  String searchShop(
//      @ModelAttribute ShopForm shopForm,
//      Model model) {
//    List<Shop> shops = shopService.findAll();
//    model.addAttribute("shops", shops);
//    return "/shop/showList";
//  }
}
