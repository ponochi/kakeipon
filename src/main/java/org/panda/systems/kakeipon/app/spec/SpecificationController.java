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

  @PostMapping(value = "/spec/edit/group/{specificationGroupId}/{shopId}/{balanceTypeId}/{accountSourceId}/{accountDestinationId}")
  String editSpecificationGroup(
      @PathVariable("specificationGroupId") Long specificationGroupId,
      @PathVariable("shopId") Long shopId,
      @PathVariable("balanceTypeId") Long balanceTypeId,
      @PathVariable("accountSourceId") Long accountSourceId,
      @PathVariable("accountDestinationId") Long accountDestinationId,
      @ModelAttribute SpecificationGroupForm specificationGroupForm,
      @ModelAttribute SpecificationForm specificationForm,
      @ModelAttribute AccountAndBalanceForm accountAndBalanceForm,
      @ModelAttribute UserForm userForm,
      @ModelAttribute ShopForm shopForm,
      @ModelAttribute BalanceTypeForm balanceTypeForm,
      @ModelAttribute AccountSourceForm accountSourceForm,
      @ModelAttribute AccountDestinationForm accountDestinationForm,
      @ModelAttribute CurrencyListForm currencyListForm,
      @ModelAttribute UnitForm unitForm,
      @ModelAttribute TaxTypeForm taxTypeForm,
      @ModelAttribute TaxRateForm taxRateForm,
      Model model) {

    SpecificationGroup group
        = specificationGroupService.findById(
        specificationGroupId);
    specificationGroupForm
        = specificationGroupForm.setSpecificationGroupToForm(
        accountAndBalanceService,
        accountSourceService,
        accountDestinationService,
        group);

    accountSourceForm = new AccountSourceForm(
        accountSourceService,
        accountSourceId);
    accountAndBalanceForm.setAccountSourceId(
        accountSourceForm.getAccountSourceId());
    accountAndBalanceForm.setAccountSourceForm(
        accountSourceForm);

    accountDestinationForm = new AccountDestinationForm(
        accountDestinationService,
        accountDestinationId);
    accountAndBalanceForm.setAccountDestinationId(
        accountDestinationForm.getAccountDestinationId());
    accountAndBalanceForm.setAccountDestinationForm(
        accountDestinationForm);

    specificationGroupForm.setAccountAndBalanceId(
        accountAndBalanceForm.getAccountAndBalanceId());
    specificationGroupForm.setAccountAndBalanceForm(
        accountAndBalanceForm);

    userForm = new UserForm(
        userService,
        roleService,
        Long.parseLong("2"));
    specificationGroupForm.setUserId(userForm.getUserId());
    specificationGroupForm.setUserForm(userForm);

    Shop shop = shopService.findById(shopId);
    specificationGroupForm.setShopId(shop.getShopId());
    shopForm = shopForm.setShopToForm(shop);
    specificationGroupForm.setShopForm(shopForm);

    balanceTypeForm
        = new BalanceTypeForm(
        balanceTypeService,
        balanceTypeId);
    specificationGroupForm.setBalanceTypeId(
        balanceTypeForm.getBalanceTypeId());
    specificationGroupForm.setBalanceTypeForm(
        balanceTypeForm);

    List<SpecificationForm> specificationForms
        = specificationService.findBySpecificationGroupIdToForm(
        specificationGroupId);

    for (var specForm : specificationForms) {
      currencyListForm = new CurrencyListForm(
          currencyListService,
          specForm.getCurrencyId());
      specForm.setCurrencyId(
          currencyListForm.getCurrencyId());
      specForm.setCurrencyListForm(currencyListForm);

      unitForm = new UnitForm(
          unitService,
          specForm.getUnitId());
      specForm.setUnitId(unitForm.getUnitId());
      specForm.setUnitForm(unitForm);

      taxTypeForm = new TaxTypeForm(
          taxTypeService,
          specForm.getTaxTypeId());
      specForm.setTaxTypeId(taxTypeForm.getTaxTypeId());
      specForm.setTaxTypeForm(taxTypeForm);

      taxRateForm = new TaxRateForm(
          taxRateService,
          specForm.getTaxRateId());
      specForm.setTaxRateId(taxRateForm.getTaxRateId());
      specForm.setTaxRateForm(taxRateForm);
    }

    model.addAttribute("specificationGroupForm", specificationGroupForm);
    model.addAttribute("specificationForms", specificationForms);
    model.addAttribute("currencyListForm", currencyListForm);
    model.addAttribute("unitForm", unitForm);
    model.addAttribute("taxTypeForm", taxTypeForm);
    model.addAttribute("taxRateForm", taxRateForm);
    model.addAttribute(
        "accountAndBalanceForm",
        accountAndBalanceForm);
    model.addAttribute("shopForm", shopForm);
    model.addAttribute(
        "accountSourceForm",
        new AccountSourceForm(
            accountSourceService,
            specificationGroupForm.getAccountAndBalanceForm().getAccountSourceId()));
    model.addAttribute(
        "accountDestinationForm",
        new AccountDestinationForm(
            accountDestinationService,
            specificationGroupForm.getAccountAndBalanceForm().getAccountDestinationId()));
    model.addAttribute("userForm", userForm);
    model.addAttribute("balanceTypes", balanceTypeService.findAll());
    model.addAttribute("selectedBalanceTypeId", specificationGroupForm.getBalanceTypeId());

    return "/spec/editGroup";
  }

  @PostMapping(value = "/spec/create/detail/{specificationGroupId}/{shopId}/{balanceTypeId}/{accountSourceId}/{accountDestinationId}")
  String createSpecification(
      @PathVariable("specificationGroupId") Long specificationGroupId,
      @PathVariable("shopId") Long shopId,
      @PathVariable("balanceTypeId") Long balanceTypeId,
      @PathVariable("accountSourceId") Long accountSourceId,
      @PathVariable("accountDestinationId") Long accountDestinationId,
      @ModelAttribute SpecificationGroupForm specificationGroupForm,
      @ModelAttribute AccountAndBalanceForm accountAndBalanceForm,
      @ModelAttribute ShopForm shopForm,
      @ModelAttribute BalanceTypeForm balanceTypeForm,
      @ModelAttribute AccountSourceForm accountSourceForm,
      @ModelAttribute AccountDestinationForm accountDestinationForm,
      @ModelAttribute SpecificationForm specificationForm,
      @ModelAttribute UserForm userForm,
      @ModelAttribute CurrencyListForm currencyListForm,
      @ModelAttribute UnitForm unitForm,
      @ModelAttribute TaxTypeForm taxTypeForm,
      @ModelAttribute TaxRateForm taxRateForm,
      Model model) {

    specificationForm.setSpecificationGroupId(
        specificationGroupId);

    shopForm = new ShopForm(shopService, shopId);
    specificationGroupForm.setShopId(shopForm.getShopId());
    specificationGroupForm.setShopForm(shopForm);

    balanceTypeForm = new BalanceTypeForm(
        balanceTypeService,
        balanceTypeId);
    specificationGroupForm.setBalanceTypeId(
        balanceTypeForm.getBalanceTypeId());
    specificationGroupForm.setBalanceTypeForm(balanceTypeForm);

    accountSourceForm = new AccountSourceForm(
        accountSourceService,
        accountSourceId);
    accountAndBalanceForm.setAccountSourceId(accountSourceId);
    accountAndBalanceForm.setAccountSourceForm(accountSourceForm);

    accountDestinationForm = new AccountDestinationForm(
        accountDestinationService,
        accountDestinationId);
    accountAndBalanceForm.setAccountDestinationId(
        accountDestinationId);
    accountAndBalanceForm.setAccountDestinationForm(
        accountDestinationForm);

    specificationGroupForm.setAccountAndBalanceForm(
        accountAndBalanceForm);

    userForm = new UserForm(
        userService,
        roleService,
        Long.parseLong("2"));
    specificationGroupForm.setUserId(userForm.getUserId());
    specificationGroupForm.setUserForm(userForm);

    specificationForm.setCurrencyId(
        currencyListForm.getCurrencyId());
    specificationForm.setCurrencyListForm(currencyListForm);

    model.addAttribute(
        "specificationGroupForm",
        specificationGroupForm);
    model.addAttribute(
        "specificationForm", specificationForm);
    model.addAttribute(
        "currencyLists",
        currencyListService.findAll());
    model.addAttribute(
        "units",
        unitService.findAll());
    model.addAttribute(
        "taxTypes", taxTypeService.findAll());
    model.addAttribute(
        "taxRates", taxRateService.findAll());
    model.addAttribute("userForm", userForm);
    model.addAttribute(
        "accountAndBalanceForm",
        accountAndBalanceForm);
    model.addAttribute("shopForm", shopForm);
    model.addAttribute(
        "balanceTypes",
        balanceTypeService.findAll());
    model.addAttribute(
        "accountSourceForm", accountSourceForm);
    model.addAttribute(
        "accountDestinationForm",
        accountDestinationForm);

    return "/spec/createDetail";
  }

  @PostMapping(value = "/spec/edit/detail/{specificationGroupId}/{specificationId}/{shopId}/{balanceTypeId}/{accountSourceId}/{accountDestinationId}")
  String editSpecification(
      @PathVariable("specificationGroupId") Long specificationGroupId,
      @PathVariable("specificationId") Long specificationId,
      @PathVariable("shopId") Long shopId,
      @PathVariable("balanceTypeId") Long balanceTypeId,
      @PathVariable("accountSourceId") Long accountSourceId,
      @PathVariable("accountDestinationId") Long accountDestinationId,
      @ModelAttribute SpecificationGroupForm specificationGroupForm,
      @ModelAttribute SpecificationForm specificationForm,
      @ModelAttribute AccountAndBalanceForm accountAndBalanceForm,
      @ModelAttribute ShopForm shopForm,
      @ModelAttribute AccountSourceForm accountSourceForm,
      @ModelAttribute AccountDestinationForm accountDestinationForm,
      @ModelAttribute BalanceType balanceType,
      @ModelAttribute UserForm userForm,
      @ModelAttribute CurrencyListForm currencyListForm,
      @ModelAttribute UnitForm unitForm,
      @ModelAttribute TaxTypeForm taxTypeForm,
      @ModelAttribute TaxRateForm taxRateForm,
      Model model) {

    accountSourceForm = new AccountSourceForm(
        accountSourceService,
        accountSourceId);
    accountDestinationForm = new AccountDestinationForm(
        accountDestinationService,
        accountDestinationId);

    Specification specification
        = specificationService.findBySpecificationGroupIdAndSpecificationId(
        specificationGroupId,
        specificationId);

    specificationForm = specificationForm.setSpecificationToForm(
        specification);

    userForm = new UserForm(
        userService,
        roleService,
        Long.parseLong("2"));
    specificationGroupForm.setUserId(userForm.getUserId());
    specificationGroupForm.setUserForm(userForm);

    shopForm = new ShopForm(shopService, shopId);
    specificationGroupForm.setShopId(shopForm.getShopId());
    specificationGroupForm.setShopForm(shopForm);

    model.addAttribute(
        "specificationGroupForm", specificationGroupForm);
    model.addAttribute(
        "specificationForm", specificationForm);
    model.addAttribute(
        "accountAndBalanceForm", accountAndBalanceForm);
    model.addAttribute(
        "shopForm", shopForm);
    model.addAttribute(
        "accountSourceForm", accountSourceForm);
    model.addAttribute(
        "accountDestinationForm", accountDestinationForm);
    model.addAttribute(
        "balanceType", balanceType);
    model.addAttribute(
        "currencyLists", currencyListService.findAll());
    model.addAttribute(
        "units", unitService.findAll());
    model.addAttribute(
        "taxTypes", taxTypeService.findAll());
    model.addAttribute(
        "taxRates", taxRateService.findAll());
    model.addAttribute("userForm", userForm);

    return "/spec/editDetail";
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

    Long balanceTypeId = groupForm.getBalanceTypeId();
    String groupMemo = groupForm.getGroupMemo();

    SpecificationGroup group
        = specificationGroupService.findById(
        specificationGroupService.getMaxGroupId());
    groupForm = groupForm.setSpecificationGroupToForm(
        accountAndBalanceService,
        accountSourceService,
        accountDestinationService,
        group);
    groupForm.setBalanceTypeId(balanceTypeId);
    groupForm.setGroupMemo(groupMemo);

    groupForm.setAccountAndBalanceId(
        accountAndBalanceForm.getAccountAndBalanceId());
    groupForm.setAccountAndBalanceForm(accountAndBalanceForm);

    accountAndBalanceForm.setAccountSourceId(
        groupForm.getAccountAndBalanceForm().getAccountSourceId());
    accountAndBalanceForm.setAccountDestinationId(
        groupForm.getAccountAndBalanceForm().getAccountDestinationId());
    accountAndBalanceService.saveAndFlush(
        accountAndBalanceForm.toEntity());

    groupForm.setUserId(userForm.getUserId());
    groupForm.setUserForm(userForm);

    groupForm.setShopId(shopForm.getShopId());
    groupForm.setShopForm(shopForm);

    SpecificationGroup specGroup = groupForm.toEntity();
    specificationGroupService.update(specGroup);

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

    return "/spec/editGroup";
  }

  @PostMapping(value = "/spec/update/group/{specificationGroupId}")
  String updateGroup(
      @PathVariable("specificationGroupId") Long specificationGroupId,
      @ModelAttribute SpecificationGroupForm groupForm,
      @ModelAttribute SpecificationForm specificationForm,
      @ModelAttribute Specification specification,
      @ModelAttribute AccountAndBalanceForm accountAndBalanceForm,
      @ModelAttribute UserForm userForm,
      @ModelAttribute ShopForm shopForm,
      @ModelAttribute CurrencyListForm currencyListForm,
      @ModelAttribute UnitForm unitForm,
      @ModelAttribute TaxTypeForm taxTypeForm,
      @ModelAttribute TaxRateForm taxRateForm,
      Model model) {

    Long balanceTypeId = groupForm.getBalanceTypeId();
    String groupMemo = groupForm.getGroupMemo();

    SpecificationGroup group
        = specificationGroupService.findById(
        specificationGroupId);
    groupForm = groupForm.setSpecificationGroupToForm(
        accountAndBalanceService,
        accountSourceService,
        accountDestinationService,
        group);

    groupForm.setBalanceTypeId(balanceTypeId);
    groupForm.setGroupMemo(groupMemo);

    groupForm.setAccountAndBalanceId(
        accountAndBalanceForm.getAccountAndBalanceId());
    groupForm.setAccountAndBalanceForm(accountAndBalanceForm);

    accountAndBalanceForm.setAccountSourceId(
        groupForm.getAccountAndBalanceForm().getAccountSourceId());
    accountAndBalanceForm.setAccountDestinationId(
        groupForm.getAccountAndBalanceForm().getAccountDestinationId());
    accountAndBalanceService.saveAndFlush(
        accountAndBalanceForm.toEntity());

    groupForm.setUserId(userForm.getUserId());
    groupForm.setUserForm(userForm);

    shopForm
        = new ShopForm(
        shopService,
        groupForm.getShopId());
    groupForm.setShopId(shopForm.getShopId());
    groupForm.setShopForm(shopForm);

    SpecificationGroup specGroup = groupForm.toEntity();
    specificationGroupService.update(specGroup);

    List<SpecificationForm> specificationForms
        = specificationService.findBySpecificationGroupIdToForm(
        groupForm.getSpecificationGroupId());

    for (var specForm : specificationForms) {
      currencyListForm = new CurrencyListForm(
          currencyListService,
          specForm.getCurrencyId());
      specForm.setCurrencyId(
          currencyListForm.getCurrencyId());
      specForm.setCurrencyListForm(currencyListForm);

      unitForm = new UnitForm(
          unitService,
          specForm.getUnitId());
      specForm.setUnitId(unitForm.getUnitId());
      specForm.setUnitForm(unitForm);

      taxTypeForm = new TaxTypeForm(
          taxTypeService,
          specForm.getTaxTypeId());
      specForm.setTaxTypeId(taxTypeForm.getTaxTypeId());
      specForm.setTaxTypeForm(taxTypeForm);

      taxRateForm = new TaxRateForm(
          taxRateService,
          specForm.getTaxRateId());
      specForm.setTaxRateId(taxRateForm.getTaxRateId());
      specForm.setTaxRateForm(taxRateForm);
    }

    model.addAttribute("specificationGroupForm", groupForm);
    model.addAttribute("specificationForms", specificationForms);
//    model.addAttribute("currencyLists", currencyListService.findAll());
//    model.addAttribute("units", unitService.findAll());
//    model.addAttribute("taxTypes", taxTypeService.findAll());
//    model.addAttribute("taxRates", taxRateService.findAll());
    model.addAttribute("currencyListForm", currencyListForm);
    model.addAttribute("unitForm", unitForm);
    model.addAttribute("taxTypeForm", taxTypeForm);
    model.addAttribute("taxRateForm", taxRateForm);
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

    return "/spec/editGroup";
  }

  @PostMapping(value = "/spec/save/create/detail/{specificationGroupId}/{shopId}/{balanceTypeId}/{accountSourceId}/{accountDestinationId}")
  String saveCreateDetail(
      @PathVariable("specificationGroupId") Long specificationGroupId,
      @PathVariable("shopId") Long shopId,
      @PathVariable("balanceTypeId") Long balanceTypeId,
      @PathVariable("accountSourceId") Long accountSourceId,
      @PathVariable("accountDestinationId") Long accountDestinationId,
      @ModelAttribute SpecificationGroupForm specificationGroupForm,
      @ModelAttribute SpecificationForm specificationForm,
      @ModelAttribute AccountAndBalanceForm accountAndBalanceForm,
      @ModelAttribute ShopForm shopForm,
      @ModelAttribute BalanceTypeForm balanceTypeForm,
      @ModelAttribute AccountSourceForm accountSourceForm,
      @ModelAttribute AccountDestinationForm accountDestinationForm,
      @ModelAttribute UserForm userForm,
      @ModelAttribute CurrencyListForm currencyListForm,
      @ModelAttribute UnitForm unitForm,
      @ModelAttribute TaxTypeForm taxTypeForm,
      @ModelAttribute TaxRateForm taxRateForm,
      Model model) {

    specificationForm.setSpecificationGroupId(
        specificationGroupId);

    specificationGroupForm.setAccountAndBalanceId(
        accountAndBalanceForm.getAccountAndBalanceId());
    specificationGroupForm.setAccountAndBalanceForm(
        accountAndBalanceForm);

    shopForm = new ShopForm(shopService, shopId);
    specificationGroupForm.setShopId(shopForm.getShopId());
    specificationGroupForm.setShopForm(shopForm);

    balanceTypeForm = new BalanceTypeForm(
        balanceTypeService,
        balanceTypeId);
    specificationGroupForm.setBalanceTypeId(
        balanceTypeForm.getBalanceTypeId());
    specificationGroupForm.setBalanceTypeForm(balanceTypeForm);

    accountSourceForm = new AccountSourceForm(
        accountSourceService,
        accountSourceId);
    accountAndBalanceForm.setAccountSourceId(accountSourceId);
    accountAndBalanceForm.setAccountSourceForm(accountSourceForm);

    accountDestinationForm
        = new AccountDestinationForm(
        accountDestinationService,
        accountDestinationId);
    accountAndBalanceForm.setAccountDestinationId(
        accountDestinationId);
    accountAndBalanceForm.setAccountDestinationForm(
        accountDestinationForm);

    currencyListForm = new CurrencyListForm(
        currencyListService,
        specificationForm.getCurrencyId());
    specificationForm.setCurrencyId(
        currencyListForm.getCurrencyId());

    unitForm = new UnitForm(
        unitService,
        specificationForm.getUnitId());
    specificationForm.setUnitId(unitForm.getUnitId());

    taxTypeForm = new TaxTypeForm(
        taxTypeService,
        specificationForm.getTaxTypeId());
    specificationForm.setTaxTypeId(taxTypeForm.getTaxTypeId());

    taxRateForm = new TaxRateForm(
        taxRateService,
        specificationForm.getTaxRateId());
    specificationForm.setTaxRateId(taxRateForm.getTaxRateId());

    if (specificationForm.getEntryDate() == null) {
      specificationForm.setEntryDate(LocalDateTime.now());
    } else {
      specificationForm.setUpdateDate(LocalDateTime.now());
    }

    specificationService.saveAndFlush(specificationForm.toEntity());

    List<SpecificationForm> specificationForms
        = specificationService.findBySpecificationGroupIdToForm(
        specificationGroupId);

    for (var specForm : specificationForms) {
      currencyListForm = new CurrencyListForm(
          currencyListService,
          specForm.getCurrencyId());
      specForm.setCurrencyId(
          currencyListForm.getCurrencyId());
      specForm.setCurrencyListForm(currencyListForm);

      unitForm = new UnitForm(
          unitService,
          specForm.getUnitId());
      specForm.setUnitId(unitForm.getUnitId());
      specForm.setUnitForm(unitForm);

      taxTypeForm = new TaxTypeForm(
          taxTypeService,
          specForm.getTaxTypeId());
      specForm.setTaxTypeId(taxTypeForm.getTaxTypeId());
      specForm.setTaxTypeForm(taxTypeForm);

      taxRateForm = new TaxRateForm(
          taxRateService,
          specForm.getTaxRateId());
      specForm.setTaxRateId(taxRateForm.getTaxRateId());
      specForm.setTaxRateForm(taxRateForm);
    }

    model.addAttribute(
        "specificationGroupForm",
        specificationGroupForm);
    model.addAttribute(
        "specificationForms", specificationForms);
    model.addAttribute("currencyListForm",
        currencyListForm);
    model.addAttribute("unitForm", unitForm);
    model.addAttribute("taxTypeForm", taxTypeForm);
    model.addAttribute("taxRateForm", taxRateForm);
    model.addAttribute("userForm", userForm);
    model.addAttribute(
        "accountAndBalanceForm", accountAndBalanceForm);
    model.addAttribute("shopForm", shopForm);
    model.addAttribute(
        "balanceTypes", balanceTypeService.findAll());
    model.addAttribute(
        "accountSourceForm", accountSourceForm);
    model.addAttribute(
        "accountDestinationForm",
        accountDestinationForm);

    return "/spec/editGroup";
  }

  @PostMapping(value = "/spec/save/edit/detail/{specificationGroupId}/{specificationId}/{shopId}/{balanceTypeId}/{accountSourceId}/{accountDestinationId}")
  String saveEditDetail(
      @PathVariable("specificationGroupId") Long specificationGroupId,
      @PathVariable("specificationId") Long specificationId,
      @PathVariable("shopId") Long shopId,
      @PathVariable("balanceTypeId") Long balanceTypeId,
      @PathVariable("accountSourceId") Long accountSourceId,
      @PathVariable("accountDestinationId") Long accountDestinationId,
      @ModelAttribute SpecificationGroupForm specificationGroupForm,
      @ModelAttribute SpecificationForm specificationForm,
      @ModelAttribute AccountAndBalanceForm accountAndBalanceForm,
      @ModelAttribute UserForm userForm,
      @ModelAttribute CurrencyListForm currencyListForm,
      @ModelAttribute UnitForm unitForm,
      @ModelAttribute TaxTypeForm taxTypeForm,
      @ModelAttribute TaxRateForm taxRateForm,
      Model model) {

    specificationGroupForm.setAccountAndBalanceId(
        accountAndBalanceForm.getAccountAndBalanceId());
    specificationGroupForm.setAccountAndBalanceForm(
        accountAndBalanceForm);

    ShopForm shopForm = new ShopForm(shopService, shopId);
    specificationGroupForm.setShopId(shopForm.getShopId());
    specificationGroupForm.setShopForm(shopForm);

    BalanceTypeForm balanceTypeForm = new BalanceTypeForm(
        balanceTypeService,
        balanceTypeId);
    specificationGroupForm.setBalanceTypeId(
        balanceTypeForm.getBalanceTypeId());
    specificationGroupForm.setBalanceTypeForm(balanceTypeForm);

    AccountSourceForm accountSourceForm = new AccountSourceForm(
        accountSourceService,
        accountSourceId);
    accountAndBalanceForm.setAccountSourceId(accountSourceId);
    accountAndBalanceForm.setAccountSourceForm(accountSourceForm);

    AccountDestinationForm accountDestinationForm
        = new AccountDestinationForm(
        accountDestinationService,
        accountDestinationId);
    accountAndBalanceForm.setAccountDestinationId(
        accountDestinationId);
    accountAndBalanceForm.setAccountDestinationForm(
        accountDestinationForm);
    specificationGroupForm.setAccountAndBalanceForm(
        accountAndBalanceForm);

    currencyListForm = new CurrencyListForm(
        currencyListService,
        specificationForm.getCurrencyId());
    specificationForm.setCurrencyId(
        currencyListForm.getCurrencyId());

    unitForm = new UnitForm(
        unitService,
        specificationForm.getUnitId());
    specificationForm.setUnitId(unitForm.getUnitId());

    taxTypeForm = new TaxTypeForm(
        taxTypeService,
        specificationForm.getTaxTypeId());
    specificationForm.setTaxTypeId(taxTypeForm.getTaxTypeId());

    taxRateForm = new TaxRateForm(
        taxRateService,
        specificationForm.getTaxRateId());
    specificationForm.setTaxRateId(taxRateForm.getTaxRateId());

    if (specificationForm.getEntryDate() == null) {
      specificationForm.setEntryDate(LocalDateTime.now());
    } else {
      specificationForm.setUpdateDate(LocalDateTime.now());
    }

    specificationService.saveAndFlush(specificationForm.toEntity());

    List<SpecificationForm> specificationForms
        = specificationService.findBySpecificationGroupIdToForm(
        specificationGroupId);

    for (var specForm : specificationForms) {
      currencyListForm = new CurrencyListForm(
          currencyListService,
          specForm.getCurrencyId());
      specForm.setCurrencyId(
          currencyListForm.getCurrencyId());
      specForm.setCurrencyListForm(currencyListForm);

      unitForm = new UnitForm(
          unitService,
          specForm.getUnitId());
      specForm.setUnitId(unitForm.getUnitId());
      specForm.setUnitForm(unitForm);

      taxTypeForm = new TaxTypeForm(
          taxTypeService,
          specForm.getTaxTypeId());
      specForm.setTaxTypeId(taxTypeForm.getTaxTypeId());
      specForm.setTaxTypeForm(taxTypeForm);

      taxRateForm = new TaxRateForm(
          taxRateService,
          specForm.getTaxRateId());
      specForm.setTaxRateId(taxRateForm.getTaxRateId());
      specForm.setTaxRateForm(taxRateForm);
    }

    model.addAttribute(
        "specificationGroupForm",
        specificationGroupForm);
    model.addAttribute(
        "specificationForms", specificationForms);
    model.addAttribute(
        "currencyListForm", currencyListForm);
    model.addAttribute("unitForm", unitForm);
    model.addAttribute("taxTypeForm", taxTypeForm);
    model.addAttribute("taxRateForm", taxRateForm);
    model.addAttribute("userForm", userForm);
    model.addAttribute(
        "accountAndBalanceForm", accountAndBalanceForm);
    model.addAttribute("shopForm", shopForm);
    model.addAttribute(
        "balanceTypes", balanceTypeService.findAll());
    model.addAttribute(
        "accountSourceForm", accountSourceForm);
    model.addAttribute(
        "accountDestinationForm",
        accountDestinationForm);

    return "/spec/editGroup";
  }

  @GetMapping("/{specificationGroupId}/{userId}/{accountAndBalanceId}/{shopId}/{accountSourceId}/{accountDestinationId}/searchShop")
  String searchShop(
      @PathVariable("specificationGroupId") Long specificationGroupId,
      @PathVariable("userId") Long userId,
      @PathVariable("accountAndBalanceId") Long accountAndBalanceId,
      @PathVariable("shopId") Long shopId,
      @PathVariable("accountSourceId") Long accountSourceId,
      @PathVariable("accountDestinationId") Long accountDestinationId,
      @ModelAttribute SpecificationGroupForm groupForm,
      @ModelAttribute UserForm userForm,
      @ModelAttribute BalanceTypeForm balanceTypeForm,
      Model model) {

    SpecificationGroup group
        = specificationGroupService.findById(specificationGroupId);
    String groupMemo = group.getGroupMemo();

    groupForm.setUserId(userId);

    AccountAndBalance accountAndBalance
        = accountAndBalanceService.getById(accountAndBalanceId);
    List<Shop> shops = shopService.findAll();
    AccountSource accountSource
        = accountSourceService.findById(accountSourceId);
    AccountDestination accountDestination
        = accountDestinationService.findById(accountDestinationId);

    groupForm.setGroupMemo(groupMemo);

    model.addAttribute("specificationGroupForm", groupForm);
    model.addAttribute("accountAndBalanceId", accountAndBalanceId);
    model.addAttribute("shops", shops);
    model.addAttribute("accountSource", accountSource);
    model.addAttribute("accountDestination", accountDestination);
    model.addAttribute("userForm", userForm);

    return "/shop/showList";
  }

  @GetMapping("/{specificationGroupId}/{userId}/{accountAndBalanceId}/{shopId}/{accountSourceId}/{accountDestinationId}/searchAccountSource")
  String searchAccountSource(
      @PathVariable("specificationGroupId") Long specificationGroupId,
      @PathVariable("userId") Long userId,
      @PathVariable("accountAndBalanceId") Long accountAndBalanceId,
      @PathVariable("shopId") Long shopId,
      @PathVariable("accountSourceId") Long accountSourceId,
      @PathVariable("accountDestinationId") Long accountDestinationId,
      @ModelAttribute SpecificationGroupForm groupForm,
      @ModelAttribute UserForm userForm,
      @ModelAttribute BalanceTypeForm balanceTypeForm,
      Model model) {

    SpecificationGroup group
        = specificationGroupService.findById(specificationGroupId);
    String groupMemo = group.getGroupMemo();

    groupForm.setUserId(userId);

    AccountAndBalance accountAndBalance
        = accountAndBalanceService.getById(accountAndBalanceId);
    ShopForm shopForm = new ShopForm(shopService, shopId);
    groupForm.setShopId(shopForm.getShopId());
    List<AccountSource> accountSources = accountSourceService.findAll();
    AccountDestinationForm accountDestinationForm
        = new AccountDestinationForm(
        accountDestinationService,
        accountDestinationId);

    groupForm.setGroupMemo(groupMemo);

    model.addAttribute("accountAndBalanceId", accountAndBalanceId);
    model.addAttribute("shopForm", shopForm);
    model.addAttribute("accountSources", accountSources);
    model.addAttribute("accountDestinationForm", accountDestinationForm);
    model.addAttribute("userForm", userForm);

    return "/account/showSourceList";
  }

  @GetMapping("/{specificationGroupId}/{userId}/{accountAndBalanceId}/{shopId}/{accountSourceId}/{accountDestinationId}/searchAccountDestination")
  String searchAccountDestination(
      @PathVariable("specificationGroupId") Long specificationGroupId,
      @PathVariable("userId") Long userId,
      @PathVariable("accountAndBalanceId") Long accountAndBalanceId,
      @PathVariable("shopId") Long shopId,
      @PathVariable("accountSourceId") Long accountSourceId,
      @PathVariable("accountDestinationId") Long accountDestinationId,
      @ModelAttribute SpecificationGroupForm groupForm,
      @ModelAttribute UserForm userForm,
      @ModelAttribute BalanceTypeForm balanceTypeForm,
      Model model) {

    SpecificationGroup group
        = specificationGroupService.findById(specificationGroupId);
    String groupMemo = group.getGroupMemo();

    groupForm.setUserId(userId);

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

    groupForm.setGroupMemo(groupMemo);

    model.addAttribute("accountAndBalanceId", accountAndBalanceId);
    model.addAttribute("shopForm", shopForm);
    model.addAttribute("accountSourceForm", accountSourceForm);
    model.addAttribute("accountDestinations", accountDestinations);
    model.addAttribute("userForm", userForm);

    return "/account/showDestinationList";
  }

  @RequestMapping(value = "/{specificationGroupId}/{userId}/{accountAndBalanceId}/{shopId}/{accountSourceId}/{accountDestinationId}/setShop",
      method = RequestMethod.GET)
  String setShopToSpecificationGroup(
      @PathVariable("specificationGroupId") Long specificationGroupId,
      @PathVariable("userId") Long userId,
      @PathVariable("accountAndBalanceId") Long accountAndBalanceId,
      @PathVariable("shopId") Long shopId,
      @PathVariable("accountSourceId") Long accountSourceId,
      @PathVariable("accountDestinationId") Long accountDestinationId,
      @ModelAttribute SpecificationGroupForm groupForm,
      @ModelAttribute BalanceTypeForm balanceTypeForm,
      Model model) {

    SpecificationGroup group
        = specificationGroupService.findById(specificationGroupId);
    String groupMemo = group.getGroupMemo();

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

    groupForm.setGroupMemo(groupMemo);

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

    if (specificationGroupId == null) {
      return "/spec/createGroup";
    } else {
      return "/spec/editGroup";
    }
  }

  @RequestMapping(
      value = "/{specificationGroupId}/{userId}/{accountAndBalanceId}/{shopId}/{accountSourceId}/{accountDestinationId}/setAccountSource",
      method = RequestMethod.GET)
  String setAccountSourceToAccountAndBalance(
      @PathVariable("specificationGroupId") Long specificationGroupId,
      @PathVariable("userId") Long userId,
      @PathVariable("accountAndBalanceId") Long accountAndBalanceId,
      @PathVariable("shopId") Long shopId,
      @PathVariable("accountSourceId") Long accountSourceId,
      @PathVariable("accountDestinationId") Long accountDestinationId,
      @ModelAttribute SpecificationGroupForm groupForm,
      @ModelAttribute BalanceTypeForm balanceTypeForm,
      Model model) {

    SpecificationGroup group
        = specificationGroupService.findById(specificationGroupId);
    String groupMemo = group.getGroupMemo();

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

    groupForm.setGroupMemo(groupMemo);

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


    if (specificationGroupId == null) {
      return "/spec/createGroup";
    } else {
      return "/spec/editGroup";
    }
  }

  @RequestMapping(
      value = "/{specificationGroupId}/{userId}/{accountAndBalanceId}/{shopId}/{accountSourceId}/{accountDestinationId}/setAccountDestination",
      method = RequestMethod.GET)
  String setAccountDestinationToAccountAndBalance(
      @PathVariable("specificationGroupId") Long specificationGroupId,
      @PathVariable("userId") Long userId,
      @PathVariable("accountAndBalanceId") Long accountAndBalanceId,
      @PathVariable("shopId") Long shopId,
      @PathVariable("accountSourceId") Long accountSourceId,
      @PathVariable("accountDestinationId") Long accountDestinationId,
      @ModelAttribute SpecificationGroupForm groupForm,
      @ModelAttribute BalanceTypeForm balanceTypeForm,
      Model model) {

    SpecificationGroup group
        = specificationGroupService.findById(specificationGroupId);
    String groupMemo = group.getGroupMemo();

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

    groupForm.setGroupMemo(groupMemo);

    model.addAttribute("groupForm", groupForm);
    model.addAttribute(
        "accountAndBalanceId", accountAndBalanceId);
    model.addAttribute("shopForm", shopForm);
    model.addAttribute("accountSourceForm", accountSourceForm);
    model.addAttribute(
        "accountDestinationForm", accountDestinationForm);
    model.addAttribute("user", user);
    model.addAttribute("balanceTypes", balanceTypes);


    if (specificationGroupId == null) {
      return "/spec/createGroup";
    } else {
      return "/spec/editGroup";
    }
  }
}
