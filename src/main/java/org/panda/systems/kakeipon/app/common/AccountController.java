package org.panda.systems.kakeipon.app.common;

import org.panda.systems.kakeipon.app.spec.SpecificationGroupForm;
import org.panda.systems.kakeipon.domain.model.common.AccountAndBalance;
import org.panda.systems.kakeipon.domain.service.common.AccountAndBalanceService;
import org.panda.systems.kakeipon.domain.service.common.AccountDestinationService;
import org.panda.systems.kakeipon.domain.service.common.AccountSourceService;
import org.panda.systems.kakeipon.domain.service.common.ShopService;
import org.panda.systems.kakeipon.domain.service.spec.SpecificationGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class AccountController {
  @Autowired
  AccountAndBalanceService accountAndBalanceService;
  @Autowired
  ShopService shopService;
  @Autowired
  SpecificationGroupService specificationGroupService;
  @Autowired
  AccountSourceService accountSourceService;
  @Autowired
  AccountDestinationService accountDestinationService;

//  @ModelAttribute("accountAndBalance")
//  AccountSourceForm setUpSourceForm() {
//    return new AccountSourceForm();
//  }
//
//  @ModelAttribute("accountAndBalance")
//  AccountDestinationForm setUpDestinationForm() {
//    return new AccountDestinationForm();
//  }
//
//  @PostMapping("/setAccountSource")
//  String setAccountSourceToSpecificationGroup(
//      @Validated AccountAndBalance accountAndBalance,
//      @ModelAttribute SpecificationGroupForm groupForm,
//      @ModelAttribute ShopForm shopForm,
//      Model model) {
//
//    var destinationForm = new AccountDestinationForm();
//    if (accountAndBalance.getAccountSourceId() > 0) {
//      AccountAndBalance accountDestination
//          = accountAndBalanceService.getById(
//          accountAndBalance.getAccountAndBalanceId());
//      destinationForm.setAccountDestinationId(
//          accountDestination.getAccountSourceId());
//      destinationForm.setEntryDate(accountDestination.getEntryDate());
//      destinationForm.setUpdateDate(accountDestination.getUpdateDate());
//    }
//
//    model.addAttribute("groupForm", groupForm);
//    model.addAttribute("shopForm", shopForm);
//    model.addAttribute("accountDestinationForm", destinationForm);
//
//    AccountAndBalance accountSource
//        = accountAndBalanceService.getById(
//        accountAndBalance.getAccountAndBalanceId());
//    AccountSourceForm sourceForm = new AccountSourceForm();
//    sourceForm.setAccountSourceId(
//        accountSource.getAccountSourceId());
//    sourceForm.setEntryDate(accountSource.getEntryDate());
//    sourceForm.setUpdateDate(accountSource.getUpdateDate());
//
//    model.addAttribute("accountSourceForm", sourceForm);
//
//    return "/spec/createGroup";
//  }
//
//  @PostMapping("/setAccountDestination/{accountSourceId}/{accountDestinationId}")
//  String setAccountDestinationToSpecificationGroup(
//      @PathVariable("accountSourceId") Long accountSourceId,
//      @PathVariable("accountDestinationId") Long accountDestinationId,
//      @Validated Long accountAndBalanceId,
//      @ModelAttribute SpecificationGroupForm groupForm,
//      @ModelAttribute ShopForm shopForm,
//      Model model) {
//
//    var sourceForm = new AccountSourceForm();
//    if (accountSourceId > 0) {
//      AccountAndBalance accountSource
//          = accountAndBalanceService.getById(accountAndBalanceId);
//      sourceForm.setAccountSourceId(accountSourceId);
//      sourceForm.setEntryDate(accountSource.getEntryDate());
//      sourceForm.setUpdateDate(accountSource.getUpdateDate());
//    }
//
//    model.addAttribute("groupForm", groupForm);
//    model.addAttribute("shopForm", shopForm);
//    model.addAttribute("accountSourceForm", sourceForm);
//
//    AccountAndBalance accountDestination
//        = accountAndBalanceService.getById(accountAndBalanceId);
//    AccountDestinationForm destinationForm = new AccountDestinationForm();
//    destinationForm.setAccountDestinationId(accountDestinationId);
//    destinationForm.setEntryDate(accountDestination.getEntryDate());
//    destinationForm.setUpdateDate(accountDestination.getUpdateDate());
//    model.addAttribute("accountDestinationForm", destinationForm);
//
//    return "/spec/createGroup";
//  }
}