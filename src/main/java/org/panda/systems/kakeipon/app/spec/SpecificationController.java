package org.panda.systems.kakeipon.app.spec;

import org.panda.systems.kakeipon.app.common.AccountDestinationForm;
import org.panda.systems.kakeipon.app.common.AccountSourceForm;
import org.panda.systems.kakeipon.app.common.ShopForm;
import org.panda.systems.kakeipon.domain.model.common.AccountAndBalance;
import org.panda.systems.kakeipon.domain.model.common.AccountAndBalancePkey;
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

import java.util.List;

@Controller
@RequestMapping("")
public class SpecificationController {
  @Autowired
  SpecificationGroupService specificationGroupService;
  @Autowired
  SpecificationService specificationService;
  @Autowired
  ShopService shopService;
  @Autowired
  AccountAndBalanceService accountService;

  @ModelAttribute("specificationGroupForm")
  SpecificationGroupForm setUpSpecificationGroupForm() {
    return new SpecificationGroupForm();
  }

  @GetMapping("/spec")
  String addSpecificationGroup(@Validated SpecificationGroupForm specificationGroupForm,
                               @Validated ShopForm shopForm,
                               @Validated AccountSourceForm accountSourceForm,
                               @Validated AccountDestinationForm accountDestinationForm,
                               BindingResult result,
                               @ModelAttribute("specificationGroupId") Long specificationGroupId,
                               @ModelAttribute("shopId") Long shopId,
                               @ModelAttribute("accountSourceId") Long accountSourceId,
                               @ModelAttribute("accountDestinationId") Long accountDestinationId,
                               Model model) {
    model.addAttribute("specificationGroupForm", specificationGroupForm);
    model.addAttribute("shopForm", shopForm);
    model.addAttribute("accountSourceForm", accountSourceForm);
    model.addAttribute("accountDestinationForm", accountDestinationForm);
    return "/spec/createGroup";
  }

  @GetMapping("/searchShop")
  String searchShop(@Validated SpecificationGroupForm specificationGroupForm,
                    @Validated ShopForm shopForm,
                    @Validated AccountSourceForm accountSourceForm,
                    @Validated AccountDestinationForm accountDestinationForm,
                    BindingResult result,
                    @ModelAttribute("specificationGroupId") Long specificationGroupId,
                    @ModelAttribute("shopId") Long shopId,
                    @ModelAttribute("accountSourceId") Long accountSourceId,
                    @ModelAttribute("accountDestinationId") Long accountDestinationId,
                    Model model) {
    List<Shop> shops = shopService.findAll();
    model.addAttribute("specificationGroupForm", specificationGroupForm);
    model.addAttribute("shops", shops);
    model.addAttribute("accountSourceForm", accountSourceForm);
    model.addAttribute("accountDestinationForm", accountDestinationForm);

    return "/shop/showList";
  }

  @GetMapping("/searchAccountSource")
  String searchAccountSource(@Validated SpecificationGroupForm specificationGroupForm,
                             @Validated ShopForm shopForm,
                             @Validated AccountSourceForm accountSourceForm,
                             @Validated AccountDestinationForm accountDestinationForm,
                             @Validated AccountAndBalancePkey accountAndBalancePkey,
                             BindingResult result,
                             @ModelAttribute("specificationGroupId") Long specificationGroupId,
                             @ModelAttribute("shopId") Long shopId,
                             @ModelAttribute("accountSourceId") Long accountSourceId,
                             @ModelAttribute("accountDestinationId") Long accountDestinationId,
                             Model model) {

    List<AccountAndBalance> accounts = accountService.findAll();
    model.addAttribute("specificationGroupForm", specificationGroupForm);
    model.addAttribute("shopForm", shopForm);
    model.addAttribute("accounts", accounts);

    AccountAndBalance accountDestination;
    if (accountDestinationId.equals(0L)) {
      accountDestination = new AccountAndBalance();
    } else {
      accountDestination = accountService.findById(accountAndBalancePkey);
    }

    var destinationForm = new AccountDestinationForm();
    if (accountDestination != null) {
      destinationForm.setEntryDate(accountDestination.getEntryDate());
      destinationForm.setUpdateDate(accountDestination.getUpdateDate());
    }

    model.addAttribute("accountDestinationForm", destinationForm);

    return "/account/showSourceList";
  }

  @GetMapping("/searchAccountDestination")
  String searchAccountDestination(@Validated SpecificationGroupForm specificationGroupForm,
                                  @Validated ShopForm shopForm,
                                  @Validated AccountSourceForm accountSourceForm,
                                  @Validated AccountDestinationForm accountDestinationForm,
                                  @Validated AccountAndBalancePkey accountAndBalancePkey,
                                  BindingResult result,
                                  @ModelAttribute("specificationGroupId") Long specificationGroupId,
                                  @ModelAttribute("shopId") Long shopId,
                                  @ModelAttribute("accountSourceId") Long accountSourceId,
                                  @ModelAttribute("accountDestinationId") Long accountDestinationId,
                                  Model model) {

    List<AccountAndBalance> accounts = accountService.findAll();
    model.addAttribute("specificationGroupForm", specificationGroupForm);
    model.addAttribute("shopForm", shopForm);
    model.addAttribute("accounts", accounts);

    AccountAndBalance accountSource;
    if (accountSourceId.equals(0L)) {
      accountSource = new AccountAndBalance();
    } else {
      accountSource = accountService.findById(accountAndBalancePkey);
    }

    var sourceForm = new AccountSourceForm();
    if (accountSource != null) {
      sourceForm.setEntryDate(accountSource.getEntryDate());
      sourceForm.setUpdateDate(accountSource.getUpdateDate());
    }

    model.addAttribute("accountSourceForm", sourceForm);

    return "/account/showDestinationList";
  }
}
