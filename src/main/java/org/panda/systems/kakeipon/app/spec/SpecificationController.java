package org.panda.systems.kakeipon.app.spec;

import org.panda.systems.kakeipon.app.common.AccountAndBalanceForm;
import org.panda.systems.kakeipon.app.common.BalanceTypeForm;
import org.panda.systems.kakeipon.domain.model.account.AccountDestination;
import org.panda.systems.kakeipon.domain.model.account.AccountSource;
import org.panda.systems.kakeipon.domain.model.common.*;
import org.panda.systems.kakeipon.domain.model.spec.SpecificationGroup;
import org.panda.systems.kakeipon.domain.model.user.Role;
import org.panda.systems.kakeipon.domain.model.user.User;
import org.panda.systems.kakeipon.domain.service.account.AccountDestinationService;
import org.panda.systems.kakeipon.domain.service.account.AccountSourceService;
import org.panda.systems.kakeipon.domain.service.common.*;
import org.panda.systems.kakeipon.domain.service.currency.CurrencyService;
import org.panda.systems.kakeipon.domain.service.spec.SpecificationGroupService;
import org.panda.systems.kakeipon.domain.service.spec.SpecificationService;
import org.panda.systems.kakeipon.domain.service.user.RoleService;
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
  RoleService roleService;
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
  SpecificationService specService;
  @Autowired
  CurrencyService currencyService;
  @Autowired
  UnitService unitService;
  @Autowired
  TaxTypeService taxTypeService;
  @Autowired
  User user;

  @ModelAttribute("specificationGroupForm")
  SpecificationGroupForm setUpSpecificationGroupForm() {
    SpecificationGroupForm form = new SpecificationGroupForm();

    User user = userService.findById(Long.parseLong("2"));
    Role role = roleService.findByRoleId(user.getRoleId());
    form.setUserId(user.getUserId());
    form.setUserToForm(
        user.getUserId(),
        user.getNickName(),
        user.getLastName(),
        user.getFirstName(),
        user.getBirthDay(),
        user.getPassword(),
        user.getPhoneNumber(),
        user.getEmail(),
        user.getRoleId(),
        role.getRoleName(),
        user.getEntryDate(),
        user.getUpdateDate());

    BalanceTypeForm balanceTypeForm = new BalanceTypeForm();
    balanceTypeForm.setBalanceTypeId(Long.parseLong("1"));
    form.setBalanceTypeForm(balanceTypeForm);

    AccountAndBalanceForm accountAndBalanceForm
        = new AccountAndBalanceForm();
    accountAndBalanceForm.setAccountAndBalanceId(
        accountAndBalanceService.getMaxAccountAndBalanceId());
    form.setAccountAndBalanceForm(accountAndBalanceForm);

    return form;
  }

  @GetMapping("/spec")
  String addSpecificationGroup(
      @ModelAttribute SpecificationGroupForm groupForm,
      Model model) {

    AccountAndBalance accountAndBalance = new AccountAndBalance();
    AccountSource accountSource
        = accountSourceService.findById(Long.parseLong("1"));
    AccountDestination accountDestination
        = accountDestinationService.findById(Long.parseLong("1"));
    accountAndBalance.setAccountSourceId(Long.parseLong("1"));
    accountAndBalance.setAccountDestinationId(Long.parseLong("1"));
    accountAndBalance.setEntryDate(LocalDateTime.now());

    accountAndBalanceService.saveAndFlush(accountAndBalance);

    groupForm = new SpecificationGroupForm();

    User user = userService.findById(Long.parseLong("2"));
    Role role = roleService.findByRoleId(user.getRoleId());
    groupForm.setUserId(user.getUserId());
    groupForm.setUserToForm(
        user.getUserId(),
        user.getNickName(),
        user.getLastName(),
        user.getFirstName(),
        user.getBirthDay(),
        user.getPassword(),
        user.getPhoneNumber(),
        user.getEmail(),
        user.getRoleId(),
        role.getRoleName(),
        user.getEntryDate(),
        user.getUpdateDate());

    groupForm.setShopId(Long.parseLong("1"));
    Shop shop = shopService.findById(groupForm.getShopId());
    groupForm.setShopToForm(shop);

    groupForm.setReceivingAndPaymentDate(LocalDate.now());
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH");

    LocalTime localTime = LocalTime.now();
    String nowHour = formatter.format(localTime);

    if (nowHour.length() == 1) {
      nowHour = "0" + nowHour + ":00";
    } else {
      nowHour = nowHour + ":00";
    }
    groupForm.setReceivingAndPaymentTime(LocalTime.parse(nowHour));

    groupForm.setBalanceTypeId(Long.parseLong("1"));
    List<BalanceType> balanceTypes
        = balanceTypeService.findAll();
    groupForm.setBalanceTypeToForm(balanceTypes.get(0));

    groupForm.setAccountAndBalanceToForm(accountAndBalance);
    groupForm.getAccountAndBalanceForm().setAccountSourceId(
        accountSource.getAccountSourceId());
    groupForm.getAccountAndBalanceForm().setAccountDestinationId(
        accountDestination.getAccountDestinationId());
    groupForm.setEntryDate(LocalDateTime.now());

//    specificationGroupService.saveAndFlush(
//        groupForm.toEntity(groupForm));

    SpecificationForm specificationForm = new SpecificationForm();
    specificationForm.setSpecificationGroupId(groupForm.getSpecificationGroupId());
    specificationForm.setUserId(groupForm.getUserId());

    model.addAttribute("specificationGroupForm", groupForm);
    model.addAttribute("specificationForm", specificationForm);
    model.addAttribute(
        "accountAndBalanceId",
        accountAndBalance.getAccountAndBalanceId());
    model.addAttribute("shop", shop);
    model.addAttribute("accountSource", accountSource);
    model.addAttribute("accountDestination", accountDestination);
    model.addAttribute("user", user);
    model.addAttribute("balanceTypes", balanceTypes);

    return "/spec/createGroup";
  }

  @PostMapping("/spec")
  String commitSpecificationGroup(
      @ModelAttribute SpecificationGroupForm groupForm,
      Model model) {

    SpecificationGroup specificationGroup
        = SpecificationGroupForm.toEntity(groupForm);
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

    groupForm.setShopId(shopId);
    Shop shop = shopService.findById(groupForm.getShopId());
    groupForm.setShopToForm(shop);

    List<BalanceType> balanceTypes = balanceTypeService.findAll();

    groupForm.setBalanceTypeId(
        groupForm.getBalanceTypeForm().getBalanceTypeId());
    BalanceType balanceType
        = balanceTypeService.findById(groupForm.getBalanceTypeId());
    groupForm.setBalanceTypeToForm(balanceType);

    groupForm.setAccountAndBalanceId(
        groupForm.getAccountAndBalanceForm().getAccountAndBalanceId());
    AccountAndBalance accountAndBalance
        = accountAndBalanceService.getById(accountAndBalanceId);
    groupForm.setAccountAndBalanceToForm(accountAndBalance);

    groupForm.getAccountAndBalanceForm().setAccountSourceId(accountSourceId);
    AccountSource accountSource
        = accountSourceService.findById(accountSourceId);
    groupForm.getAccountAndBalanceForm().setAccountSourceToForm(accountSource);

    groupForm.getAccountAndBalanceForm().setAccountDestinationId(
        accountDestinationId);
    AccountDestination accountDestination
        = accountDestinationService.findById(accountDestinationId);
    groupForm.getAccountAndBalanceForm().setAccountDestinationToForm(
        accountDestination);

    model.addAttribute("groupForm", groupForm);
    model.addAttribute("shop", shop);
    model.addAttribute("accountSource", accountSource);
    model.addAttribute("accountDestination", accountDestination);
    model.addAttribute("user", user);
    model.addAttribute("balanceTypes", balanceTypes);

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

    List<Shop> shops = shopService.findAll();
    AccountSource accountSource
        = accountSourceService.findById(accountSourceId);
    AccountDestination accountDestination
        = accountDestinationService.findById(accountDestinationId);

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

    groupForm.setShopId(shopId);
    Shop shop = shopService.findById(groupForm.getShopId());
    groupForm.setShopToForm(shop);

    List<BalanceType> balanceTypes = balanceTypeService.findAll();

    groupForm.setBalanceTypeId(
        groupForm.getBalanceTypeForm().getBalanceTypeId());
    BalanceType balanceType
        = balanceTypeService.findById(groupForm.getBalanceTypeId());
    groupForm.setBalanceTypeToForm(balanceType);

    groupForm.setAccountAndBalanceId(
        groupForm.getAccountAndBalanceForm().getAccountAndBalanceId());
    AccountAndBalance accountAndBalance
        = accountAndBalanceService.getById(accountAndBalanceId);
    groupForm.setAccountAndBalanceToForm(accountAndBalance);

    groupForm.getAccountAndBalanceForm().setAccountSourceId(accountSourceId);
    AccountSource accountSource
        = accountSourceService.findById(accountSourceId);
    groupForm.getAccountAndBalanceForm().setAccountSourceToForm(accountSource);

    groupForm.getAccountAndBalanceForm().setAccountDestinationId(
        accountDestinationId);
    AccountDestination accountDestination
        = accountDestinationService.findById(accountDestinationId);
    groupForm.getAccountAndBalanceForm().setAccountDestinationToForm(
        accountDestination);

    model.addAttribute("groupForm", groupForm);
    model.addAttribute(
        "accountAndBalanceId", accountAndBalanceId);
    model.addAttribute("shop", shop);
    model.addAttribute("accountSource", accountSource);
    model.addAttribute(
        "accountDestination", accountDestination);
    model.addAttribute("user", user);
    model.addAttribute("balanceTypes", balanceTypes);

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

    groupForm.setShopId(shopId);
    Shop shop = shopService.findById(groupForm.getShopId());
    groupForm.setShopToForm(shop);

    List<BalanceType> balanceTypes = balanceTypeService.findAll();

    groupForm.setBalanceTypeId(
        groupForm.getBalanceTypeForm().getBalanceTypeId());
    BalanceType balanceType
        = balanceTypeService.findById(groupForm.getBalanceTypeId());
    groupForm.setBalanceTypeToForm(balanceType);

    groupForm.setAccountAndBalanceId(
        groupForm.getAccountAndBalanceForm().getAccountAndBalanceId());
    AccountAndBalance accountAndBalance
        = accountAndBalanceService.getById(accountAndBalanceId);
    groupForm.setAccountAndBalanceToForm(accountAndBalance);

    groupForm.getAccountAndBalanceForm().setAccountSourceId(accountSourceId);
    AccountSource accountSource
        = accountSourceService.findById(accountSourceId);
    groupForm.getAccountAndBalanceForm().setAccountSourceToForm(accountSource);

    groupForm.getAccountAndBalanceForm().setAccountDestinationId(accountDestinationId);
    AccountDestination accountDestination
        = accountDestinationService.findById(accountDestinationId);
    groupForm.getAccountAndBalanceForm().setAccountDestinationToForm(
        accountDestination);

    model.addAttribute("groupForm", groupForm);
    model.addAttribute(
        "accountAndBalanceId", accountAndBalanceId);
    model.addAttribute("shop", shop);
    model.addAttribute("accountSource", accountSource);
    model.addAttribute(
        "accountDestination", accountDestination);
    model.addAttribute("user", user);
    model.addAttribute("balanceTypes", balanceTypes);

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
        = accountDestinationService.findById(accountDestinationId);

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
        = accountSourceService.findById(accountSourceId);
    List<AccountDestination> accountDestinations
        = accountDestinationService.findAll();

    model.addAttribute("accountAndBalanceId", accountAndBalanceId);
    model.addAttribute("shop", shop);
    model.addAttribute("accountSource", accountSource);
    model.addAttribute("accountDestinations", accountDestinations);

    return "/account/showDestinationList";
  }
}
