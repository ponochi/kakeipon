package org.panda.systems.kakeipon.app.spec;

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

  @ModelAttribute("specificationGroupForm")
  SpecificationGroupForm setUpSpecificationGroupForm() {
    SpecificationGroupForm form = new SpecificationGroupForm();
    form.setReceivingAndPaymentType(Long.parseLong("1"));
    return form;
  }

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
    SpecificationGroup specificationGroup = new SpecificationGroup();
//    specificationGroup.setUserId(Long.parseLong("2"));
    User user = userService.findById(Long.parseLong("2"));
    specificationGroup.setUser(user);
    specificationGroup.setShopId(Long.parseLong("1"));
    specificationGroup.setReceivingAndPaymentDate(LocalDate.now());
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH");
    specificationGroup.setBalanceId(Long.parseLong("1"));
    LocalTime localTime = LocalTime.now();
    String nowHour = formatter.format(localTime);

    if (nowHour.length() == 1) {
      nowHour = "0" + nowHour + ":00:00";
    } else {
      nowHour = nowHour + ":00:00";
    }
    specificationGroup.setReceivingAndPaymentTime(LocalTime.parse(nowHour));
    specificationGroup.setAccountAndBalanceId(
        accountAndBalance.getAccountAndBalanceId());
    specificationGroup.setEntryDate(LocalDateTime.now());

//    specificationGroupService.saveAndFlush(specificationGroup);

    model.addAttribute("specificationGroup", specificationGroup);
    model.addAttribute("user", user);
    model.addAttribute("accountSource", accountSource);
    model.addAttribute("accountDestination", accountDestination);
    model.addAttribute(
        "accountAndBalanceId",
        accountAndBalance.getAccountAndBalanceId());
    return "/spec/createGroup";
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
    if (groupForm.getReceivingAndPaymentType() == null) {
      groupForm.setReceivingAndPaymentType(Long.parseLong("1"));
    }
    User user = userService.findById(Long.parseLong("2"));
    // Shop shop = shopService.findById(shopId);
    groupForm.setShopId(shopId);
    groupForm.setShop(shopService.findById(shopId));
//    Long accountAndBalanceId
//        = groupForm.getAccountAndBalance().getAccountAndBalanceId();
    AccountAndBalance accountAndBalance
        = accountAndBalanceService.getById(accountAndBalanceId);
    if (accountSourceId >= 1) {
      AccountSource accountSource
          = accountSourceService.getById(accountSourceId);
      accountAndBalance.setAccountSourceId(
          accountSource.getAccountSourceId());
    }
    if (accountDestinationId >= 1) {
      AccountDestination accountDestination
          = accountDestinationService.getById(accountDestinationId);
      accountAndBalance.setAccountDestinationId(
          accountDestination.getAccountDestinationId());
    }
    model.addAttribute("groupForm", groupForm);
    model.addAttribute("user", user);
    model.addAttribute(
        "accountSource",
        accountSourceService.getById(accountSourceId));
    model.addAttribute("accountDestination",
        accountDestinationService.getById(accountDestinationId));
    return "/spec/createGroup";
  }

  @GetMapping("/searchShop")
  String searchShopList(
      Model model) {
    List<Shop> shops = shopService.findAll();
    model.addAttribute("shops", shops);
    return "/shop/showList";
  }

  @GetMapping("/{accountAndBalanceId}/{shopId}/{accountSourceId}/{accountDestinationId}/searchShop")
  String searchShop(
      @PathVariable("accountAndBalanceId") Long accountAndBalanceId,
      @PathVariable("shopId") Long shopId,
      @PathVariable("accountSourceId") Long accountSourceId,
      @PathVariable("accountDestinationId") Long accountDestinationId,
//      @ModelAttribute ShopForm shopForm,
      Model model) {
    User user = userService.findById(Long.parseLong("2"));
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
    User user = userService.findById(Long.parseLong("2"));
    if (groupForm.getReceivingAndPaymentType() == null) {
      groupForm.setReceivingAndPaymentType(Long.parseLong("1"));
    }
    Shop shop = shopService.findById(shopId);
    groupForm.setShopId(shopId);
    groupForm.setShop(shopService.findById(shopId));
    AccountAndBalance accountAndBalance
        = accountAndBalanceService.getById(accountAndBalanceId);
    if (accountSourceId >= 1) {
      AccountSource accountSource
          = accountSourceService.getById(accountSourceId);
      accountAndBalance.setAccountSourceId(
          accountSource.getAccountSourceId());
    }
    if (accountDestinationId >= 1) {
      AccountDestination accountDestination
          = accountDestinationService.getById(accountDestinationId);
      accountAndBalance.setAccountDestinationId(
          accountDestination.getAccountDestinationId());
    }
    model.addAttribute("groupForm", groupForm);
    model.addAttribute(
        "accountAndBalanceId", accountAndBalanceId);
    model.addAttribute(
        "accountSource",
        accountSourceService.getById(accountSourceId));
    model.addAttribute(
        "accountDestination",
        accountDestinationService.getById(accountDestinationId));
    model.addAttribute("shop", shop);
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
    User user = userService.findById(Long.parseLong("2"));
    if (groupForm.getReceivingAndPaymentType() == null) {
      groupForm.setReceivingAndPaymentType(Long.parseLong("1"));
    }
    Shop shop = shopService.findById(shopId);
    groupForm.setShopId(shopId);
    groupForm.setShop(shopService.findById(shopId));
    AccountAndBalance accountAndBalance
        = accountAndBalanceService.getById(accountAndBalanceId);
    if (accountSourceId >= 1) {
      AccountSource accountSource
          = accountSourceService.getById(accountSourceId);
      accountAndBalance.setAccountSourceId(
          accountSource.getAccountSourceId());
    }
    if (accountDestinationId >= 1) {
      AccountDestination accountDestination
          = accountDestinationService.getById(accountDestinationId);
      accountAndBalance.setAccountDestinationId(
          accountDestination.getAccountDestinationId());
    }
    model.addAttribute("groupForm", groupForm);
    model.addAttribute(
        "accountAndBalanceId", accountAndBalanceId);
    model.addAttribute(
        "accountSource",
        accountSourceService.getById(accountSourceId));
    model.addAttribute(
        "accountDestination",
        accountDestinationService.getById(accountDestinationId));
    model.addAttribute("shop", shop);
    model.addAttribute("user", user);
    return "/spec/createGroup";
  }

  @GetMapping("/{accountAndBalanceId}/searchAccountSource")
  String searchAccountSourceList(
      @PathVariable("accountAndBalanceId") Long accountAndBalanceId,
      Model model) {
    List<AccountSource> accountSources = accountSourceService.findAll();
    model.addAttribute(
        "accountSources", accountSources);
    model.addAttribute(
        "accountAndBalanceId", accountAndBalanceId);
    return "/account/showSourceList";
  }

  @GetMapping("/{accountAndBalanceId}/{shopId}/{accountSourceId}/{accountDestinationId}/searchAccountSource")
  String searchAccountSource(
      @PathVariable("accountAndBalanceId") Long accountAndBalanceId,
      @PathVariable("shopId") Long shopId,
      @PathVariable("accountSourceId") Long accountSourceId,
      @PathVariable("accountDestinationId") Long accountDestinationId,
      Model model) {
    AccountAndBalance accountAndBalance
        = accountAndBalanceService.getById(accountAndBalanceId);
    Shop shop = shopService.findById(shopId);
    List<AccountSource> accountSources = accountSourceService.findAll();
    AccountDestination accountDestination
        = accountDestinationService.getById(accountDestinationId);

    model.addAttribute("accountAndBalanceId", accountAndBalanceId);
    model.addAttribute("shop", shop);
    model.addAttribute(
        "accountSources", accountSources);
    model.addAttribute(
        "accountDestination", accountDestination);
    return "/account/showSourceList";
  }

  @GetMapping("/{accountAndBalanceId}/searchAccountDestination")
  String searchAccountDestinationList(
      @PathVariable("accountAndBalanceId") Long accountAndBalanceId,
      Model model) {
    List<AccountDestination> accountDestinations = accountDestinationService.findAll();
    model.addAttribute(
        "accountDestinations", accountDestinations);
    model.addAttribute(
        "accountAndBalanceId", accountAndBalanceId);
    return "/account/showDestinationList";
  }

  @GetMapping("/{accountAndBalanceId}/{shopId}/{accountSourceId}/{accountDestinationId}/searchAccountDestination")
  String searchAccountDestination(
      @PathVariable("accountAndBalanceId") Long accountAndBalanceId,
      @PathVariable("shopId") Long shopId,
      @PathVariable("accountSourceId") Long accountSourceId,
      @PathVariable("accountDestinationId") Long accountDestinationId,
      Model model) {
    AccountAndBalance accountAndBalance
        = accountAndBalanceService.getById(accountAndBalanceId);
    Shop shop = shopService.findById(shopId);
    AccountSource accountSource
        = accountSourceService.getById(accountSourceId);
    List<AccountDestination> accountDestinations = accountDestinationService.findAll();

    model.addAttribute("accountAndBalanceId", accountAndBalanceId);
    model.addAttribute("shop", shop);
    model.addAttribute(
        "accountSource", accountSource);
    model.addAttribute(
        "accountDestinations", accountDestinations);
    return "/account/showDestinationList";
  }
}
