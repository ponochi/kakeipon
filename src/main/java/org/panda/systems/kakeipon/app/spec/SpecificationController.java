package org.panda.systems.kakeipon.app.spec;

import org.panda.systems.kakeipon.app.account.AccountDestinationForm;
import org.panda.systems.kakeipon.app.account.AccountSourceForm;
import org.panda.systems.kakeipon.app.common.*;
import org.panda.systems.kakeipon.app.currency.CurrencyListForm;
import org.panda.systems.kakeipon.app.shop.ShopForm;
import org.panda.systems.kakeipon.app.user.UserForm;
import org.panda.systems.kakeipon.domain.model.account.AccountDestination;
import org.panda.systems.kakeipon.domain.model.account.AccountSource;
import org.panda.systems.kakeipon.domain.model.common.*;
import org.panda.systems.kakeipon.domain.model.spec.Specification;
import org.panda.systems.kakeipon.domain.model.spec.SpecificationGroup;
import org.panda.systems.kakeipon.domain.model.user.User;
import org.panda.systems.kakeipon.domain.service.account.AccountDestinationService;
import org.panda.systems.kakeipon.domain.service.account.AccountSourceService;
import org.panda.systems.kakeipon.domain.service.common.*;
import org.panda.systems.kakeipon.domain.service.currency.CurrencyListService;
import org.panda.systems.kakeipon.domain.service.spec.SpecificationGroupService;
import org.panda.systems.kakeipon.domain.service.spec.SpecificationService;
import org.panda.systems.kakeipon.domain.service.user.RoleService;
import org.panda.systems.kakeipon.domain.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
  CurrencyListService currencyListService;
  @Autowired
  UnitService unitService;
  @Autowired
  TaxTypeService taxTypeService;
  @Autowired
  TaxRateService taxRateService;
  @Autowired
  User user;

  @GetMapping("/spec")
  String index(
      @ModelAttribute UserForm userForm,
      Model model) {

    List<SpecificationGroupForm> groupForms
        = specificationGroupService.findAllToForm();

    for (SpecificationGroupForm groupForm : groupForms) {
      groupForm.setUserForm(userForm);
      AccountAndBalanceForm accountAndBalanceForm
          = new AccountAndBalanceForm(
          accountAndBalanceService,
          accountSourceService,
          accountDestinationService,
          groupForm.getAccountAndBalanceId(),
          accountAndBalanceService.getById(
              groupForm.getAccountAndBalanceId()).getAccountSourceId(),
          accountAndBalanceService.getById(
              groupForm.getAccountAndBalanceId()).getAccountDestinationId());
      groupForm.setAccountAndBalanceForm(accountAndBalanceForm);
      ShopForm shopForm = new ShopForm(
          shopService,
          groupForm.getShopId());
      groupForm.setShopForm(shopForm);
      BalanceTypeForm balanceTypeForm = new BalanceTypeForm(
          balanceTypeService,
          groupForm.getBalanceTypeId());
      groupForm.setBalanceTypeForm(balanceTypeForm);
    }

    model.addAttribute("specificationGroupForms", groupForms);
    return "/spec/showList";
  }

  @GetMapping(value = "/spec/create/group")
  String createSpecificationGroup(
      @ModelAttribute SpecificationGroupForm groupForm,
      @ModelAttribute SpecificationForm specificationForm,
      @ModelAttribute AccountAndBalanceForm accountAndBalanceForm,
      @ModelAttribute UserForm userForm,
      @ModelAttribute ShopForm shopForm,
      @ModelAttribute BalanceTypeForm balanceTypeForm,
      @ModelAttribute AccountSourceForm accountSourceForm,
      @ModelAttribute AccountDestinationForm accountDestinationForm,
      Model model) {

    groupForm = new SpecificationGroupForm(
        specificationGroupService,
        specificationService,
        accountAndBalanceService,
        accountSourceService,
        accountDestinationService,
        userService,
        roleService,
        shopService,
        balanceTypeService,
        currencyListService,
        unitService,
        taxTypeService,
        taxRateService);
    SpecificationGroup group
        = specificationGroupService.findById(
            specificationGroupService.getMaxGroupId());
    groupForm = groupForm.setSpecificationGroupToForm(
        accountAndBalanceService,
        accountSourceService,
        accountDestinationService,
        group);

    userForm = new UserForm(
        userService,
        roleService,
        Long.parseLong("2"));
    groupForm.setUserId(userForm.getUserId());
    groupForm.setUserForm(userForm);

    shopForm = new ShopForm(shopService, Long.parseLong("1"));
    groupForm.setShopId(shopForm.getShopId());
    groupForm.setShopForm(shopForm);

    specificationGroupService.saveAndFlush(groupForm.toEntity());

    SpecificationGroup specificationGroup = specificationGroupService.findById(
        specificationGroupService.getMaxGroupId());
    groupForm = groupForm.setSpecificationGroupToForm(
        accountAndBalanceService,
        accountSourceService,
        accountDestinationService,
        specificationGroup);

    model.addAttribute("specificationGroupForm", groupForm);
    model.addAttribute("specificationForm", specificationForm);
    model.addAttribute("currencyLists", currencyListService.findAll());
    model.addAttribute("units", unitService.findAll());
    model.addAttribute("taxTypes", taxTypeService.findAll());
    model.addAttribute("taxRates", taxRateService.findAll());
    model.addAttribute(
        "accountAndBalanceId",
        groupForm.getAccountAndBalanceForm().getAccountAndBalanceId());
    model.addAttribute("shopForm", shopForm);
    model.addAttribute(
        "accountSourceForm",
        new AccountSourceForm(
            accountSourceService,
            groupForm.getAccountAndBalanceForm().getAccountSourceId()));
    model.addAttribute(
        "accountDestinationForm",
        new AccountDestinationForm(
            accountDestinationService,
            groupForm.getAccountAndBalanceForm().getAccountDestinationId()));
    model.addAttribute("userForm", userForm);
    model.addAttribute("balanceTypes", balanceTypeService.findAll());

    return "/spec/createGroup";
  }

  @GetMapping(value = "/spec/edit/group/{specificationGroupId}")
  String editSpecificationGroup(
      @PathVariable("specificationGroupId") Long specificationGroupId,
      @ModelAttribute SpecificationGroupForm groupForm,
      @ModelAttribute SpecificationForm specificationForm,
      @ModelAttribute AccountAndBalanceForm accountAndBalanceForm,
      @ModelAttribute UserForm userForm,
      @ModelAttribute ShopForm shopForm,
      @ModelAttribute BalanceTypeForm balanceTypeForm,
      @ModelAttribute AccountSourceForm accountSourceForm,
      @ModelAttribute AccountDestinationForm accountDestinationForm,
      Model model) {

    System.out.println("1>>>> specificationGroupId: "
        + specificationGroupId);
    SpecificationGroup group
        = specificationGroupService.findById(
            specificationGroupId);
    groupForm = groupForm.setSpecificationGroupToForm(
        accountAndBalanceService,
        accountSourceService,
        accountDestinationService,
        group);

    userForm = new UserForm(
        userService,
        roleService,
        Long.parseLong("2"));
    groupForm.setUserId(userForm.getUserId());
    groupForm.setUserForm(userForm);

    System.out.println("1>>>> shopId: " + groupForm.getShopId());
    shopForm = new ShopForm(shopService, groupForm.getShopId());
    System.out.println("2>>>> shopForm: " + shopForm.toString());
    groupForm.setShopId(shopForm.getShopId());
    groupForm.setShopForm(shopForm);

    specificationGroupService.saveAndFlush(groupForm.toEntity());

    SpecificationGroup specificationGroup = specificationGroupService.findById(
        specificationGroupService.getMaxGroupId());
    groupForm = groupForm.setSpecificationGroupToForm(
        accountAndBalanceService,
        accountSourceService,
        accountDestinationService,
        specificationGroup);

    model.addAttribute("specificationGroupForm", groupForm);
    model.addAttribute("specificationForm", specificationForm);
    model.addAttribute("currencyLists", currencyListService.findAll());
    model.addAttribute("units", unitService.findAll());
    model.addAttribute("taxTypes", taxTypeService.findAll());
    model.addAttribute("taxRates", taxRateService.findAll());
    model.addAttribute(
        "accountAndBalanceId",
        groupForm.getAccountAndBalanceForm().getAccountAndBalanceId());
    model.addAttribute("shopForm", shopForm);
    model.addAttribute(
        "accountSourceForm",
        new AccountSourceForm(
            accountSourceService,
            groupForm.getAccountAndBalanceForm().getAccountSourceId()));
    model.addAttribute(
        "accountDestinationForm",
        new AccountDestinationForm(
            accountDestinationService,
            groupForm.getAccountAndBalanceForm().getAccountDestinationId()));
    model.addAttribute("userForm", userForm);
    model.addAttribute("balanceTypes", balanceTypeService.findAll());

    return "/spec/createGroup";
  }

  @GetMapping(value = "/spec/create/detail/{specificationGroupId}")
  String createSpecification(
      @PathVariable("specificationGroupId") Long specificationGroupId,
      @ModelAttribute SpecificationGroupForm specificationGroupForm,
      @ModelAttribute SpecificationForm specificationForm,
      @ModelAttribute AccountAndBalanceForm accountAndBalanceForm,
      @ModelAttribute UserForm userForm,
      @ModelAttribute CurrencyListForm currencyListForm,
      @ModelAttribute UnitForm unitForm,
      @ModelAttribute TaxTypeForm taxTypeForm,
      @ModelAttribute TaxRateForm taxRateForm,
      Model model) {

    specificationForm.setSpecificationGroupId(
        specificationGroupId);

    userForm = new UserForm(
        userService,
        roleService,
        Long.parseLong("2"));
    specificationGroupForm.setUserId(userForm.getUserId());
    specificationGroupForm.setUserForm(userForm);

    model.addAttribute(
        "specificationGroupForm", specificationGroupForm);
    model.addAttribute(
        "specificationForm", specificationForm);
    model.addAttribute(
        "currencyLists", currencyListService.findAll());
    model.addAttribute("units", unitService.findAll());
    model.addAttribute(
        "taxTypes", taxTypeService.findAll());
    model.addAttribute(
        "taxRates", taxRateService.findAll());
    model.addAttribute("userForm", userForm);

    return "/spec/createDetail";
  }

  @PostMapping(value = "/spec/save/detail/{specificationGroupId}")
  String saveDetail(
      @PathVariable("specificationGroupId") Long specificationGroupId,
      @ModelAttribute SpecificationGroupForm specificationGroupForm,
      @ModelAttribute SpecificationForm specificationForm,
      @ModelAttribute AccountAndBalanceForm accountAndBalanceForm,
      @ModelAttribute UserForm userForm,
      @ModelAttribute CurrencyListForm currencyListForm,
      @ModelAttribute UnitForm unitForm,
      @ModelAttribute TaxTypeForm taxTypeForm,
      @ModelAttribute TaxRateForm taxRateForm,
      Model model) {

    if (specificationForm.getEntryDate() == null) {
      specificationForm.setEntryDate(LocalDateTime.now());
    } else {
      specificationForm.setUpdateDate(specificationForm.getEntryDate());
    }

    specificationService.saveAndFlush(specificationForm.toEntity());

    List<SpecificationForm> specificationForms
        = specificationService.findBySpecificationGroupIdToForm(
        specificationGroupForm.getSpecificationGroupId());


    model.addAttribute("specificationGroupForm", specificationGroupForm);
    model.addAttribute("specificationForms", specificationForms);
    model.addAttribute("currencyLists", currencyListService.findAll());
    model.addAttribute("units", unitService.findAll());
    model.addAttribute("taxTypes", taxTypeService.findAll());
    model.addAttribute("taxRates", taxRateService.findAll());
    model.addAttribute("userForm", userForm);

    return "redirect:/spec";
  }

  @PostMapping(value = "/spec/save/group")
  String saveGroup(
      @ModelAttribute SpecificationGroupForm groupForm,
      @ModelAttribute SpecificationForm specificationForm,
      @ModelAttribute Specification specification,
      @ModelAttribute AccountAndBalanceForm accountAndBalanceForm,
      @ModelAttribute UserForm userForm,
      @ModelAttribute ShopForm shopForm,
      Model model) {

    String memo = groupForm.getMemo();
    SpecificationGroup group
        = specificationGroupService.findById(
        specificationGroupService.getMaxGroupId());
    groupForm = groupForm.setSpecificationGroupToForm(
        accountAndBalanceService,
        accountSourceService,
        accountDestinationService,
        group);
    groupForm.setMemo(memo);

    groupForm.setAccountAndBalanceId(
        accountAndBalanceForm.getAccountAndBalanceId());
    groupForm.setAccountAndBalanceForm(accountAndBalanceForm);

    groupForm.setUserId(userForm.getUserId());
    groupForm.setUserForm(userForm);

    groupForm.setShopId(shopForm.getShopId());
    groupForm.setShopForm(shopForm);

    SpecificationGroup specGroup = groupForm.toEntity();
    System.out.println("specGroup: " + specGroup.getMemo());
    specificationGroupService.update(specGroup);

    accountAndBalanceForm.setAccountSourceId(
        groupForm.getAccountAndBalanceForm().getAccountSourceId());
    accountAndBalanceForm.setAccountDestinationId(
        groupForm.getAccountAndBalanceForm().getAccountDestinationId());
    accountAndBalanceService.saveAndFlush(
        accountAndBalanceForm.toEntity());

    List<SpecificationForm> specificationForms
        = specificationService.findBySpecificationGroupIdToForm(
        groupForm.getSpecificationGroupId());

    model.addAttribute("specificationGroupForm", groupForm);
    model.addAttribute("specificationForms", specificationForms);
    model.addAttribute("currencyLists", currencyListService.findAll());
    model.addAttribute("units", unitService.findAll());
    model.addAttribute("taxTypes", taxTypeService.findAll());
    model.addAttribute("taxRates", taxRateService.findAll());
    model.addAttribute(
        "accountAndBalanceId",
        groupForm.getAccountAndBalanceForm().getAccountAndBalanceId());
    model.addAttribute("shopForm", shopService.findById(groupForm.getShopId()));
    model.addAttribute(
        "accountSourceForm",
        new AccountSourceForm(
            accountSourceService,
            groupForm.getAccountAndBalanceForm().getAccountSourceId()));
    model.addAttribute(
        "accountDestinationForm",
        new AccountDestinationForm(
            accountDestinationService,
            groupForm.getAccountAndBalanceForm().getAccountDestinationId()));
    model.addAttribute("user", user);
    model.addAttribute("balanceTypes", balanceTypeService.findAll());

    return "/spec/createGroup";
  }

//  @PostMapping(value = "/spec/save")
//  String saveSpecificationDetail(
//      @ModelAttribute("specificationGroupForm") SpecificationGroupForm groupForm,
//      @ModelAttribute("specificationForm") SpecificationForm form,
//      Model model) {
//
////    for (SpecificationForm form : specificationForms) {
//      form.setSpecificationGroupId(groupForm.getSpecificationGroupId());
//      form.setSpecificationId(
//          specificationService.getMaxSpecificationId(
//              groupForm.getSpecificationGroupId()));
//      form.setUserId(groupForm.getUserId());
//      form.setUpdateDate(LocalDateTime.now());
//
//      specificationService.saveAndFlush(form.toEntity());
////    }
//
////    List<SpecificationForm> specificationForms
////        = specificationService.findBySpecificationGroupIdToForm(
////        groupForm.getSpecificationGroupId());
//
//    model.addAttribute("specificationGroupForm", groupForm);
////    model.addAttribute("specificationForms", specificationForms);
//    model.addAttribute("specificationForm", form);
//    model.addAttribute("currencyLists", currencyService.findAll());
//    model.addAttribute("units", unitService.findAll());
//    model.addAttribute("taxTypes", taxTypeService.findAll());
//    model.addAttribute("taxRates", taxRateService.findAll());
//    model.addAttribute(
//        "accountAndBalanceId",
//        groupForm.getAccountAndBalanceForm().getAccountAndBalanceId());
//    model.addAttribute("shopForm", shopService.findById(groupForm.getShopId()));
//    model.addAttribute(
//        "accountSourceForm",
//        new AccountSourceForm(
//            accountSourceService,
//            groupForm.getAccountAndBalanceForm().getAccountSourceId()));
//    model.addAttribute(
//        "accountDestinationForm",
//        new AccountDestinationForm(
//            accountDestinationService,
//            groupForm.getAccountAndBalanceForm().getAccountDestinationId()));
//    model.addAttribute("user", user);
//    model.addAttribute("balanceTypes", balanceTypeService.findAll());
//
//    return "redirect:/spec/createGroup";
//  }

  @RequestMapping(value = "/{userId}/{accountAndBalanceId}/{shopId}/{accountSourceId}/{accountDestinationId}/setShop",
      method = RequestMethod.GET)
  String setShopToSpecificationGroup(
      @PathVariable("userId") Long userId,
      @PathVariable("accountAndBalanceId") Long accountAndBalanceId,
      @PathVariable("shopId") Long shopId,
      @PathVariable("accountSourceId") Long accountSourceId,
      @PathVariable("accountDestinationId") Long accountDestinationId,
      @ModelAttribute BalanceTypeForm balanceTypeForm,
      @ModelAttribute SpecificationGroupForm groupForm,
      Model model) {

    groupForm.setUserId(userId);
    UserForm userForm = new UserForm(
        userService,
        roleService,
        userId);
    groupForm.setUserForm(userForm);

    ShopForm shopForm = new ShopForm(shopService, shopId);
    groupForm.setShopForm(shopForm);

    List<BalanceType> balanceTypes = balanceTypeService.findAll();

    groupForm.setBalanceTypeId(
        balanceTypeForm.getBalanceTypeId());
    BalanceType balanceType
        = balanceTypeService.findById(groupForm.getBalanceTypeId());
    groupForm.setBalanceTypeToForm(balanceType);

    AccountAndBalance accountAndBalance
        = accountAndBalanceService.getById(accountAndBalanceId);
    groupForm.setAccountAndBalanceId(
        accountAndBalance.getAccountAndBalanceId());
    groupForm.setAccountAndBalanceToForm(accountAndBalance);

    groupForm.getAccountAndBalanceForm().setAccountSourceId(accountSourceId);
    AccountSourceForm accountSourceForm
        = new AccountSourceForm(
        accountSourceService,
        accountSourceId);
    groupForm.getAccountAndBalanceForm().setAccountSourceForm(
        accountSourceForm);

    groupForm.getAccountAndBalanceForm().setAccountDestinationId(
        accountDestinationId);
    AccountDestinationForm accountDestinationForm
        = new AccountDestinationForm(
        accountDestinationService,
        accountDestinationId);
    groupForm.getAccountAndBalanceForm().setAccountDestinationForm(
        accountDestinationForm);

    model.addAttribute("groupForm", groupForm);
    model.addAttribute(
        "accountAndBalanceId", accountAndBalanceId);
    model.addAttribute("shopForm", shopForm);
    model.addAttribute(
        "accountSourceForm",
        accountSourceForm);
    model.addAttribute(
        "accountDestinationForm",
        accountDestinationForm);
    model.addAttribute("user", user);
    model.addAttribute("balanceTypes", balanceTypes);

    return "/spec/createGroup";
  }

  @GetMapping("/{userId}/{accountAndBalanceId}/{shopId}/{accountSourceId}/{accountDestinationId}/searchShop")
  String searchShop(
      @PathVariable("userId") Long userId,
      @PathVariable("accountAndBalanceId") Long accountAndBalanceId,
      @PathVariable("shopId") Long shopId,
      @PathVariable("accountSourceId") Long accountSourceId,
      @PathVariable("accountDestinationId") Long accountDestinationId,
      @ModelAttribute UserForm userForm,
      @ModelAttribute BalanceTypeForm balanceTypeForm,
      @ModelAttribute SpecificationGroupForm specificationGroupForm,
      Model model) {

    List<Shop> shops = shopService.findAll();
    AccountSource accountSource
        = accountSourceService.findById(accountSourceId);
    AccountDestination accountDestination
        = accountDestinationService.findById(accountDestinationId);

    model.addAttribute("specificationGroupForm", specificationGroupForm);
    model.addAttribute("accountAndBalanceId", accountAndBalanceId);
    model.addAttribute("shops", shops);
    model.addAttribute("accountSource", accountSource);
    model.addAttribute("accountDestination", accountDestination);
    model.addAttribute("userForm", userForm);

    return "/shop/showList";
  }


  @RequestMapping(
      value = "/{userId}/{accountAndBalanceId}/{shopId}/{accountSourceId}/{accountDestinationId}/setAccountSource",
      method = RequestMethod.GET)
  String setAccountSourceToAccountAndBalance(
      @PathVariable("userId") Long userId,
      @PathVariable("accountAndBalanceId") Long accountAndBalanceId,
      @PathVariable("shopId") Long shopId,
      @PathVariable("accountSourceId") Long accountSourceId,
      @PathVariable("accountDestinationId") Long accountDestinationId,
      @ModelAttribute BalanceTypeForm balanceTypeForm,
      @ModelAttribute SpecificationGroupForm groupForm,
      Model model) {

    groupForm.setUserId(userId);
    UserForm userForm = new UserForm(
        userService,
        roleService,
        userId);
    groupForm.setUserForm(userForm);

    groupForm.setShopId(shopId);
    ShopForm shopForm = new ShopForm(shopService,
        groupForm.getShopId());
    groupForm.setShopForm(shopForm);

    List<BalanceType> balanceTypes = balanceTypeService.findAll();

    groupForm.setBalanceTypeId(
        balanceTypeForm.getBalanceTypeId());
    BalanceType balanceType
        = balanceTypeService.findById(groupForm.getBalanceTypeId());
    groupForm.setBalanceTypeToForm(balanceType);

    AccountAndBalance accountAndBalance
        = accountAndBalanceService.getById(accountAndBalanceId);
    groupForm.setAccountAndBalanceId(
        accountAndBalance.getAccountAndBalanceId());
    groupForm.setAccountAndBalanceToForm(accountAndBalance);

    groupForm.getAccountAndBalanceForm().setAccountSourceId(accountSourceId);
    AccountSourceForm accountSourceForm
        = new AccountSourceForm(
        accountSourceService,
        accountSourceId);
    groupForm.getAccountAndBalanceForm().setAccountSourceForm(
        accountSourceForm);

    groupForm.getAccountAndBalanceForm().setAccountDestinationId(
        accountDestinationId);
    AccountDestinationForm accountDestinationForm
        = new AccountDestinationForm(
        accountDestinationService,
        accountDestinationId);
    groupForm.getAccountAndBalanceForm().setAccountDestinationForm(
        accountDestinationForm);

    model.addAttribute("groupForm", groupForm);
    model.addAttribute(
        "accountAndBalanceId", accountAndBalanceId);
    model.addAttribute("shopForm", shopForm);
    model.addAttribute(
        "accountSourceForm",
        accountSourceForm);
    model.addAttribute(
        "accountDestinationForm",
        accountDestinationForm);
    model.addAttribute("user", user);
    model.addAttribute("balanceTypes", balanceTypes);

    return "/spec/createGroup";
  }

  @RequestMapping(
      value = "/{userId}/{accountAndBalanceId}/{shopId}/{accountSourceId}/{accountDestinationId}/setAccountDestination",
      method = RequestMethod.GET)
  String setAccountDestinationToAccountAndBalance(
      @PathVariable("userId") Long userId,
      @PathVariable("accountAndBalanceId") Long accountAndBalanceId,
      @PathVariable("shopId") Long shopId,
      @PathVariable("accountSourceId") Long accountSourceId,
      @PathVariable("accountDestinationId") Long accountDestinationId,
      @ModelAttribute SpecificationGroupForm groupForm,
      @ModelAttribute BalanceTypeForm balanceTypeForm,
      Model model) {

    groupForm.setUserId(userId);
    UserForm userForm = new UserForm(
        userService,
        roleService,
        userId);
    groupForm.setUserForm(userForm);

    groupForm.setShopId(shopId);
    ShopForm shopForm = new ShopForm(
        shopService,
        groupForm.getShopId());
    groupForm.setShopForm(shopForm);

    List<BalanceType> balanceTypes = balanceTypeService.findAll();

    groupForm.setBalanceTypeId(
        balanceTypeForm.getBalanceTypeId());
    BalanceType balanceType
        = balanceTypeService.findById(groupForm.getBalanceTypeId());
    groupForm.setBalanceTypeToForm(balanceType);


    groupForm.setAccountAndBalanceId(
        accountAndBalanceId);
    AccountAndBalance accountAndBalance
        = accountAndBalanceService.getById(accountAndBalanceId);
    groupForm.setAccountAndBalanceToForm(accountAndBalance);

    groupForm.getAccountAndBalanceForm().setAccountSourceId(accountSourceId);
    AccountSourceForm accountSourceForm
        = new AccountSourceForm(
        accountSourceService,
        accountSourceId);
    groupForm.getAccountAndBalanceForm().setAccountSourceForm(accountSourceForm);

    groupForm.getAccountAndBalanceForm().setAccountDestinationId(accountDestinationId);
    AccountDestinationForm accountDestinationForm
        = new AccountDestinationForm(
        accountDestinationService,
        accountDestinationId);
    groupForm.getAccountAndBalanceForm().setAccountDestinationForm(
        accountDestinationForm);

    model.addAttribute("groupForm", groupForm);
    model.addAttribute(
        "accountAndBalanceId", accountAndBalanceId);
    model.addAttribute("shopForm", shopForm);
    model.addAttribute("accountSourceForm", accountSourceForm);
    model.addAttribute(
        "accountDestinationForm", accountDestinationForm);
    model.addAttribute("user", user);
    model.addAttribute("balanceTypes", balanceTypes);

    return "/spec/createGroup";
  }

  @GetMapping("/{userId}/{accountAndBalanceId}/{shopId}/{accountSourceId}/{accountDestinationId}/searchAccountSource")
  String searchAccountSource(
      @PathVariable("userId") Long userId,
      @PathVariable("accountAndBalanceId") Long accountAndBalanceId,
      @PathVariable("shopId") Long shopId,
      @PathVariable("accountSourceId") Long accountSourceId,
      @PathVariable("accountDestinationId") Long accountDestinationId,
      @ModelAttribute SpecificationGroupForm groupForm,
      Model model) {

    groupForm.setUserId(userId);
    UserForm userForm = new UserForm(
        userService,
        roleService,
        userId);
    groupForm.setUserForm(userForm);

    AccountAndBalance accountAndBalance
        = accountAndBalanceService.getById(accountAndBalanceId);
    ShopForm shopForm = new ShopForm(shopService, shopId);
    groupForm.setShopId(shopForm.getShopId());
    List<AccountSource> accountSources = accountSourceService.findAll();
    AccountDestinationForm accountDestinationForm
        = new AccountDestinationForm(
        accountDestinationService,
        accountDestinationId);

    model.addAttribute("accountAndBalanceId", accountAndBalanceId);
    model.addAttribute("shopForm", shopForm);
    model.addAttribute("accountSources", accountSources);
    model.addAttribute("accountDestinationForm", accountDestinationForm);

    return "/account/showSourceList";
  }

  @GetMapping("/{userId}/{accountAndBalanceId}/{shopId}/{accountSourceId}/{accountDestinationId}/searchAccountDestination")
  String searchAccountDestination(
      @PathVariable("userId") Long userId,
      @PathVariable("accountAndBalanceId") Long accountAndBalanceId,
      @PathVariable("shopId") Long shopId,
      @PathVariable("accountSourceId") Long accountSourceId,
      @PathVariable("accountDestinationId") Long accountDestinationId,
      @ModelAttribute SpecificationGroupForm groupForm,
      Model model) {

    groupForm.setUserId(userId);
    UserForm userForm = new UserForm(
        userService,
        roleService,
        userId);
    groupForm.setUserForm(userForm);

    AccountAndBalance accountAndBalance
        = accountAndBalanceService.getById(accountAndBalanceId);
    ShopForm shopForm = new ShopForm(shopService, shopId);
    groupForm.setShopId(shopForm.getShopId());
    AccountSourceForm accountSourceForm
        = new AccountSourceForm(
        accountSourceService,
        accountSourceId);
    List<AccountDestination> accountDestinations
        = accountDestinationService.findAll();

    model.addAttribute("accountAndBalanceId", accountAndBalanceId);
    model.addAttribute("shopForm", shopForm);
    model.addAttribute("accountSourceForm", accountSourceForm);
    model.addAttribute("accountDestinations", accountDestinations);

    return "/account/showDestinationList";
  }
}
