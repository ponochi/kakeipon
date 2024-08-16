package org.panda.systems.kakeipon.app.common;

import org.panda.systems.kakeipon.app.spec.SpecificationGroupForm;
import org.panda.systems.kakeipon.domain.model.common.Account;
import org.panda.systems.kakeipon.domain.service.common.AccountService;
import org.panda.systems.kakeipon.domain.service.common.ShopService;
import org.panda.systems.kakeipon.domain.service.spec.SpecificationGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping("")
public class AccountController {
  @Autowired
  AccountService accountService;
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

  @PostMapping("/setAccountSource/{accountDestinationId}/{accountSourceId}")
  String setAccountSourceToSpecificationGroup(
      @PathVariable("accountDestinationId") Long accountDestinationId,
      @PathVariable("accountSourceId") Long accountSourceId,
      @ModelAttribute SpecificationGroupForm groupForm,
      @ModelAttribute ShopForm shopForm,
      Model model,
      UriComponentsBuilder builder) {

    var destinationForm = new AccountDestinationForm();
    if (accountDestinationId > 0) {
      Account accountDestination = accountService.findById(accountDestinationId);
      destinationForm.setAccountId(accountDestinationId);
      destinationForm.setAccountName(accountDestination.getAccountName());
      destinationForm.setBranchName(accountDestination.getBranchName());
      destinationForm.setEntryDate(accountDestination.getEntryDate());
      destinationForm.setUpdateDate(accountDestination.getUpdateDate());
    }

    model.addAttribute("groupForm", groupForm);
    model.addAttribute("shopForm", shopForm);
    model.addAttribute("accountDestinationForm", destinationForm);

    Account accountSource = accountService.findById(accountSourceId);
    AccountSourceForm sourceForm = new AccountSourceForm();
    sourceForm.setAccountId(accountSourceId);
    sourceForm.setAccountName(accountSource.getAccountName());
    sourceForm.setBranchName(accountSource.getBranchName());
    sourceForm.setEntryDate(accountSource.getEntryDate());
    sourceForm.setUpdateDate(accountSource.getUpdateDate());

    model.addAttribute("accountSourceForm", sourceForm);

    // URI location = builder.path("/spec/createGroup").build().toUri();
    // return "redirect:" + location.toString();
    return "/spec/createGroup";
  }

  @PostMapping("/setAccountDestination/{accountSourceId}/{accountDestinationId}")
  String setAccountDestinationToSpecificationGroup(
      @PathVariable("accountSourceId") Long accountSourceId,
      @PathVariable("accountDestinationId") Long accountDestinationId,
      @ModelAttribute SpecificationGroupForm groupForm,
      @ModelAttribute ShopForm shopForm,
      Model model) {

    var sourceForm = new AccountSourceForm();
    if (accountSourceId > 0) {
      Account accountSource = accountService.findById(accountSourceId);
      sourceForm.setAccountId(accountSourceId);
      sourceForm.setAccountName(accountSource.getAccountName());
      sourceForm.setBranchName(accountSource.getBranchName());
      sourceForm.setEntryDate(accountSource.getEntryDate());
      sourceForm.setUpdateDate(accountSource.getUpdateDate());
    }

    model.addAttribute("groupForm", groupForm);
    model.addAttribute("shopForm", shopForm);
    model.addAttribute("accountSourceForm", sourceForm);

    Account accountDestination = accountService.findById(accountDestinationId);
    AccountDestinationForm destinationForm = new AccountDestinationForm();
    destinationForm.setAccountId(accountDestinationId);
    destinationForm.setAccountName(accountDestination.getAccountName());
    destinationForm.setBranchName(accountDestination.getBranchName());
    destinationForm.setEntryDate(accountDestination.getEntryDate());
    destinationForm.setUpdateDate(accountDestination.getUpdateDate());
    model.addAttribute("accountDestinationForm", destinationForm);

    return "/spec/createGroup";
  }
}