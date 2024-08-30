package org.panda.systems.kakeipon.app.spec;

import org.panda.systems.kakeipon.app.common.AccountAndBalanceForm;
import org.panda.systems.kakeipon.app.common.BalanceTypeForm;
import org.panda.systems.kakeipon.domain.model.common.*;
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
  BalanceTypeService balanceTypeService;
  @Autowired
  AccountAndBalanceService accountAndBalanceService;
  @Autowired
  AccountSourceService accountSourceService;
  @Autowired
  AccountDestinationService accountDestinationService;
  @Autowired
  User user;

  @ModelAttribute("specificationGroupForm")
  SpecificationGroupForm setUpSpecificationGroupForm() {
    SpecificationGroupForm form = new SpecificationGroupForm();

    User user = userService.findById(Long.parseLong("2"));
    form.setUserToForm(user);

    Shop shop = shopService.findById(Long.parseLong("1"));
    form.setShopToForm(shop);

    BalanceType balanceType = balanceTypeService.findById(Long.parseLong("1"));
    form.setBalanceTypeToForm(balanceType);

    AccountAndBalance accountAndBalance = new AccountAndBalance();
    form.setAccountAndBalanceToForm(accountAndBalance);

    form.setReceivingAndPaymentDate(LocalDate.now());
    form.setReceivingAndPaymentTime(LocalTime.now());

    return form;
  }

//  ShopForm setShopForm(Shop shop) {
//    ShopForm form = new ShopForm();
//    form.setShopId(shop.getShopId());
//    form.setShopName(shop.getShopName());
//    form.setBranchName(shop.getBranchName());
//    form.setShopUrl(shop.getShopUrl());
//    form.setPhoneNumber(shop.getPhoneNumber());
//    form.setEmail(shop.getEmail());
//    form.setOpenShopDate(shop.getOpenShopDate());
//    form.setCloseShopDate(shop.getCloseShopDate());
//    form.setShopMemo(shop.getShopMemo());
//    form.setEntryDate(shop.getEntryDate());
//    form.setUpdateDate(shop.getUpdateDate());
//
//    return form;
//  }

//  AccountAndBalanceForm setAccountAndBalanceForm(AccountAndBalance accountAndBalance) {
//    AccountAndBalanceForm form = new AccountAndBalanceForm();
//    form.setAccountAndBalanceId(accountAndBalance.getAccountAndBalanceId());
//
//    form.setAccountSourceId(accountAndBalance.getAccountSourceId());
//    form.setAccountDestinationId(accountAndBalance.getAccountDestinationId());
//
//    form.setEntryDate(accountAndBalance.getEntryDate());
//    form.setUpdateDate(accountAndBalance.getUpdateDate());
//
//    return form;
//  }

//  @ModelAttribute("AccountAndBalanceForm")
//  AccountAndBalanceForm setUpAccountAndBalanceForm() {
//    AccountAndBalanceForm form = new AccountAndBalanceForm();
//
//    form.setAccountSourceForm(setUpAccountSourceForm());
//    form.getAccountSourceForm().setAccountId(Long.parseLong("1"));
//
//    form.setAccountDestinationForm(setUpAccountDestinationForm());
//    form.getAccountDestinationForm().setAccountId(Long.parseLong("1"));
//
//    return form;
//  }

//  AccountSourceForm setAccountSourceForm(AccountSource accountSource) {
//    AccountSourceForm form = new AccountSourceForm();
//
//    form.setAccountId(accountSource.getAccountSourceId());
//    form.setAccountName(accountSource.getAccountName());
//    form.setBranchName(accountSource.getBranchName());
//    form.setEntryDate(accountSource.getEntryDate());
//    form.setUpdateDate(accountSource.getUpdateDate());
//
//    return form;
//  }

//  @ModelAttribute("AccountSourceForm")
//  AccountSourceForm setUpAccountSourceForm() {
//    AccountSourceForm form = new AccountSourceForm();
//    AccountSource accountSource = accountSourceService.getById(Long.parseLong("1"));
//
//    form.setAccountId(accountSource.getAccountSourceId());
//    form.setAccountName(accountSource.getAccountName());
//    form.setBranchName(accountSource.getBranchName());
//    form.setEntryDate(accountSource.getEntryDate());
//
//    return form;
//  }

//  AccountDestinationForm setAccountDestinationForm(AccountDestination accountDestination) {
//    AccountDestinationForm form = new AccountDestinationForm();
//
//    form.setAccountId(accountDestination.getAccountDestinationId());
//    form.setAccountName(accountDestination.getAccountName());
//    form.setBranchName(accountDestination.getBranchName());
//    form.setEntryDate(accountDestination.getEntryDate());
//    form.setUpdateDate(accountDestination.getUpdateDate());
//
//    return form;
//  }

//  @ModelAttribute("AccountDestinationForm")
//  AccountDestinationForm setUpAccountDestinationForm() {
//    AccountDestinationForm form = new AccountDestinationForm();
//    AccountDestination accountDestination
//        = accountDestinationService.getById(Long.parseLong("1"));
//
//    form.setAccountDestinationId(accountDestination.getAccountDestinationId());
//    form.setAccountName(accountDestination.getAccountName());
//    form.setBranchName(accountDestination.getBranchName());
//    form.setEntryDate(accountDestination.getEntryDate());
//
//    return form;
//  }

  @GetMapping("/spec")
  String addSpecificationGroup(Model model) {

    AccountAndBalance accountAndBalance = new AccountAndBalance();
    AccountSource accountSource
        = accountSourceService.getById(Long.parseLong("1"));
    AccountDestination accountDestination
        = accountDestinationService.getById(Long.parseLong("1"));
    accountAndBalance.setAccountSourceId(Long.parseLong("1"));
    accountAndBalance.setAccountDestinationId(Long.parseLong("1"));
    accountAndBalance.setEntryDate(LocalDateTime.now());

    accountAndBalanceService.saveAndFlush(accountAndBalance);

    SpecificationGroupForm specificationGroupForm = new SpecificationGroupForm();

    User user = userService.findById(Long.parseLong("2"));
    specificationGroupForm.setUserId(user.getUserId());
    specificationGroupForm.setUserToForm(user);

    specificationGroupForm.setShopId(Long.parseLong("1"));
    Shop shop = shopService.findById(specificationGroupForm.getShopId());
    specificationGroupForm.setShopToForm(shop);

    specificationGroupForm.setReceivingAndPaymentDate(LocalDate.now());
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH");
    specificationGroupForm.setBalanceTypeId(Long.parseLong("1"));
    LocalTime localTime = LocalTime.now();
    String nowHour = formatter.format(localTime);

    if (nowHour.length() == 1) {
      nowHour = "0" + nowHour + ":00";
    } else {
      nowHour = nowHour + ":00";
    }
    specificationGroupForm.setReceivingAndPaymentTime(LocalTime.parse(nowHour));
    specificationGroupForm.setAccountAndBalanceToForm(accountAndBalance);
    specificationGroupForm.getAccountAndBalanceForm().setAccountSourceId(
        accountSource.getAccountSourceId());
    specificationGroupForm.getAccountAndBalanceForm().setAccountDestinationId(
        accountDestination.getAccountDestinationId());
    specificationGroupForm.setEntryDate(LocalDateTime.now());

    model.addAttribute("specificationGroupForm", specificationGroupForm);
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
  String commitSpecificationGroup(
    @ModelAttribute SpecificationGroupForm specificationGroupForm,
//    @ModelAttribute Long balanceTypeId,
//    @ModelAttribute Long accountAndBalanceId,
//    @ModelAttribute Shop shop,
//    @ModelAttribute AccountSource accountSource,
//    @ModelAttribute AccountDestination accountDestination,
//    @ModelAttribute User user,
    Model model) {

//    System.out.println("balanceTypeId : " + balanceTypeId);
    System.out.println("specificationGroupForm.getBalanceTypeId() : " + specificationGroupForm.getBalanceTypeId());
    System.out.println("specificationGroupForm.getBalanceTypeForm().getBalanceTypeId() : " + specificationGroupForm.getBalanceTypeForm().getBalanceTypeId());
    SpecificationGroup specificationGroup
        = SpecificationGroupForm.toEntity(specificationGroupForm);
//    System.out.println(
//        "specificationGroupForm.getBalanceTypeId() : "
//            + specificationGroupForm.getBalanceTypeId());
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
      @ModelAttribute BalanceTypeForm balanceTypeForm,
      Model model) {

    // User user = userService.findById(Long.parseLong("2"));

    groupForm.setShopId(shopId);
    Shop shop = shopService.findById(groupForm.getShopId());
    groupForm.setShopToForm(shop);

    groupForm.setBalanceTypeId(balanceTypeForm.getBalanceTypeId());
    BalanceType balanceType = balanceTypeService.findById(groupForm.getBalanceTypeId());
    groupForm.setBalanceTypeToForm(balanceType);

    AccountAndBalanceForm accountAndBalanceForm = new AccountAndBalanceForm();
    AccountAndBalance accountAndBalance
        = accountAndBalanceService.getById(accountAndBalanceId);
    groupForm.setAccountAndBalanceToForm(accountAndBalance);

    groupForm.getAccountAndBalanceForm().setAccountSourceId(accountSourceId);
      AccountSource accountSource
          = accountSourceService.getById(accountSourceId);
    groupForm.getAccountAndBalanceForm().setAccountSourceToForm(accountSource);

    groupForm.getAccountAndBalanceForm().setAccountDestinationId(accountDestinationId);
      AccountDestination accountDestination
          = accountDestinationService.getById(accountDestinationId);
    groupForm.getAccountAndBalanceForm().setAccountDestinationToForm(
        accountDestination);

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
      @ModelAttribute BalanceTypeForm balanceTypeForm,
      Model model) {

    groupForm.setShopId(shopId);
    Shop shop = shopService.findById(groupForm.getShopId());
    groupForm.setShopToForm(shop);

    groupForm.setBalanceTypeId(balanceTypeForm.getBalanceTypeId());
    BalanceType balanceType = balanceTypeService.findById(groupForm.getBalanceTypeId());
    groupForm.setBalanceTypeToForm(balanceType);

    AccountAndBalanceForm accountAndBalanceForm = new AccountAndBalanceForm();
    AccountAndBalance accountAndBalance
        = accountAndBalanceService.getById(accountAndBalanceId);
    groupForm.setAccountAndBalanceForm(accountAndBalanceForm);

    groupForm.getAccountAndBalanceForm().setAccountSourceId(accountSourceId);
    AccountSource accountSource
        = accountSourceService.getById(accountSourceId);
    groupForm.getAccountAndBalanceForm().setAccountSourceToForm(accountSource);

    groupForm.getAccountAndBalanceForm().setAccountDestinationId(accountDestinationId);
    AccountDestination accountDestination
        = accountDestinationService.getById(accountDestinationId);
    groupForm.getAccountAndBalanceForm().setAccountDestinationToForm(accountDestination);

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
      @ModelAttribute BalanceTypeForm balanceTypeForm,
      Model model) {

    // User user = userService.findById(Long.parseLong("2"));

    groupForm.setShopId(shopId);
    Shop shop = shopService.findById(groupForm.getShopId());
    groupForm.setShopToForm(shop);

    groupForm.setBalanceTypeId(balanceTypeForm.getBalanceTypeId());
    BalanceType balanceType = balanceTypeService.findById(groupForm.getBalanceTypeId());
    groupForm.setBalanceTypeToForm(balanceType);

    AccountAndBalanceForm accountAndBalanceForm = new AccountAndBalanceForm();
    AccountAndBalance accountAndBalance
        = accountAndBalanceService.getById(accountAndBalanceId);
    groupForm.setAccountAndBalanceForm(accountAndBalanceForm);

    groupForm.getAccountAndBalanceForm().setAccountSourceId(accountSourceId);
    AccountSource accountSource
        = accountSourceService.getById(accountSourceId);
    groupForm.getAccountAndBalanceForm().setAccountSourceToForm(accountSource);

    groupForm.getAccountAndBalanceForm().setAccountDestinationId(accountDestinationId);
    AccountDestination accountDestination
        = accountDestinationService.getById(accountDestinationId);
    groupForm.getAccountAndBalanceForm().setAccountDestinationToForm(
        accountDestination);

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
