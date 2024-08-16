package org.panda.systems.kakeipon.app.spec;

import org.panda.systems.kakeipon.app.common.AccountDestinationForm;
import org.panda.systems.kakeipon.app.common.AccountSourceForm;
import org.panda.systems.kakeipon.app.common.ShopForm;
import org.panda.systems.kakeipon.domain.model.common.Account;
import org.panda.systems.kakeipon.domain.model.common.Shop;
import org.panda.systems.kakeipon.domain.service.common.AccountService;
import org.panda.systems.kakeipon.domain.service.common.ShopService;
import org.panda.systems.kakeipon.domain.service.spec.SpecificationGroupService;
import org.panda.systems.kakeipon.domain.service.spec.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class SpecificationController {
  @Autowired
  SpecificationGroupService specificationGroupService;
  @Autowired
  SpecificationService specificationService;
  @Autowired
  ShopService shopService;
  @Autowired
  AccountService accountService;

  @ModelAttribute
  SpecificationGroupForm setUpForm() {
    return new SpecificationGroupForm();
  }

  @GetMapping("spec")
  String addSpecificationGroup(
      @ModelAttribute SpecificationGroupForm groupForm,
      @ModelAttribute ShopForm shopForm,
      @ModelAttribute AccountSourceForm accountSourceForm,
      @ModelAttribute AccountDestinationForm accountDestinationForm,
      Model model) {
    model.addAttribute("groupForm", groupForm);
    model.addAttribute("shopForm", shopForm);
    model.addAttribute("accountSourceForm", accountSourceForm);
    model.addAttribute("accountDestinationForm", accountDestinationForm);
    return "/spec/createGroup";
  }

  @GetMapping("searchShop")
  String searchShop(
      @ModelAttribute SpecificationGroupForm groupForm,
      @ModelAttribute AccountSourceForm accountSourceForm,
      @ModelAttribute AccountDestinationForm accountDestinationForm,
      Model model) {
    List<Shop> shops = shopService.findAll();
    model.addAttribute("groupForm", groupForm);
    model.addAttribute("shops", shops);
    model.addAttribute("accountSourceForm", accountSourceForm);
    model.addAttribute("accountDestinationForm", accountDestinationForm);

    return "/shop/showList";
  }

  @GetMapping("searchAccountSource/{accountDestinationId}/{accountSourceId}")
  String searchAccountSource(
      @PathVariable("accountDestinationId") String accountDestinationId,
      @PathVariable("accountSourceId") String accountSourceId,
      @ModelAttribute AccountSourceForm accountSourceForm,
      @ModelAttribute SpecificationGroupForm groupForm,
      @ModelAttribute ShopForm shopForm,
      Model model) {

    List<Account> accounts = accountService.findAll();
    model.addAttribute("groupForm", groupForm);
    model.addAttribute("shopForm", shopForm);
    model.addAttribute("accounts", accounts);

    Account accountDestination;
    if (accountDestinationId.equals("0")) {
      accountDestination = new Account();
    } else {
      accountDestination = accountService.findById(
          Long.parseLong(accountDestinationId));
    }

    var destinationForm = new AccountDestinationForm();
    if (accountDestination != null) {
      destinationForm.setAccountId(accountDestination.getAccountId());
      destinationForm.setAccountName(accountDestination.getAccountName());
      destinationForm.setBranchName(accountDestination.getBranchName());
      destinationForm.setEntryDate(accountDestination.getEntryDate());
      destinationForm.setUpdateDate(accountDestination.getUpdateDate());
    }

    model.addAttribute("accountDestinationForm", destinationForm);

    return "/account/showSourceList";
  }

  @GetMapping("searchAccountDestination/{accountSourceId}/{accountDestinationId}")
  String searchAccountDestination(
      @PathVariable("accountSourceId") String accountSourceId,
      @PathVariable("accountDestinationId") String accountDestinationId,
      @ModelAttribute AccountDestinationForm accountDestinationForm,
      @ModelAttribute SpecificationGroupForm groupForm,
      @ModelAttribute ShopForm shopForm,
      Model model) {

    List<Account> accounts = accountService.findAll();
    model.addAttribute("groupForm", groupForm);
    model.addAttribute("shopForm", shopForm);
    model.addAttribute("accounts", accounts);

    Account accountSource;
    if (accountSourceId.equals("0")) {
      accountSource = new Account();
    } else {
      accountSource = accountService.findById(
          Long.parseLong(accountSourceId));
    }

    var sourceForm = new AccountSourceForm();
    if (accountSource != null) {
      sourceForm.setAccountId(accountSource.getAccountId());
      sourceForm.setAccountName(accountSource.getAccountName());
      sourceForm.setBranchName(accountSource.getBranchName());
      sourceForm.setEntryDate(accountSource.getEntryDate());
      sourceForm.setUpdateDate(accountSource.getUpdateDate());
    }

    model.addAttribute("accountSourceForm", sourceForm);

    return "/account/showDestinationList";
  }
}
