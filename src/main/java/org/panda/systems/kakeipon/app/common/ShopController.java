package org.panda.systems.kakeipon.app.common;

import org.panda.systems.kakeipon.app.spec.SpecificationGroupForm;
import org.panda.systems.kakeipon.domain.model.common.AccountAndBalance;
import org.panda.systems.kakeipon.domain.model.common.Shop;
import org.panda.systems.kakeipon.domain.service.common.AccountAndBalanceService;
import org.panda.systems.kakeipon.domain.service.common.ShopService;
import org.panda.systems.kakeipon.domain.service.spec.SpecificationGroupService;
import org.panda.systems.kakeipon.domain.service.spec.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("")
@SessionAttributes({"specificationGroupForm", "shopForm", "accountSourceForm", "accountDestinationForm"})
public class ShopController {
  @Autowired
  SpecificationGroupService specificationGroupService;
  @Autowired
  SpecificationService specificationService;
  @Autowired
  ShopService shopService;
  @Autowired
  AccountAndBalanceService accountAndBalanceService;

  @GetMapping("setShop")
  String setShopToSpecificationGroup(
      @Validated SpecificationGroupForm specificationGroupForm,
      @Validated ShopForm shopForm,
      @Validated AccountSourceForm accountSourceForm,
      @Validated AccountDestinationForm accountDestinationForm,
      @Validated Long accountAndBalanceId,
      BindingResult result,
      @ModelAttribute("shopId") Long shopId,
      @ModelAttribute("accountSourceId") Long accountSourceId,
      @ModelAttribute("accountDestinationId") Long accountDestinationId,
      Model model) {

    Shop shop = shopService.findById(shopId);
    shopForm.setShopName(shop.getShopName());
    shopForm.setBranchName(shop.getBranchName());
    shopForm.setEntryDate(shop.getEntryDate());
    shopForm.setUpdateDate(shop.getUpdateDate());

    var sourceForm = new AccountSourceForm();
    if (accountSourceId > 0) {
      AccountAndBalance accountSource
          = accountAndBalanceService.getById(accountAndBalanceId);
      sourceForm.setAccountId(accountSourceId);
      sourceForm.setEntryDate(accountSource.getEntryDate());
      sourceForm.setUpdateDate(accountSource.getUpdateDate());
    }

    model.addAttribute("specificationGroupForm", specificationGroupForm);
    model.addAttribute("shopForm", shopForm);
    model.addAttribute("accountSourceForm", sourceForm);

    AccountAndBalance accountDestination
        = accountAndBalanceService.getById(accountAndBalanceId);
    AccountDestinationForm destinationForm = new AccountDestinationForm();
    destinationForm.setAccountId(accountDestinationId);
    destinationForm.setEntryDate(accountDestination.getEntryDate());
    destinationForm.setUpdateDate(accountDestination.getUpdateDate());
    model.addAttribute("accountDestinationForm", destinationForm);

    return "/spec/createGroup";
  }
}
