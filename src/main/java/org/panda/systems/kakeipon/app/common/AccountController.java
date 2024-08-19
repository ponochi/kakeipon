package org.panda.systems.kakeipon.app.common;

import org.panda.systems.kakeipon.app.spec.SpecificationGroupForm;
import org.panda.systems.kakeipon.domain.model.common.AccountAndBalance;
import org.panda.systems.kakeipon.domain.model.common.AccountAndBalancePkey;
import org.panda.systems.kakeipon.domain.service.common.AccountAndBalanceService;
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
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping("")
public class AccountController {
  @Autowired
  AccountAndBalanceService accountService;
  @Autowired
  ShopService shopService;
  @Autowired
  SpecificationGroupService specificationGroupService;

  @ModelAttribute
  AccountSourceForm setUpSourceForm() {
    return new AccountSourceForm();
  }

  @ModelAttribute
  AccountDestinationForm setUpDestinationForm() {
    return new AccountDestinationForm();
  }

  @PostMapping("/setAccountSource")
  String setAccountSourceToSpecificationGroup(
      @Validated AccountAndBalancePkey accountAndBalancePkey,
      @ModelAttribute SpecificationGroupForm groupForm,
      @ModelAttribute ShopForm shopForm,
      Model model,
      UriComponentsBuilder builder) {

    var destinationForm = new AccountDestinationForm();
    if (accountAndBalancePkey.getAccountId() > 0) {
      AccountAndBalance accountDestination
          = accountService.findById(accountAndBalancePkey);
      destinationForm.setAccountId(
          accountDestination.getAccountAndBalancePkey().getAccountId());
      destinationForm.setEntryDate(accountDestination.getEntryDate());
      destinationForm.setUpdateDate(accountDestination.getUpdateDate());
    }

    model.addAttribute("groupForm", groupForm);
    model.addAttribute("shopForm", shopForm);
    model.addAttribute("accountDestinationForm", destinationForm);

    AccountAndBalance accountSource
        = accountService.findById(accountAndBalancePkey);
    AccountSourceForm sourceForm = new AccountSourceForm();
    sourceForm.setAccountId(
        accountSource.getAccountAndBalancePkey().getAccountId());
    sourceForm.setEntryDate(accountSource.getEntryDate());
    sourceForm.setUpdateDate(accountSource.getUpdateDate());

    model.addAttribute("accountSourceForm", sourceForm);

    return "/spec/createGroup";
  }

  @PostMapping("/setAccountDestination/{accountSourceId}/{accountDestinationId}")
  String setAccountDestinationToSpecificationGroup(
      @PathVariable("accountSourceId") Long accountSourceId,
      @PathVariable("accountDestinationId") Long accountDestinationId,
      @Validated AccountAndBalancePkey accountAndBalancePkey,
      @ModelAttribute SpecificationGroupForm groupForm,
      @ModelAttribute ShopForm shopForm,
      Model model) {

    var sourceForm = new AccountSourceForm();
    if (accountSourceId > 0) {
      AccountAndBalance accountSource
          = accountService.findById(accountAndBalancePkey);
      sourceForm.setAccountId(accountSourceId);
      sourceForm.setEntryDate(accountSource.getEntryDate());
      sourceForm.setUpdateDate(accountSource.getUpdateDate());
    }

    model.addAttribute("groupForm", groupForm);
    model.addAttribute("shopForm", shopForm);
    model.addAttribute("accountSourceForm", sourceForm);

    AccountAndBalance accountDestination
        = accountService.findById(accountAndBalancePkey);
    AccountDestinationForm destinationForm = new AccountDestinationForm();
    destinationForm.setAccountId(accountDestinationId);
    destinationForm.setEntryDate(accountDestination.getEntryDate());
    destinationForm.setUpdateDate(accountDestination.getUpdateDate());
    model.addAttribute("accountDestinationForm", destinationForm);

    return "/spec/createGroup";
  }
}