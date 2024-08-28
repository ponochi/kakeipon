package org.panda.systems.kakeipon.app.spec;

import org.panda.systems.kakeipon.app.common.AccountAndBalanceForm;
import org.panda.systems.kakeipon.app.common.AccountDestinationForm;
import org.panda.systems.kakeipon.app.common.AccountSourceForm;
import org.panda.systems.kakeipon.app.common.ShopForm;
import org.panda.systems.kakeipon.domain.model.common.AccountAndBalance;
import org.panda.systems.kakeipon.domain.model.common.AccountDestination;
import org.panda.systems.kakeipon.domain.model.common.AccountSource;
import org.panda.systems.kakeipon.domain.model.common.Shop;
import org.panda.systems.kakeipon.domain.model.spec.SpecificationGroup;
import org.panda.systems.kakeipon.domain.model.user.User;
import org.panda.systems.kakeipon.domain.service.common.*;
import org.panda.systems.kakeipon.domain.service.spec.SpecificationGroupService;
import org.panda.systems.kakeipon.domain.service.spec.SpecificationService;
import org.panda.systems.kakeipon.domain.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("")
public class SpecificationController {
  @Autowired
  SpecificationGroupService specificationGroupService;
  @Autowired
  SpecificationService specificationService;
  @Autowired
  UserService userService;
  @Autowired
  ShopService shopService;
  @Autowired
  AccountAndBalanceService accountAndBalanceService;
  @Autowired
  BalanceService balanceService;
  @Autowired
  AccountSourceService accountSourceService;
  @Autowired
  AccountDestinationService accountDestinationService;
  @Autowired
  User user;

  ShopForm setShopForm(Shop shop) {
    ShopForm form = new ShopForm();
    form.setShopId(shop.getShopId());
    form.setShopName(shop.getShopName());
    form.setBranchName(shop.getBranchName());
    form.setShopUrl(shop.getShopUrl());
    form.setPhoneNumber(shop.getPhoneNumber());
    form.setEmail(shop.getEmail());
    form.setOpenShopDate(shop.getOpenShopDate());
    form.setCloseShopDate(shop.getCloseShopDate());
    form.setShopMemo(shop.getShopMemo());
    form.setEntryDate(shop.getEntryDate());
    form.setUpdateDate(shop.getUpdateDate());

    return form;
  }

  AccountAndBalanceForm setAccountAndBalanceForm(AccountAndBalance accountAndBalance) {
    AccountAndBalanceForm form = new AccountAndBalanceForm();
    form.setAccountAndBalanceId(accountAndBalance.getAccountAndBalanceId());
//
//    AccountSourceForm accountSourceForm = new AccountSourceForm();
//    AccountSource accountSource = accountSourceService.getById(
//        form.getAccountSourceId());
//    form.setAccountSourceForm(accountSourceForm);
//    form.getAccountSourceForm().setAccountId(accountSource.getAccountSourceId());
//    form.getAccountSourceForm().setAccountName(accountSource.getAccountName());
//    form.getAccountSourceForm().setBranchName(accountSource.getBranchName());
//    form.getAccountSourceForm().setEntryDate(accountSource.getEntryDate());
//
//    AccountDestinationForm accountDestinationForm = new AccountDestinationForm();
//    AccountDestination accountDestination = accountDestinationService.getById(
//      form.getAccountDestinationId());
//    form.setAccountDestinationForm(accountDestinationForm);
//    form.getAccountDestinationForm().setAccountId(
//        accountDestination.getAccountDestinationId());
//    form.getAccountDestinationForm().setAccountName(
//        accountDestination.getAccountName());
//    form.getAccountDestinationForm().setBranchName(
//        accountDestination.getBranchName());
//    form.getAccountDestinationForm().setEntryDate(
//        accountDestination.getEntryDate());

    form.setEntryDate(accountAndBalance.getEntryDate());
    form.setUpdateDate(accountAndBalance.getUpdateDate());

    return form;
  }

  AccountSourceForm setAccountSourceForm(AccountSource accountSource) {
    AccountSourceForm form = new AccountSourceForm();

    form.setAccountId(accountSource.getAccountSourceId());
    form.setAccountName(accountSource.getAccountName());
    form.setBranchName(accountSource.getBranchName());
    form.setEntryDate(accountSource.getEntryDate());
    form.setUpdateDate(accountSource.getUpdateDate());

    return form;
  }

  AccountDestinationForm setAccountDestinationForm(AccountDestination accountDestination) {
    AccountDestinationForm form = new AccountDestinationForm();

    form.setAccountId(accountDestination.getAccountDestinationId());
    form.setAccountName(accountDestination.getAccountName());
    form.setBranchName(accountDestination.getBranchName());
    form.setEntryDate(accountDestination.getEntryDate());
    form.setUpdateDate(accountDestination.getUpdateDate());

    return form;
  }

  @ModelAttribute("specificationGroupForm")
  SpecificationGroupForm setUpSpecificationGroupForm() {
    SpecificationGroupForm form = new SpecificationGroupForm();

    User user = userService.findById(Long.parseLong("2"));
    form.setUser(user);

    Shop shop = shopService.findById(Long.parseLong("1"));
    form.setShopForm(setShopForm(shopService.findById(Long.parseLong("1"))));

    form.setReceivingAndPaymentType(Long.parseLong("1"));

    form.setAccountAndBalanceForm(setUpAccountAndBalanceForm());

    form.setReceivingAndPaymentDate(LocalDate.now());
    form.setReceivingAndPaymentTime(LocalTime.now());

    return form;
  }

  @ModelAttribute("AccountAndBalanceForm")
  AccountAndBalanceForm setUpAccountAndBalanceForm() {
    AccountAndBalanceForm form = new AccountAndBalanceForm();

    form.setAccountSourceForm(setUpAccountSourceForm());
    form.getAccountSourceForm().setAccountId(Long.parseLong("1"));

    form.setAccountDestinationForm(setUpAccountDestinationForm());
    form.getAccountDestinationForm().setAccountId(Long.parseLong("1"));

    return form;
  }

  @ModelAttribute("AccountSourceForm")
  AccountSourceForm setUpAccountSourceForm() {
    AccountSourceForm form = new AccountSourceForm();
    AccountSource accountSource = accountSourceService.getById(Long.parseLong("1"));

    form.setAccountId(accountSource.getAccountSourceId());
    form.setAccountName(accountSource.getAccountName());
    form.setBranchName(accountSource.getBranchName());
    form.setEntryDate(accountSource.getEntryDate());

    return form;
  }

  @ModelAttribute("AccountDestinationForm")
  AccountDestinationForm setUpAccountDestinationForm() {
    AccountDestinationForm form = new AccountDestinationForm();
    AccountDestination accountDestination
        = accountDestinationService.getById(Long.parseLong("1"));

    form.setAccountId(accountDestination.getAccountDestinationId());
    form.setAccountName(accountDestination.getAccountName());
    form.setBranchName(accountDestination.getBranchName());
    form.setEntryDate(accountDestination.getEntryDate());

    return form;
  }

  @GetMapping("/spec")
  String addSpecificationGroup(Model model) {

    // User user = userService.findById(Long.parseLong("2"));

    AccountAndBalance accountAndBalance = new AccountAndBalance();
    AccountSource accountSource
        = accountSourceService.getById(Long.parseLong("1"));
    AccountDestination accountDestination
        = accountDestinationService.getById(Long.parseLong("1"));
    accountAndBalance.setAccountSourceId(Long.parseLong("1"));
    accountAndBalance.setAccountDestinationId(Long.parseLong("1"));
    accountAndBalance.setEntryDate(LocalDateTime.now());

    accountAndBalanceService.saveAndFlush(accountAndBalance);

    SpecificationGroup specificationGroup = new SpecificationGroup();
    specificationGroup.setUser(user);
    specificationGroup.setShopId(Long.parseLong("1"));
    Shop shop = shopService.findById(specificationGroup.getShopId());
    specificationGroup.setReceivingAndPaymentDate(LocalDate.now());
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH");
    specificationGroup.setBalanceId(Long.parseLong("1"));
    LocalTime localTime = LocalTime.now();
    String nowHour = formatter.format(localTime);

    if (nowHour.length() == 1) {
      nowHour = "0" + nowHour + ":00";
    } else {
      nowHour = nowHour + ":00";
    }
    specificationGroup.setReceivingAndPaymentTime(LocalTime.parse(nowHour));
    specificationGroup.setAccountAndBalanceId(
        accountAndBalance.getAccountAndBalanceId());
    specificationGroup.setAccountAndBalance(accountAndBalance);
    specificationGroup.getAccountAndBalance().setAccountSourceId(
        accountSource.getAccountSourceId());
    specificationGroup.getAccountAndBalance().setAccountDestinationId(
        accountDestination.getAccountDestinationId());
    specificationGroup.setEntryDate(LocalDateTime.now());

    // model.addAttribute("specificationGroup", specificationGroup);
    model.addAttribute(
        "accountAndBalanceId",
        accountAndBalance.getAccountAndBalanceId());
    model.addAttribute("shop", shop);
    model.addAttribute("accountSource", accountSource);
    model.addAttribute("accountDestination", accountDestination);
    model.addAttribute("user", user);

    return "/spec/createGroup";
  }

  @PostMapping("/spec")
  String commitSpecificationGroup(SpecificationGroup specificationGroup, Model model) {
    specificationGroupService.saveAndFlush(specificationGroup);

    return "redirect:/spec";
  }

    @RequestMapping(value = "/{accountAndBalanceId}/{shopId}/{accountSourceId}/{accountDestinationId}/setShop",
      method = RequestMethod.GET)
  String setShopToSpecificationGroup(
      @PathVariable("accountAndBalanceId") Long accountAndBalanceId,
      @PathVariable("shopId") Long shopId,
      @PathVariable("accountSourceId") Long accountSourceId,
      @PathVariable("accountDestinationId") Long accountDestinationId,
      @ModelAttribute SpecificationGroupForm groupForm,
      Model model) {

    // User user = userService.findById(Long.parseLong("2"));

    if (groupForm.getReceivingAndPaymentType() == null) {
      groupForm.setReceivingAndPaymentType(Long.parseLong("1"));
    }
    groupForm.setShopId(shopId);
    Shop shop = shopService.findById(groupForm.getShopId());
    groupForm.setShopForm(setShopForm(shop));


      AccountAndBalanceForm accountAndBalanceForm = new AccountAndBalanceForm();
      AccountAndBalance accountAndBalance
          = accountAndBalanceService.getById(accountAndBalanceId);
      groupForm.setAccountAndBalanceForm(accountAndBalanceForm);

      groupForm.getAccountAndBalanceForm().setAccountSourceId(accountSourceId);
      AccountSource accountSource
          = accountSourceService.getById(accountSourceId);
      groupForm.getAccountAndBalanceForm().setAccountSourceForm(
          setAccountSourceForm(accountSource));

      groupForm.getAccountAndBalanceForm().setAccountDestinationId(accountDestinationId);
      AccountDestination accountDestination
          = accountDestinationService.getById(accountDestinationId);
      groupForm.getAccountAndBalanceForm().setAccountDestinationForm(
          setAccountDestinationForm(accountDestination));

    model.addAttribute("groupForm", groupForm);
    model.addAttribute("shop", shop);
    model.addAttribute("accountSource", accountSource);
    model.addAttribute("accountDestination", accountDestination);
    model.addAttribute("user", user);

    return "/spec/createGroup";
  }

  @GetMapping("/{accountAndBalanceId}/{shopId}/{accountSourceId}/{accountDestinationId}/searchShop")
  String searchShop(
      @PathVariable("accountAndBalanceId") Long accountAndBalanceId,
      @PathVariable("shopId") Long shopId,
      @PathVariable("accountSourceId") Long accountSourceId,
      @PathVariable("accountDestinationId") Long accountDestinationId,
      @ModelAttribute SpecificationGroupForm groupForm,
      Model model) {

    // User user = userService.findById(Long.parseLong("2"));

    List<Shop> shops = shopService.findAll();
    AccountSource accountSource
        = accountSourceService.getById(accountSourceId);
    AccountDestination accountDestination
        = accountDestinationService.getById(accountDestinationId);
    model.addAttribute("accountAndBalanceId", accountAndBalanceId);
    model.addAttribute("shops", shops);
    model.addAttribute("accountSource", accountSource);
    model.addAttribute("accountDestination", accountDestination);
    model.addAttribute("user", user);

    return "/shop/showList";
  }


  @RequestMapping(
      value = "/{accountAndBalanceId}/{shopId}/{accountSourceId}/{accountDestinationId}/setAccountSource",
      method = RequestMethod.GET)
  String setAccountSourceToAccountAndBalance(
      @PathVariable("accountAndBalanceId") Long accountAndBalanceId,
      @PathVariable("shopId") Long shopId,
      @PathVariable("accountSourceId") Long accountSourceId,
      @PathVariable("accountDestinationId") Long accountDestinationId,
      @ModelAttribute SpecificationGroupForm groupForm,
      Model model) {

    // User user = userService.findById(Long.parseLong("2"));

    if (groupForm.getReceivingAndPaymentType() == null) {
      groupForm.setReceivingAndPaymentType(Long.parseLong("1"));
    }
    groupForm.setShopId(shopId);
    Shop shop = shopService.findById(groupForm.getShopId());
    groupForm.setShopForm(setShopForm(shop));

    AccountAndBalanceForm accountAndBalanceForm = new AccountAndBalanceForm();
    AccountAndBalance accountAndBalance
        = accountAndBalanceService.getById(accountAndBalanceId);
    groupForm.setAccountAndBalanceForm(accountAndBalanceForm);

    groupForm.getAccountAndBalanceForm().setAccountSourceId(accountSourceId);
    AccountSource accountSource
        = accountSourceService.getById(accountSourceId);
    groupForm.getAccountAndBalanceForm().setAccountSourceForm(
        setAccountSourceForm(accountSource));

    groupForm.getAccountAndBalanceForm().setAccountDestinationId(accountDestinationId);
    AccountDestination accountDestination
        = accountDestinationService.getById(accountDestinationId);
    groupForm.getAccountAndBalanceForm().setAccountDestinationForm(
        setAccountDestinationForm(accountDestination));

    model.addAttribute("groupForm", groupForm);
    model.addAttribute(
        "accountAndBalanceId", accountAndBalanceId);
    model.addAttribute("shop", shop);
    model.addAttribute("accountSource", accountSource);
    model.addAttribute("accountDestination", accountDestination);
    model.addAttribute("user", user);

    return "/spec/createGroup";
  }

  @RequestMapping(
      value = "/{accountAndBalanceId}/{shopId}/{accountSourceId}/{accountDestinationId}/setAccountDestination",
      method = RequestMethod.GET)
  String setAccountDestinationToAccountAndBalance(
      @PathVariable("accountAndBalanceId") Long accountAndBalanceId,
      @PathVariable("shopId") Long shopId,
      @PathVariable("accountSourceId") Long accountSourceId,
      @PathVariable("accountDestinationId") Long accountDestinationId,
      @ModelAttribute SpecificationGroupForm groupForm,
      Model model) {

    // User user = userService.findById(Long.parseLong("2"));

    if (groupForm.getReceivingAndPaymentType() == null) {
      groupForm.setReceivingAndPaymentType(Long.parseLong("1"));
    }

    groupForm.setShopId(shopId);
    Shop shop = shopService.findById(groupForm.getShopId());
    groupForm.setShopForm(setShopForm(shop));

    AccountAndBalanceForm accountAndBalanceForm = new AccountAndBalanceForm();
    AccountAndBalance accountAndBalance
        = accountAndBalanceService.getById(accountAndBalanceId);
    groupForm.setAccountAndBalanceForm(accountAndBalanceForm);

    groupForm.getAccountAndBalanceForm().setAccountSourceId(accountSourceId);
    AccountSource accountSource
        = accountSourceService.getById(accountSourceId);
    groupForm.getAccountAndBalanceForm().setAccountSourceForm(
        setAccountSourceForm(accountSource));

    groupForm.getAccountAndBalanceForm().setAccountDestinationId(accountDestinationId);
    AccountDestination accountDestination
        = accountDestinationService.getById(accountDestinationId);
    groupForm.getAccountAndBalanceForm().setAccountDestinationForm(
        setAccountDestinationForm(accountDestination));

    model.addAttribute("groupForm", groupForm);
    model.addAttribute("accountAndBalanceId", accountAndBalanceId);
    model.addAttribute("shop", shop);
    model.addAttribute("accountSource", accountSource);
    model.addAttribute("accountDestination", accountDestination);
    model.addAttribute("user", user);

    return "/spec/createGroup";
  }

  @GetMapping("/{accountAndBalanceId}/{shopId}/{accountSourceId}/{accountDestinationId}/searchAccountSource")
  String searchAccountSource(
      @PathVariable("accountAndBalanceId") Long accountAndBalanceId,
      @PathVariable("shopId") Long shopId,
      @PathVariable("accountSourceId") Long accountSourceId,
      @PathVariable("accountDestinationId") Long accountDestinationId,
      @ModelAttribute SpecificationGroupForm groupForm,
      Model model) {
    AccountAndBalance accountAndBalance
        = accountAndBalanceService.getById(accountAndBalanceId);
    Shop shop = shopService.findById(shopId);
    List<AccountSource> accountSources = accountSourceService.findAll();
    AccountDestination accountDestination
        = accountDestinationService.getById(accountDestinationId);

    model.addAttribute("accountAndBalanceId", accountAndBalanceId);
    model.addAttribute("shop", shop);
    model.addAttribute("accountSources", accountSources);
    model.addAttribute("accountDestination", accountDestination);

    return "/account/showSourceList";
  }

  @GetMapping("/{accountAndBalanceId}/{shopId}/{accountSourceId}/{accountDestinationId}/searchAccountDestination")
  String searchAccountDestination(
      @PathVariable("accountAndBalanceId") Long accountAndBalanceId,
      @PathVariable("shopId") Long shopId,
      @PathVariable("accountSourceId") Long accountSourceId,
      @PathVariable("accountDestinationId") Long accountDestinationId,
      @ModelAttribute SpecificationGroupForm groupForm,
      Model model) {
    AccountAndBalance accountAndBalance
        = accountAndBalanceService.getById(accountAndBalanceId);
    Shop shop = shopService.findById(shopId);
    AccountSource accountSource
        = accountSourceService.getById(accountSourceId);
    List<AccountDestination> accountDestinations = accountDestinationService.findAll();

    model.addAttribute("accountAndBalanceId", accountAndBalanceId);
    model.addAttribute("shop", shop);
    model.addAttribute("accountSource", accountSource);
    model.addAttribute("accountDestinations", accountDestinations);

    return "/account/showDestinationList";
  }
}
