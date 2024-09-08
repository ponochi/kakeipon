package org.panda.systems.kakeipon.app.spec;

import org.panda.systems.kakeipon.app.account.AccountDestinationForm;
import org.panda.systems.kakeipon.app.account.AccountSourceForm;
import org.panda.systems.kakeipon.app.common.AccountAndBalanceForm;
import org.panda.systems.kakeipon.app.common.BalanceTypeForm;
import org.panda.systems.kakeipon.app.shop.ShopForm;
import org.panda.systems.kakeipon.app.user.RoleForm;
import org.panda.systems.kakeipon.app.user.UserForm;
import org.panda.systems.kakeipon.domain.model.account.AccountDestination;
import org.panda.systems.kakeipon.domain.model.account.AccountSource;
import org.panda.systems.kakeipon.domain.model.common.*;
import org.panda.systems.kakeipon.domain.model.currency.CurrencyList;
import org.panda.systems.kakeipon.domain.model.spec.Specification;
import org.panda.systems.kakeipon.domain.model.spec.SpecificationGroup;
import org.panda.systems.kakeipon.domain.model.user.Role;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

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
  CurrencyListService currencyService;
  @Autowired
  UnitService unitService;
  @Autowired
  TaxTypeService taxTypeService;
  @Autowired
  TaxRateService taxRateService;
  @Autowired
  User user;

  @ModelAttribute("specificationGroupForm")
  SpecificationGroupForm setUpSpecificationGroupForm() {
    SpecificationGroupForm form = new SpecificationGroupForm();

    form.setSpecificationGroupId(
        specificationGroupService.getMaxGroupId());
    AccountAndBalanceForm accountAndBalanceForm
        = new AccountAndBalanceForm(
        accountAndBalanceService,
        accountSourceService,
        accountDestinationService,
        new AccountAndBalanceForm(),
        new AccountSourceForm(
            accountSourceService,
            Long.parseLong("1")),
        new AccountDestinationForm(
            accountDestinationService,
            Long.parseLong("1")));
    UserForm userForm = new UserForm(
        userService,
        roleService,
        Long.parseLong("2"),
        Long.parseLong("2"));
    RoleForm roleForm = new RoleForm(
        roleService,
        Long.parseLong("1"));
    form.setUserId(userForm.getUserId());
    form.setUserForm(userForm);
    form.setUserToForm(
        userForm.getUserId(),
        userForm.getNickName(),
        userForm.getLastName(),
        userForm.getFirstName(),
        userForm.getBirthDay(),
        userForm.getPassword(),
        userForm.getPhoneNumber(),
        userForm.getEmail(),
        roleForm.getRoleId(),
        roleForm.getRoleName(),
        userForm.getEntryDate(),
        userForm.getUpdateDate());

    form.getUserForm().setRoleId(
        form.getUserForm().getRoleForm().getRoleId());

    form.setShopId(Long.parseLong("1"));
    ShopForm shopForm = new ShopForm(shopService,
        form.getShopId());

    form.setReceivingAndPaymentDate(LocalDate.now());
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH");

    LocalTime localTime = LocalTime.now();
    String nowHour = formatter.format(localTime);

    if (nowHour.length() == 1) {
      nowHour = "0" + nowHour + ":00";
    } else {
      nowHour = nowHour + ":00";
    }
    form.setReceivingAndPaymentTime(LocalTime.parse(nowHour));

    form.setBalanceTypeId(Long.parseLong("1"));
    List<BalanceType> balanceTypes
        = balanceTypeService.findAll();
    form.setBalanceTypeToForm(balanceTypes.get(0));

    BalanceTypeForm balanceTypeForm = new BalanceTypeForm();
    balanceTypeForm.setBalanceTypeId(Long.parseLong("1"));
    form.setBalanceTypeForm(balanceTypeForm);

    AccountAndBalance accountAndBalance
        = accountAndBalanceService.getById(
        accountAndBalanceService.getMaxAccountAndBalanceId());
    form.setAccountAndBalanceId(
        accountAndBalance.getAccountAndBalanceId()
    );
    form.setAccountAndBalanceForm(
        accountAndBalanceForm.setAccountAndBalanceToForm(
            accountAndBalance));

    AccountSource accountSource
        = accountSourceService.findById(
        accountAndBalance.getAccountSourceId());
    form.getAccountAndBalanceForm().setAccountSourceForm(
        accountAndBalanceForm.setAccountSourceToForm(
            accountSource));

    AccountDestination accountDestination
        = accountDestinationService.findById(
        accountAndBalance.getAccountDestinationId());
    form.getAccountAndBalanceForm().setAccountDestinationForm(
        accountAndBalanceForm.setAccountDestinationToForm(
            accountDestination));

    accountAndBalanceService.saveAndFlush(
        form.getAccountAndBalanceForm().toEntity());

    // ToDo: Fix this
    specificationGroupService.saveAndFlush(form.toEntity());

    SpecificationForm specForm = new SpecificationForm();

    List<Specification> specifications
        = specificationService.findBySpecificationGroupId(
        specificationGroupService.getMaxGroupId());
    if (specifications.size() == 0) {
      specForm.setSpecificationGroupId(
          specificationGroupService.getMaxGroupId());
      specForm.setSpecificationId(Long.parseLong("1"));
      specForm.setUserId(form.getUserId());
      specForm.setName("");
      specForm.setPrice(Long.parseLong("0"));
      specForm.setCurrencyId(Long.parseLong("1"));
      specForm.setQuantity(Long.parseLong("1"));
      specForm.setUnitId(Long.parseLong("1"));
      specForm.setTaxTypeId(Long.parseLong("1"));
      specForm.setTaxRateId(Long.parseLong("1"));
      specForm.setTax(Long.parseLong("0"));
      specForm.setMemo("");
      specForm.setEntryDate(LocalDateTime.now());
    } else {
      Long count = 0L;
      for (Specification specification : specifications) {
        count++;
        specForm.setSpecificationGroupId(
            specificationGroupService.getMaxGroupId());
        System.out.println("1>>>> form.getSpecificationGroupId(): " + form.getSpecificationGroupId());
        System.out.println("2>>>> specForm.getSpecificationGroupId(): " + specForm.getSpecificationGroupId());
        specForm.setSpecificationId(count);
        specForm.setUserId(form.getUserId());
        specForm.setName("");
        specForm.setPrice(Long.parseLong("0"));
        specForm.setCurrencyId(Long.parseLong("1"));
        specForm.setQuantity(Long.parseLong("1"));
        specForm.setUnitId(Long.parseLong("1"));
        specForm.setTaxTypeId(Long.parseLong("1"));
        specForm.setTaxRateId(Long.parseLong("1"));
        specForm.setTax(Long.parseLong("0"));
        specForm.setMemo("");
        specForm.setEntryDate(LocalDateTime.now());
      }
    }

    List<CurrencyList> currencyLists = currencyService.findAll();
    List<Unit> units = unitService.findAll();
    List<TaxType> taxTypes = taxTypeService.findAll();
    List<TaxRate> taxRates = taxRateService.findAll();
    Specification specification
        = specForm.toEntity();
    specificationService.saveAndFlush(specification);

    return form;
  }

  @GetMapping("/spec")
  String addSpecificationGroup(
      @ModelAttribute SpecificationGroupForm groupForm,
      @ModelAttribute SpecificationForm form,
      @ModelAttribute AccountAndBalanceForm accountAndBalanceForm,
      @ModelAttribute UserForm userForm,
      @ModelAttribute ShopForm shopForm,
      Model model) {

    AccountAndBalance accountAndBalance
        = accountAndBalanceService.getById(
        accountAndBalanceService.getMaxAccountAndBalanceId());
    AccountSourceForm accountSourceForm
        = new AccountSourceForm(
        accountSourceService,
        accountAndBalanceService.getById(
            accountAndBalanceService.getMaxAccountAndBalanceId()
        ).getAccountSourceId());
    AccountDestinationForm accountDestinationForm = new AccountDestinationForm(
        accountDestinationService,
        accountAndBalanceService.getById(
            accountAndBalanceService.getMaxAccountAndBalanceId()
        ).getAccountDestinationId());
    accountAndBalance.setEntryDate(LocalDateTime.now());
    accountAndBalanceService.saveAndFlush(accountAndBalance);

    groupForm = new SpecificationGroupForm();

    Role role = new Role();

    groupForm.setAccountAndBalanceId(
        accountAndBalance.getAccountAndBalanceId());

    groupForm.setUserId(userForm.getUserId());

    shopForm = new ShopForm(shopService,
        Long.parseLong("1"));
    groupForm.setShopId(shopForm.getShopId());

    List<BalanceType> balanceTypes
        = balanceTypeService.findAll();

    // ToDo: Fix this
    groupForm = new SpecificationGroupForm(
            specificationGroupService,
            specificationGroupService.getMaxGroupId());
System.out.println("1>>>> groupForm.getSpecificationGroupId(): " + groupForm.getSpecificationGroupId());
    List<CurrencyList> currencyLists = currencyService.findAll();
    List<Unit> units = unitService.findAll();
    List<TaxType> taxTypes = taxTypeService.findAll();
    List<TaxRate> taxRates = taxRateService.findAll();
//    specificationService.saveAndFlush(specification);

    List<SpecificationForm> specificationForms
        = specificationService.findBySpecificationGroupIdToForm(
        groupForm.getSpecificationGroupId());

    System.out.println(">>>> specificationForms.size(): " + specificationForms.size());
    model.addAttribute("specificationGroupForm", groupForm);
    model.addAttribute("specificationForms", specificationForms);
    model.addAttribute("currencyLists", currencyLists);
    model.addAttribute("units", units);
    model.addAttribute("taxTypes", taxTypes);
    model.addAttribute("taxRates", taxRates);
    model.addAttribute("shopForm", shopForm);
    model.addAttribute(
        "accountSourceForm",
        new AccountSourceForm(
            accountSourceService,
            accountAndBalance.getAccountSourceId()));
    model.addAttribute(
        "accountDestinationForm",
        new AccountDestinationForm(
            accountDestinationService,
            accountAndBalance.getAccountDestinationId()));
    model.addAttribute("userForm", userForm);
    model.addAttribute("balanceTypes", balanceTypes);

    return "/spec/createGroup";
  }

  @GetMapping(value = "/spec/detail")
  String addSpecificationDetail(
      @ModelAttribute SpecificationGroupForm groupForm,
      @ModelAttribute SpecificationForm form,
      Model model) {

    Specification specification = form.toEntity();
    specification.setSpecificationGroupId(
        groupForm.getSpecificationGroupId());
    specification.setUserId(groupForm.getUserId());
    specification.setUpdateDate(LocalDateTime.now());

    specificationService.saveAndFlush(specification);

    List<SpecificationForm> specificationForms
        = specificationService.findBySpecificationGroupIdToForm(
        groupForm.getSpecificationGroupId());

    model.addAttribute("specificationGroupForm", groupForm);
    model.addAttribute("specificationForms", specificationForms);
    model.addAttribute("currencyLists", currencyService.findAll());
    model.addAttribute("units", unitService.findAll());
    model.addAttribute("taxTypes", taxTypeService.findAll());
    model.addAttribute("taxRates", taxRateService.findAll());
    model.addAttribute(
        "accountAndBalanceId",
        groupForm.getAccountAndBalanceForm().getAccountAndBalanceId());
    model.addAttribute("shopForm", shopService.findById(groupForm.getShopId()));
    model.addAttribute(
        "accountSourceForm",
        accountSourceService.findById(
            groupForm.getAccountAndBalanceForm().getAccountSourceId()));
    model.addAttribute(
        "accountDestination",
        accountDestinationService.findById(
            groupForm.getAccountAndBalanceForm().getAccountDestinationId()));
    model.addAttribute("user", user);
    model.addAttribute("balanceTypes", balanceTypeService.findAll());

    return "/spec/createGroup";
  }

  @PostMapping(value = "/spec/save")
  String saveSpecificationDetail(
      @ModelAttribute SpecificationGroupForm groupForm,
      @ModelAttribute SpecificationForm form,
      Model model) {

    Specification specification = form.toEntity();
    specification.setSpecificationGroupId(
        groupForm.getSpecificationGroupId());
    specification.setUserId(groupForm.getUserId());
    specification.setUpdateDate(LocalDateTime.now());

    specificationService.saveAndFlush(specification);

    List<SpecificationForm> specificationForms
        = specificationService.findBySpecificationGroupIdToForm(
        groupForm.getSpecificationGroupId());

    model.addAttribute("specificationGroupForm", groupForm);
    model.addAttribute("specificationForms", specificationForms);
    model.addAttribute("currencyLists", currencyService.findAll());
    model.addAttribute("units", unitService.findAll());
    model.addAttribute("taxTypes", taxTypeService.findAll());
    model.addAttribute("taxRates", taxRateService.findAll());
    model.addAttribute(
        "accountAndBalanceId",
        groupForm.getAccountAndBalanceForm().getAccountAndBalanceId());
    model.addAttribute("shopForm", shopService.findById(groupForm.getShopId()));
    model.addAttribute(
        "accountSource",
        accountSourceService.findById(
            groupForm.getAccountAndBalanceForm().getAccountSourceId()));
    model.addAttribute(
        "accountDestination",
        accountDestinationService.findById(
            groupForm.getAccountAndBalanceForm().getAccountDestinationId()));
    model.addAttribute("user", user);
    model.addAttribute("balanceTypes", balanceTypeService.findAll());

    return "/spec/createGroup";
  }

  @PostMapping(value = "/spec/confirm")
  String commitSpecificationGroup(
      @ModelAttribute SpecificationGroupForm groupForm,
      @ModelAttribute SpecificationForm form,
      Model model) {

    Specification specification = form.toEntity();
    specification.setUpdateDate(LocalDateTime.now());
    specificationService.saveAndFlush(specification);

    model.addAttribute(
        "specificationGroupForm", groupForm);
    model.addAttribute(
        "specificationForm", form);
    return "redirect:/spec/createGroup";
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
    ShopForm shopForm = new ShopForm(shopService,
        groupForm.getShopId());
    groupForm.setShopId(shopForm.getShopId());

    List<BalanceType> balanceTypes = balanceTypeService.findAll();

    groupForm.setBalanceTypeId(
        groupForm.getBalanceTypeForm().getBalanceTypeId());
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
    model.addAttribute("shopForm", shopForm);
    model.addAttribute("accountSourceForm", accountSourceForm);
    model.addAttribute("accountDestinationForm", accountDestinationForm);
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
    ShopForm shopForm = new ShopForm(shopService,
        groupForm.getShopId());
    groupForm.setShopForm(shopForm);

    List<BalanceType> balanceTypes = balanceTypeService.findAll();

    groupForm.setBalanceTypeId(
        groupForm.getBalanceTypeForm().getBalanceTypeId());
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
    ShopForm shopForm = new ShopForm(
        shopService,
        groupForm.getShopId());
    groupForm.setShopId(shopForm.getShopId());

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
