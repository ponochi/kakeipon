package org.panda.systems.kakeipon.app.spec;

import org.panda.systems.kakeipon.app.account.AccountDestinationForm;
import org.panda.systems.kakeipon.app.account.AccountSourceForm;
import org.panda.systems.kakeipon.app.common.*;
import org.panda.systems.kakeipon.app.currency.CurrencyListForm;
import org.panda.systems.kakeipon.app.shop.ShopForm;
import org.panda.systems.kakeipon.app.users.UsersExtForm;
import org.panda.systems.kakeipon.domain.model.account.AccountDestination;
import org.panda.systems.kakeipon.domain.model.account.AccountSource;
import org.panda.systems.kakeipon.domain.model.common.AccountAndBalance;
import org.panda.systems.kakeipon.domain.model.common.BalanceType;
import org.panda.systems.kakeipon.domain.model.common.Shop;
import org.panda.systems.kakeipon.domain.model.spec.Specification;
import org.panda.systems.kakeipon.domain.model.spec.SpecificationGroup;
import org.panda.systems.kakeipon.domain.model.users.Users;
import org.panda.systems.kakeipon.domain.model.users.UsersExt;
import org.panda.systems.kakeipon.domain.service.account.AccountDestinationService;
import org.panda.systems.kakeipon.domain.service.account.AccountSourceService;
import org.panda.systems.kakeipon.domain.service.common.*;
import org.panda.systems.kakeipon.domain.service.currency.CurrencyListService;
import org.panda.systems.kakeipon.domain.service.spec.SpecificationGroupService;
import org.panda.systems.kakeipon.domain.service.spec.SpecificationService;
import org.panda.systems.kakeipon.domain.service.users.UsersDetail;
import org.panda.systems.kakeipon.domain.service.users.UsersDetailService;
import org.panda.systems.kakeipon.domain.service.users.UsersExtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("")
public class SpecificationController implements Serializable {

  @Autowired
  private SpecificationGroupService specificationGroupService;
  @Autowired
  private SpecificationService specificationService;
  @Autowired
  private UsersDetailService usersDetailService;
//  @Autowired
//  private AuthoritiesService authoritiesService;
  @Autowired
  private UsersExtService usersExtService;
  @Autowired
  private ShopService shopService;
  @Autowired
  private BalanceTypeService balanceTypeService;
  @Autowired
  private AccountAndBalanceService accountAndBalanceService;
  @Autowired
  private AccountSourceService accountSourceService;
  @Autowired
  private AccountDestinationService accountDestinationService;
  @Autowired
  private CurrencyListService currencyListService;
  @Autowired
  private UnitService unitService;
  @Autowired
  private TaxTypeService taxTypeService;
  @Autowired
  private TaxRateService taxRateService;
  @Autowired
  private UsersDetail usersDetail;

//  public SpecificationController(SpecificationGroupService specificationGroupService, SpecificationService specificationService, UsersDetailService usersDetailsService, AuthoritiesService authoritiesService, UsersExtService userExtService, ShopService shopService, BalanceTypeService balanceTypeService, AccountAndBalanceService accountAndBalanceService, AccountSourceService accountSourceService, AccountDestinationService accountDestinationService, CurrencyListService currencyListService, UnitService unitService, TaxTypeService taxTypeService, TaxRateService taxRateService, UsersDetail usersDetails) {
//    this.specificationGroupService = specificationGroupService;
//    this.specificationService = specificationService;
//    this.usersDetailsService = usersDetailsService;
//    this.authoritiesService = authoritiesService;
//    this.userExtService = userExtService;
//    this.shopService = shopService;
//    this.balanceTypeService = balanceTypeService;
//    this.accountAndBalanceService = accountAndBalanceService;
//    this.accountSourceService = accountSourceService;
//    this.accountDestinationService = accountDestinationService;
//    this.currencyListService = currencyListService;
//    this.unitService = unitService;
//    this.taxTypeService = taxTypeService;
//    this.taxRateService = taxRateService;
//    this.usersDetails = usersDetails;
//  }

//  public UsersDetail convertUserDetailsToKakeiPonUsersDetails(
//      UserDetails userDetails) {
//
//    // Lambda expression to get RoleName from authority
//    Function<String, RoleName> getRoleName
//        = (authority) -> {
//      for (RoleName allRoles : RoleName.values()) {
//        if (allRoles.name().equals(authority)) {
//          return allRoles;
//        }
//      }
//      return RoleName.USER;
//    };
//
//    UsersDetail usersDetails
//        = new UsersDetail(users);
//
//    usersDetails.getUsers().setUsername();
//    usersDetails.getUsers().setPassword(
//        userDetails.getPassword());
//    usersDetails.getUsers().setAuthorities(
//        new Authorities());
//
//    for (var authority : userDetails.getAuthorities()) {
//      usersDetails.getAuthorities().setAuthority(
//          getRoleName.apply(authority.getAuthority())
//      );
//    }
//
//    usersDetails.setAccountNonExpired(
//        userDetails.isAccountNonExpired());
//    usersDetails.setAccountNonLocked(
//        userDetails.isAccountNonLocked());
//    usersDetails.setCredentialsNonExpired(
//        userDetails.isCredentialsNonExpired());
//    usersDetails.setEnabled(
//        userDetails.isEnabled());
//
//    return usersDetails;
//  }

  @GetMapping("/spec")
  String index(
      @AuthenticationPrincipal UserDetails userDetails,
      @ModelAttribute UsersExtForm usersExtForm,
      Model model) {

    List<SpecificationGroupForm> groupForms
        = specificationGroupService.findAllToForm(false);

    for (SpecificationGroupForm groupForm : groupForms) {
      UsersExt usersExt
          = usersExtService.findByUserId(groupForm.getUserId());
      usersExtForm = usersExtForm.setUserExtToForm(usersExt);
      groupForm.setUsersExtForm(usersExtForm);

      AccountAndBalanceForm accountAndBalanceForm
          = new AccountAndBalanceForm(
          accountAndBalanceService,
          accountSourceService,
          accountDestinationService)
          .setAccountAndBalanceFormAndAccountSourceFormAndAccountDestinationForm(
              groupForm.getAccountAndBalanceId(),
              groupForm.getAccountAndBalanceForm().getAccountSourceId(),
              groupForm.getAccountAndBalanceForm().getAccountDestinationId());
      groupForm.setAccountAndBalanceForm(accountAndBalanceForm);

      groupForm.setAccountAndBalanceId(
          accountAndBalanceForm.getAccountAndBalanceId());
      groupForm.setAccountAndBalanceForm(
          accountAndBalanceForm);

      AccountSourceForm accountSourceForm = new AccountSourceForm(
          accountSourceService)
          .setAccountSourceFormByDB(
              groupForm
                  .getAccountAndBalanceForm()
                  .getAccountSourceId());
      accountAndBalanceForm
          .setAccountSourceId(
              accountSourceForm.getAccountSourceId());
      accountAndBalanceForm.setAccountSourceForm(accountSourceForm);

      AccountDestinationForm accountDestinationForm
          = new AccountDestinationForm(
          accountDestinationService)
          .setAccountDestinationFormByDB(
              groupForm.getAccountAndBalanceForm()
                  .getAccountDestinationId());
      accountAndBalanceForm.setAccountDestinationId(
          accountDestinationForm.getAccountDestinationId());
      accountAndBalanceForm.setAccountDestinationForm(
          accountDestinationForm);

      ShopForm shopForm = new ShopForm(shopService);
      shopForm.setShopId(groupForm.getShopId());
      groupForm.setShopForm(shopForm);

      BalanceType balanceType
          = balanceTypeService.findById(
          groupForm.getBalanceTypeId());
      groupForm.setBalanceTypeId(balanceType.getBalanceTypeId());
      groupForm.setBalanceTypeToForm(balanceType);
    }

    model.addAttribute("specificationGroupForms", groupForms);
    model.addAttribute("userDetails", userDetails);
    return "/spec/showList";
  }

  @GetMapping(value = "/spec/create/group/{userId}")
  String createSpecificationGroup(
      @PathVariable("userId") Long userId,
      @ModelAttribute SpecificationGroupForm groupForm,
      @ModelAttribute AccountAndBalanceForm accountAndBalanceForm,
      @AuthenticationPrincipal UserDetails userDetails,
      @ModelAttribute ShopForm shopForm,
      @ModelAttribute BalanceTypeForm balanceTypeForm,
      @ModelAttribute AccountSourceForm accountSourceForm,
      @ModelAttribute AccountDestinationForm accountDestinationForm,
      Model model) {

    Users users = usersDetailService.findByUserId(userId);

    groupForm = new SpecificationGroupForm(
        specificationGroupService,
        specificationService,
        accountAndBalanceService,
        accountSourceService,
        accountDestinationService,
        usersDetailService,
//        authoritiesService,
        usersExtService,
        shopService,
        balanceTypeService,
        currencyListService,
        unitService,
        taxTypeService,
        taxRateService);

    SpecificationGroup group
        = specificationGroupService
        .findBySpecificationGroupIdAndUserIdAndDeleted(
            specificationGroupService.getMaxGroupId(),
            userId,
            false);

    groupForm = groupForm.setSpecificationGroupToForm(group);

    groupForm.setUserId(userId);
    groupForm.setId(userDetails.getUsername());
    groupForm.setUsersToForm(
        usersDetailService,
        users);

    Shop shop = shopService.findById(Long.parseLong("1"));
    groupForm.setShopId(shop.getShopId());
    shopForm = shopForm.setShopToForm(shop);
    groupForm.setShopForm(shopForm);

    BalanceType balanceType
        = balanceTypeService.findById(
        balanceTypeForm.getBalanceTypeId());
    groupForm.setBalanceTypeId(balanceType.getBalanceTypeId());
    groupForm.setBalanceTypeToForm(balanceType);

    specificationGroupService
        .saveAndFlushSpecificationGroup(
            groupForm.toEntity());

    SpecificationGroup specificationGroup
        = specificationGroupService
        .findBySpecificationGroupIdAndUserIdAndDeleted(
            specificationGroupService.getMaxGroupId(),
            userId,
            false);
    groupForm = groupForm.setSpecificationGroupToForm(specificationGroup);

    model.addAttribute("specificationGroupForm", groupForm);
    model.addAttribute(
        "accountAndBalanceId",
        groupForm.getAccountAndBalanceForm().getAccountAndBalanceId());
    model.addAttribute("shopForm", shopForm);
    model.addAttribute(
        "accountSourceForm",
        new AccountSourceForm(
            accountSourceService)
            .setAccountSourceFormByDB(
                groupForm
                    .getAccountAndBalanceForm()
                    .getAccountSourceId()));
    model.addAttribute(
        "accountDestinationForm",
        new AccountDestinationForm(
            accountDestinationService)
            .setAccountDestinationFormByDB(
                groupForm
                    .getAccountAndBalanceForm()
                    .getAccountDestinationId()));
    model.addAttribute("userDetails", userDetails);
    model.addAttribute("balanceTypes", balanceTypeService.findAll());
    model.addAttribute("selectedBalanceTypeId", groupForm.getBalanceTypeId());

    return "/spec/createGroup";
  }

  @PostMapping(value = "/spec/edit/group/{specificationGroupId}/{userId}/{accountAndBalanceId}/{shopId}/{balanceTypeId}/{accountSourceId}/{accountDestinationId}")
  String editSpecificationGroup(
      @PathVariable("specificationGroupId") Long specificationGroupId,
      @PathVariable("userId") Long userId,
      @PathVariable("accountAndBalanceId") Long accountAndBalanceId,
      @PathVariable("shopId") Long shopId,
      @PathVariable("balanceTypeId") Long balanceTypeId,
      @PathVariable("accountSourceId") Long accountSourceId,
      @PathVariable("accountDestinationId") Long accountDestinationId,
      @ModelAttribute SpecificationGroupForm groupForm,
      @ModelAttribute SpecificationForm detailForm,
      @ModelAttribute AccountAndBalanceForm accountAndBalanceForm,
      @ModelAttribute ShopForm shopForm,
      @ModelAttribute BalanceTypeForm balanceTypeForm,
      @ModelAttribute AccountSourceForm accountSourceForm,
      @ModelAttribute AccountDestinationForm accountDestinationForm,
      @ModelAttribute CurrencyListForm currencyListForm,
      @ModelAttribute UnitForm unitForm,
      @ModelAttribute TaxTypeForm taxTypeForm,
      @ModelAttribute TaxRateForm taxRateForm,
      @AuthenticationPrincipal UserDetails userDetails,
      Model model) {

    double totalAmount = Double.parseDouble("0.0");

    SpecificationGroup group
        = specificationGroupService
        .findBySpecificationGroupIdAndUserIdAndDeleted(
            specificationGroupId,
            userId,
            false);
    groupForm
        = groupForm.setSpecificationGroupToForm(group);

//    groupForm.setUsersDetail(
//        convertUserDetailsToKakeiPonUsersDetails(
//            userDetails));

    Shop shop = shopService.findById(shopId);
    groupForm.setShopId(shop.getShopId());
    shopForm = shopForm.setShopToForm(shop);
    groupForm.setShopForm(shopForm);

    BalanceType balanceType
        = balanceTypeService.findById(
        balanceTypeId);
    groupForm.setBalanceTypeId(balanceType.getBalanceTypeId());
    groupForm.setBalanceTypeToForm(balanceType);

    groupForm.setAccountAndBalanceId(
        accountAndBalanceForm.getAccountAndBalanceId());
    groupForm.setAccountAndBalanceForm(
        accountAndBalanceForm);

    accountSourceForm = new AccountSourceForm(
        accountSourceService)
        .setAccountSourceFormByDB(
            accountSourceId);
    accountAndBalanceForm.setAccountSourceId(accountSourceId);
    accountAndBalanceForm.setAccountSourceForm(accountSourceForm);

    accountDestinationForm = new AccountDestinationForm(
        accountDestinationService)
        .setAccountDestinationFormByDB(
            accountDestinationId);
    accountAndBalanceForm.setAccountDestinationId(
        accountDestinationId);
    accountAndBalanceForm.setAccountDestinationForm(
        accountDestinationForm);

    List<SpecificationForm> detailForms
        = specificationService.findBySpecificationGroupIdToForm(
        specificationGroupId,
        userId,
        false);

    for (var specForm : detailForms) {
      currencyListForm = new CurrencyListForm(
          currencyListService,
          specForm.getCurrencyId());
      specForm.setCurrencyId(
          currencyListForm.getCurrencyId());
      specForm.setCurrencyListForm(currencyListForm);

      unitForm = new UnitForm(
          unitService)
          .setUnitFormByDB(
              specForm.getUnitId());
      specForm.setUnitId(unitForm.getUnitId());
      specForm.setUnitForm(unitForm);

      taxTypeForm = new TaxTypeForm(
          taxTypeService)
          .setTaxTypeFormByDB(
              specForm.getTaxTypeId());
      specForm.setTaxTypeId(taxTypeForm.getTaxTypeId());
      specForm.setTaxTypeForm(taxTypeForm);

      taxRateForm = new TaxRateForm(
          taxRateService)
          .setTaxRateFormByDB(
              specForm.getTaxRateId());
      specForm.setTaxRateId(taxRateForm.getTaxRateId());
      specForm.setTaxRateForm(taxRateForm);

      if (taxTypeForm.getTaxTypeId() == 1) {
        if (currencyListForm.getCurrencyId() == 1) {
          totalAmount += (specForm.getPrice()
              * specForm.getQuantity()
              * (1.0 + ((double) taxRateForm.getTaxRate() / 100)));
        }
      } else {
        if (currencyListForm.getCurrencyId() == 1) {
          totalAmount += (specForm.getPrice()
              * specForm.getQuantity());
        }
      }
    }

    model.addAttribute("specificationGroupForm", groupForm);
    model.addAttribute("specificationForms", detailForms);
    model.addAttribute("currencyListForm", currencyListForm);
    model.addAttribute("unitForm", unitForm);
    model.addAttribute("taxTypeForm", taxTypeForm);
    model.addAttribute("taxRateForm", taxRateForm);
    model.addAttribute("shopForm", shopForm);
    model.addAttribute(
        "accountAndBalanceForm",
        accountAndBalanceForm);
    model.addAttribute(
        "accountSourceForm", accountSourceForm);
    model.addAttribute(
        "accountDestinationForm",
        accountDestinationForm);
    model.addAttribute("userDetails", userDetails);
    model.addAttribute(
        "balanceTypes", balanceTypeService.findAll());
    model.addAttribute(
        "selectedBalanceTypeId", groupForm.getBalanceTypeId());
    model.addAttribute(
        "totalAmount", Math.round(totalAmount));

    return "/spec/editGroup";
  }

  @PostMapping(value = "/spec/create/detail/{specificationGroupId}/{userId}/{accountAndBalanceId}/{shopId}/{balanceTypeId}/{accountSourceId}/{accountDestinationId}")
  String createSpecification(
      @PathVariable("specificationGroupId") Long specificationGroupId,
      @PathVariable("userId") Long userId,
      @PathVariable("accountAndBalanceId") Long accountAndBalanceId,
      @PathVariable("shopId") Long shopId,
      @PathVariable("balanceTypeId") Long balanceTypeId,
      @PathVariable("accountSourceId") Long accountSourceId,
      @PathVariable("accountDestinationId") Long accountDestinationId,
      @ModelAttribute SpecificationGroupForm groupForm,
      @ModelAttribute SpecificationForm detailForm,
      @ModelAttribute AccountAndBalanceForm accountAndBalanceForm,
      @ModelAttribute ShopForm shopForm,
      @ModelAttribute BalanceTypeForm balanceTypeForm,
      @ModelAttribute AccountSourceForm accountSourceForm,
      @ModelAttribute AccountDestinationForm accountDestinationForm,
      @AuthenticationPrincipal UserDetails userDetails,
      @ModelAttribute CurrencyListForm currencyListForm,
      @ModelAttribute UnitForm unitForm,
      @ModelAttribute TaxTypeForm taxTypeForm,
      @ModelAttribute TaxRateForm taxRateForm,
      Model model) {

    shopForm = new ShopForm(shopService);
    shopForm.setShopId(shopId);
    groupForm.setShopId(shopForm.getShopId());
    groupForm.setShopForm(shopForm);

    BalanceType balanceType
        = balanceTypeService.findById(
        balanceTypeId);
    groupForm.setBalanceTypeId(balanceType.getBalanceTypeId());
    groupForm.setBalanceTypeToForm(balanceType);

    accountSourceForm = new AccountSourceForm(
        accountSourceService)
        .setAccountSourceFormByDB(
            accountSourceId);
    accountAndBalanceForm.setAccountSourceId(accountSourceId);
    accountAndBalanceForm.setAccountSourceForm(accountSourceForm);

    accountDestinationForm = new AccountDestinationForm(
        accountDestinationService)
        .setAccountDestinationFormByDB(
            accountDestinationId);
    accountAndBalanceForm.setAccountDestinationId(
        accountDestinationId);
    accountAndBalanceForm.setAccountDestinationForm(
        accountDestinationForm);

    groupForm.setAccountAndBalanceId(
        accountAndBalanceForm.getAccountAndBalanceId());
    groupForm.setAccountAndBalanceForm(
        accountAndBalanceForm);

//    groupForm.setUsersDetail(
//        convertUserDetailsToKakeiPonUsersDetails(
//            userDetails));

    detailForm
        = new SpecificationForm(
        specificationService,
        specificationGroupId);
    detailForm.setDeleted(false);

    List<SpecificationForm> specificationForms
        = specificationService.findBySpecificationGroupIdToForm(
        specificationGroupId,
        userId,
        false);

    if (specificationForms.size() == 1) {
      currencyListForm = new CurrencyListForm(
          currencyListService,
          specificationForms.getFirst().getCurrencyId());
      specificationForms.getFirst().setCurrencyId(
          currencyListForm.getCurrencyId());
      specificationForms.getFirst().setCurrencyListForm(currencyListForm);

      unitForm = new UnitForm(
          unitService)
          .setUnitFormByDB(
              specificationForms.getFirst().getUnitId());
      specificationForms.getFirst().setUnitId(unitForm.getUnitId());
      specificationForms.getFirst().setUnitForm(unitForm);

      taxTypeForm = new TaxTypeForm(
          taxTypeService)
          .setTaxTypeFormByDB(
              specificationForms.getFirst().getTaxTypeId());
      specificationForms.getFirst().setTaxTypeId(taxTypeForm.getTaxTypeId());
      specificationForms.getFirst().setTaxTypeForm(taxTypeForm);

      taxRateForm = new TaxRateForm(
          taxRateService)
          .setTaxRateFormByDB(
              specificationForms.getFirst().getTaxRateId());
      specificationForms.getFirst().setTaxRateId(taxRateForm.getTaxRateId());
      specificationForms.getFirst().setTaxRateForm(taxRateForm);
    }

    model.addAttribute(
        "specificationGroupForm", groupForm);
    model.addAttribute(
        "specificationForm", detailForm);
    model.addAttribute(
        "currencyLists", currencyListService.findAll());
    model.addAttribute(
        "units", unitService.findAll());
    model.addAttribute(
        "taxTypes", taxTypeService.findAll());
    model.addAttribute(
        "taxRates", taxRateService.findAll());
    model.addAttribute("userDetails", userDetails);
    model.addAttribute(
        "accountAndBalanceForm",
        accountAndBalanceForm);
    model.addAttribute("shopForm", shopForm);
    model.addAttribute(
        "balanceTypes",
        balanceTypeService.findAll());
    model.addAttribute(
        "selectedBalanceTypeId",
        groupForm.getBalanceTypeId());
    model.addAttribute(
        "accountSourceForm", accountSourceForm);
    model.addAttribute(
        "accountDestinationForm",
        accountDestinationForm);

    return "/spec/createDetail";
  }

  @PostMapping(value = "/spec/edit/detail/{specificationGroupId}/{specificationId}/{userId}/{accountAndBalanceId}/{shopId}/{balanceTypeId}/{accountSourceId}/{accountDestinationId}")
  String editSpecification(
      @PathVariable("specificationGroupId") Long specificationGroupId,
      @PathVariable("specificationId") Long specificationId,
      @PathVariable("userId") Long userId,
      @PathVariable("accountAndBalanceId") Long accountAndBalanceId,
      @PathVariable("shopId") Long shopId,
      @PathVariable("balanceTypeId") Long balanceTypeId,
      @PathVariable("accountSourceId") Long accountSourceId,
      @PathVariable("accountDestinationId") Long accountDestinationId,
      @ModelAttribute SpecificationGroupForm groupForm,
      @ModelAttribute SpecificationForm detailForm,
      @ModelAttribute AccountAndBalanceForm accountAndBalanceForm,
      @ModelAttribute ShopForm shopForm,
      @ModelAttribute AccountSourceForm accountSourceForm,
      @ModelAttribute AccountDestinationForm accountDestinationForm,
      @ModelAttribute BalanceType balanceType,
      @ModelAttribute CurrencyListForm currencyListForm,
      @ModelAttribute UnitForm unitForm,
      @ModelAttribute TaxTypeForm taxTypeForm,
      @ModelAttribute TaxRateForm taxRateForm,
      @AuthenticationPrincipal UserDetails userDetails,
      Model model) {

    accountSourceForm = new AccountSourceForm(
        accountSourceService)
        .setAccountSourceFormByDB(
            accountSourceId);
    accountDestinationForm = new AccountDestinationForm(
        accountDestinationService)
        .setAccountDestinationFormByDB(
            accountDestinationId);

    Specification specification
        = specificationService.findBySpecificationGroupIdAndSpecificationIdAndIdAndDeleted(
        specificationGroupId,
        specificationId,
        userDetails.getUsername(),
        false);

    detailForm = detailForm.setSpecificationToForm(
        specification);

//    groupForm.setUsersDetail(
//        convertUserDetailsToKakeiPonUsersDetails(
//            userDetails));

    shopForm = new ShopForm(shopService);
    shopForm.setShopId(shopId);
    groupForm.setShopId(shopForm.getShopId());
    groupForm.setShopForm(shopForm);

    model.addAttribute(
        "specificationGroupForm", groupForm);
    model.addAttribute(
        "specificationForm", detailForm);
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
    model.addAttribute("userDetails", userDetails);

    return "/spec/editDetail";
  }

  @PostMapping(value = "/spec/save/group")
  String saveGroup(
      @ModelAttribute SpecificationGroupForm groupForm,
      @ModelAttribute SpecificationForm detailForm,
      @ModelAttribute Specification specification,
      @ModelAttribute AccountAndBalanceForm accountAndBalanceForm,
      @AuthenticationPrincipal UserDetails userDetails,
      @ModelAttribute ShopForm shopForm,
      @ModelAttribute BalanceTypeForm balanceTypeForm,
      Model model) {

    double totalAmount = Double.parseDouble("0.0");

    Long balanceTypeId = groupForm.getBalanceTypeId();
    String groupMemo = groupForm.getGroupMemo();

    SpecificationGroup group
        = specificationGroupService.findBySpecificationGroupIdAndUserIdAndDeleted(
        specificationGroupService.getMaxGroupId(),
        null,
        false);
    groupForm = groupForm.setSpecificationGroupToForm(group);

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

//    groupForm.setUsersDetail(
//        convertUserDetailsToKakeiPonUsersDetails(
//            userDetails));

    Shop shop = shopService.findById(shopForm.getShopId());
    groupForm.setShopId(shop.getShopId());
    shopForm = shopForm.setShopToForm(shop);
    groupForm.setShopForm(shopForm);

    BalanceType balanceType
        = balanceTypeService.findById(
        balanceTypeForm.getBalanceTypeId());
    groupForm.setBalanceTypeId(balanceType.getBalanceTypeId());
    groupForm.setBalanceTypeToForm(balanceType);

    SpecificationGroup specGroup = groupForm.toEntity();
    specificationGroupService.updateSpecificationGroup(specGroup);

    List<SpecificationForm> detailForms
        = specificationService.findBySpecificationGroupIdToForm(
        groupForm.getSpecificationGroupId(),
        null,
        false);

    balanceType
        = balanceTypeService.findById(balanceTypeId);
    groupForm.setBalanceTypeId(balanceType.getBalanceTypeId());
    groupForm.setBalanceTypeToForm(balanceType);

    for (var specForm : detailForms) {
      if (specForm.getTaxTypeForm().getTaxTypeId() == 1) {
        if (specForm.getCurrencyListForm().getCurrencyId() == 1) {
          totalAmount += (specForm.getPrice()
              * specForm.getQuantity()
              * (1.0 + ((double) specForm.getTaxRateForm().getTaxRate() / 100)));
        }
      } else {
        if (specForm.getCurrencyListForm().getCurrencyId() == 1) {
          totalAmount += (specForm.getPrice()
              * specForm.getQuantity());
        }
      }
    }

    model.addAttribute("specificationGroupForm", groupForm);
    model.addAttribute("specificationForms", detailForms);
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
            accountSourceService)
            .setAccountSourceFormByDB(
                groupForm
                    .getAccountAndBalanceForm()
                    .getAccountSourceId()));
    model.addAttribute(
        "accountDestinationForm",
        new AccountDestinationForm(
            accountDestinationService)
            .setAccountDestinationFormByDB(
                groupForm
                    .getAccountAndBalanceForm()
                    .getAccountDestinationId()));
    model.addAttribute("userDetails", userDetails);
    model.addAttribute("balanceTypes", balanceTypeService.findAll());
    model.addAttribute("totalAmount", Math.round(totalAmount));

    return "/spec/editGroup";
  }

  @PostMapping(value = "/spec/update/group/{specificationGroupId}/{userId}")
  String updateGroup(
      @PathVariable("specificationGroupId") Long specificationGroupId,
      @PathVariable("userId") Long userId,
      @ModelAttribute SpecificationGroupForm groupForm,
      @ModelAttribute SpecificationForm detailForm,
      @ModelAttribute Specification specification,
      @ModelAttribute AccountAndBalanceForm accountAndBalanceForm,
      @AuthenticationPrincipal UserDetails userDetails,
      @ModelAttribute ShopForm shopForm,
      @ModelAttribute BalanceTypeForm balanceTypeForm,
      @ModelAttribute CurrencyListForm currencyListForm,
      @ModelAttribute UnitForm unitForm,
      @ModelAttribute TaxTypeForm taxTypeForm,
      @ModelAttribute TaxRateForm taxRateForm,
      Model model) {

    double totalAmount = Double.parseDouble("0.0");

    Long balanceTypeId = groupForm.getBalanceTypeId();
    String groupMemo = groupForm.getGroupMemo();

    SpecificationGroup group
        = specificationGroupService
        .findBySpecificationGroupIdAndUserIdAndDeleted(
            specificationGroupId,
            userId,
            false);
    groupForm = groupForm.setSpecificationGroupToForm(group);

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

//    groupForm.setUsersDetail(
//        convertUserDetailsToKakeiPonUsersDetails(
//            userDetails));

    Shop shop = shopService.findById(shopForm.getShopId());
    groupForm.setShopId(shop.getShopId());
    shopForm = shopForm.setShopToForm(shop);
    groupForm.setShopForm(shopForm);

    BalanceType balanceType
        = balanceTypeService.findById(
        balanceTypeForm.getBalanceTypeId());
    groupForm.setBalanceTypeId(balanceType.getBalanceTypeId());
    groupForm.setBalanceTypeToForm(balanceType);

    SpecificationGroup specGroup = groupForm.toEntity();
    specificationGroupService.updateSpecificationGroup(specGroup);

    balanceType
        = balanceTypeService.findById(
        balanceTypeId);
    groupForm.setBalanceTypeId(balanceType.getBalanceTypeId());
    groupForm.setBalanceTypeToForm(balanceType);

    List<SpecificationForm> detailForms
        = specificationService.findBySpecificationGroupIdToForm(
        groupForm.getSpecificationGroupId(),
        userId,
        false);

    for (var specForm : detailForms) {
      currencyListForm = new CurrencyListForm(
          currencyListService,
          specForm.getCurrencyId());
      specForm.setCurrencyId(
          currencyListForm.getCurrencyId());
      specForm.setCurrencyListForm(currencyListForm);

      unitForm = new UnitForm(
          unitService)
          .setUnitFormByDB(
              specForm.getUnitId());
      specForm.setUnitId(unitForm.getUnitId());
      specForm.setUnitForm(unitForm);

      taxTypeForm = new TaxTypeForm(
          taxTypeService)
          .setTaxTypeFormByDB(
              specForm.getTaxTypeId());
      specForm.setTaxTypeId(taxTypeForm.getTaxTypeId());
      specForm.setTaxTypeForm(taxTypeForm);

      taxRateForm = new TaxRateForm(
          taxRateService)
          .setTaxRateFormByDB(
              specForm.getTaxRateId());
      specForm.setTaxRateId(taxRateForm.getTaxRateId());
      specForm.setTaxRateForm(taxRateForm);

      if (taxTypeForm.getTaxTypeId() == 1) {
        if (currencyListForm.getCurrencyId() == 1) {
          totalAmount += (specForm.getPrice()
              * specForm.getQuantity()
              * (1.0 + ((double) taxRateForm.getTaxRate() / 100)));
        }
      } else {
        if (currencyListForm.getCurrencyId() == 1) {
          totalAmount += (specForm.getPrice()
              * specForm.getQuantity());
        }
      }
    }

    model.addAttribute("specificationGroupForm", groupForm);
    model.addAttribute("specificationForms", detailForms);
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
            accountSourceService)
            .setAccountSourceFormByDB(
                groupForm
                    .getAccountAndBalanceForm()
                    .getAccountSourceId()));
    model.addAttribute(
        "accountDestinationForm",
        new AccountDestinationForm(
            accountDestinationService)
            .setAccountDestinationFormByDB(
                groupForm
                    .getAccountAndBalanceForm()
                    .getAccountDestinationId()));
    model.addAttribute("userDetails", userDetails);
    model.addAttribute("balanceTypes", balanceTypeService.findAll());
    model.addAttribute("selectedBalanceTypeId", groupForm.getBalanceTypeId());
    model.addAttribute("totalAmount", Math.round(totalAmount));

    return "/spec/editGroup";
  }

  @PostMapping(value = "/spec/save/create/detail/{specificationGroupId}/{userId}/{accountAndBalanceId}/{shopId}/{balanceTypeId}/{accountSourceId}/{accountDestinationId}")
  String saveCreateDetail(
      @PathVariable("specificationGroupId") Long specificationGroupId,
      @PathVariable("userId") Long userId,
      @PathVariable("accountAndBalanceId") Long accountAndBalanceId,
      @PathVariable("shopId") Long shopId,
      @PathVariable("balanceTypeId") Long balanceTypeId,
      @PathVariable("accountSourceId") Long accountSourceId,
      @PathVariable("accountDestinationId") Long accountDestinationId,
      @ModelAttribute SpecificationGroupForm groupForm,
      @ModelAttribute SpecificationForm detailForm,
      @ModelAttribute AccountAndBalanceForm accountAndBalanceForm,
      @ModelAttribute ShopForm shopForm,
      @ModelAttribute BalanceTypeForm balanceTypeForm,
      @ModelAttribute AccountSourceForm accountSourceForm,
      @ModelAttribute AccountDestinationForm accountDestinationForm,
      @AuthenticationPrincipal UserDetails userDetails,
      @ModelAttribute CurrencyListForm currencyListForm,
      @ModelAttribute UnitForm unitForm,
      @ModelAttribute TaxTypeForm taxTypeForm,
      @ModelAttribute TaxRateForm taxRateForm,
      Model model) {

    double totalAmount = Double.parseDouble("0.0");

    Long previousBalanceTypeId = groupForm.getBalanceTypeId();
    String groupMemo = groupForm.getGroupMemo();

    detailForm.setSpecificationGroupId(
        specificationGroupId);

    groupForm.setAccountAndBalanceId(
        accountAndBalanceForm.getAccountAndBalanceId());
    groupForm.setAccountAndBalanceForm(
        accountAndBalanceForm);

    Shop shop = shopService.findById(shopForm.getShopId());
    groupForm.setShopId(shop.getShopId());
    shopForm = shopForm.setShopToForm(shop);
    groupForm.setShopForm(shopForm);

    BalanceType balanceType
        = balanceTypeService.findById(
        balanceTypeId);
    groupForm.setBalanceTypeId(balanceType.getBalanceTypeId());
    groupForm.setBalanceTypeToForm(balanceType);

    accountSourceForm = new AccountSourceForm(
        accountSourceService)
        .setAccountSourceFormByDB(
            accountSourceId);
    accountAndBalanceForm.setAccountSourceId(accountSourceId);
    accountAndBalanceForm.setAccountSourceForm(accountSourceForm);

    accountDestinationForm
        = new AccountDestinationForm(
        accountDestinationService)
        .setAccountDestinationFormByDB(
            accountDestinationId);
    accountAndBalanceForm.setAccountDestinationId(
        accountDestinationId);
    accountAndBalanceForm.setAccountDestinationForm(
        accountDestinationForm);

    currencyListForm = new CurrencyListForm(
        currencyListService,
        detailForm.getCurrencyId());
    detailForm.setCurrencyId(
        currencyListForm.getCurrencyId());

    unitForm = new UnitForm(
        unitService)
        .setUnitFormByDB(
            detailForm.getUnitId());
    detailForm.setUnitId(unitForm.getUnitId());

    taxTypeForm = new TaxTypeForm(
        taxTypeService)
        .setTaxTypeFormByDB(
            detailForm.getTaxTypeId());
    detailForm.setTaxTypeId(taxTypeForm.getTaxTypeId());

    taxRateForm = new TaxRateForm(
        taxRateService)
        .setTaxRateFormByDB(
            detailForm.getTaxRateId());
    detailForm.setTaxRateId(taxRateForm.getTaxRateId());

    if (detailForm.getEntryDate() == null) {
      detailForm.setEntryDate(LocalDateTime.now());
    } else {
      detailForm.setUpdateDate(LocalDateTime.now());
    }

    specificationService.saveAndFlushSpecification(
        detailForm.toEntity());

    List<SpecificationForm> detailForms
        = specificationService.findBySpecificationGroupIdToForm(
        specificationGroupId,
        userId,
        false);

    for (var specForm : detailForms) {
      currencyListForm = new CurrencyListForm(
          currencyListService,
          specForm.getCurrencyId());
      specForm.setCurrencyId(
          currencyListForm.getCurrencyId());
      specForm.setCurrencyListForm(currencyListForm);

      unitForm = new UnitForm(
          unitService)
          .setUnitFormByDB(
              specForm.getUnitId());
      specForm.setUnitId(unitForm.getUnitId());
      specForm.setUnitForm(unitForm);

      taxTypeForm = new TaxTypeForm(
          taxTypeService)
          .setTaxTypeFormByDB(
              specForm.getTaxTypeId());
      specForm.setTaxTypeId(taxTypeForm.getTaxTypeId());
      specForm.setTaxTypeForm(taxTypeForm);

      taxRateForm = new TaxRateForm(
          taxRateService)
          .setTaxRateFormByDB(
              specForm.getTaxRateId());
      specForm.setTaxRateId(taxRateForm.getTaxRateId());
      specForm.setTaxRateForm(taxRateForm);

      if (taxTypeForm.getTaxTypeId() == 1) {
        if (currencyListForm.getCurrencyId() == 1) {
          totalAmount += (specForm.getPrice()
              * specForm.getQuantity()
              * (1.0 + ((double) taxRateForm.getTaxRate() / 100)));
        }
      } else {
        if (currencyListForm.getCurrencyId() == 1) {
          totalAmount += (specForm.getPrice()
              * specForm.getQuantity());
        }
      }
    }

    SpecificationGroup group
        = specificationGroupService
        .findBySpecificationGroupIdAndUserIdAndDeleted(
            specificationGroupId,
            userId,
            false);

    groupForm = groupForm.setSpecificationGroupToForm(group);

    balanceType
        = balanceTypeService.findById(
        previousBalanceTypeId);
    groupForm.setBalanceTypeId(balanceType.getBalanceTypeId());
    groupForm.setBalanceTypeToForm(balanceType);

    groupForm.setGroupMemo(group.getGroupMemo());

    model.addAttribute(
        "specificationGroupForm", groupForm);
    model.addAttribute(
        "specificationForms", detailForms);
    model.addAttribute("currencyListForm",
        currencyListForm);
    model.addAttribute("unitForm", unitForm);
    model.addAttribute("taxTypeForm", taxTypeForm);
    model.addAttribute("taxRateForm", taxRateForm);
    model.addAttribute("userDetails", userDetails);
    model.addAttribute(
        "accountAndBalanceForm", accountAndBalanceForm);
    model.addAttribute("shopForm", shopForm);
    model.addAttribute(
        "balanceTypes", balanceTypeService.findAll());
    model.addAttribute(
        "selectedBalanceTypeId",
        groupForm.getBalanceTypeId());
    model.addAttribute(
        "accountSourceForm", accountSourceForm);
    model.addAttribute(
        "accountDestinationForm",
        accountDestinationForm);
    model.addAttribute("totalAmount", Math.round(totalAmount));

    return "/spec/editGroup";
  }

  @PostMapping(value = "/spec/save/edit/detail/{specificationGroupId}/{specificationId}/{id}/{accountAndBalanceId}/{shopId}/{balanceTypeId}/{accountSourceId}/{accountDestinationId}")
  String saveEditDetail(
      @PathVariable("specificationGroupId") Long specificationGroupId,
      @PathVariable("specificationId") Long specificationId,
      @PathVariable("userId") Long userId,
      @PathVariable("accountAndBalanceId") Long accountAndBalanceId,
      @PathVariable("shopId") Long shopId,
      @PathVariable("balanceTypeId") Long balanceTypeId,
      @PathVariable("accountSourceId") Long accountSourceId,
      @PathVariable("accountDestinationId") Long accountDestinationId,
      @ModelAttribute SpecificationGroupForm groupForm,
      @ModelAttribute SpecificationForm detailForm,
      @ModelAttribute AccountAndBalanceForm accountAndBalanceForm,
      @AuthenticationPrincipal UserDetails userDetails,
      @ModelAttribute CurrencyListForm currencyListForm,
      @ModelAttribute UnitForm unitForm,
      @ModelAttribute TaxTypeForm taxTypeForm,
      @ModelAttribute TaxRateForm taxRateForm,
      Model model) {

    double totalAmount = Double.parseDouble("0.0");

    Long previousBalanceTypeId = groupForm.getBalanceTypeId();

    groupForm.setAccountAndBalanceId(
        accountAndBalanceForm.getAccountAndBalanceId());
    groupForm.setAccountAndBalanceForm(
        accountAndBalanceForm);

    Shop shop = shopService.findById(shopId);
    groupForm.setShopId(shop.getShopId());
    ShopForm shopForm = new ShopForm(shopService);
    shopForm = shopForm.setShopToForm(shop);
    groupForm.setShopForm(shopForm);

    BalanceType balanceType
        = balanceTypeService.findById(
        balanceTypeId);
    groupForm.setBalanceTypeId(balanceType.getBalanceTypeId());
    groupForm.setBalanceTypeToForm(balanceType);

    AccountSourceForm accountSourceForm = new AccountSourceForm(
        accountSourceService)
        .setAccountSourceFormByDB(
            accountSourceId);
    accountAndBalanceForm.setAccountSourceId(accountSourceId);
    accountAndBalanceForm.setAccountSourceForm(accountSourceForm);

    AccountDestinationForm accountDestinationForm
        = new AccountDestinationForm(
        accountDestinationService)
        .setAccountDestinationFormByDB(
            accountDestinationId);
    accountAndBalanceForm.setAccountDestinationId(
        accountDestinationId);
    accountAndBalanceForm.setAccountDestinationForm(
        accountDestinationForm);
    groupForm.setAccountAndBalanceForm(
        accountAndBalanceForm);

    currencyListForm = new CurrencyListForm(
        currencyListService,
        detailForm.getCurrencyId());
    detailForm.setCurrencyId(
        currencyListForm.getCurrencyId());

    unitForm = new UnitForm(
        unitService)
        .setUnitFormByDB(
            detailForm.getUnitId());
    detailForm.setUnitId(unitForm.getUnitId());

    taxTypeForm = new TaxTypeForm(
        taxTypeService)
        .setTaxTypeFormByDB(
            detailForm.getTaxTypeId());
    detailForm.setTaxTypeId(taxTypeForm.getTaxTypeId());

    taxRateForm = new TaxRateForm(
        taxRateService)
        .setTaxRateFormByDB(
            detailForm.getTaxRateId());
    detailForm.setTaxRateId(taxRateForm.getTaxRateId());

    if (detailForm.getEntryDate() == null) {
      detailForm.setEntryDate(LocalDateTime.now());
    } else {
      detailForm.setUpdateDate(LocalDateTime.now());
    }

    detailForm.setDeleted(false);

    specificationService.saveAndFlushSpecification(detailForm.toEntity());

    List<SpecificationForm> detailForms
        = specificationService.findBySpecificationGroupIdToForm(
        specificationGroupId,
        userId,
        false);

    for (var specForm : detailForms) {
      currencyListForm = new CurrencyListForm(
          currencyListService,
          specForm.getCurrencyId());
      specForm.setCurrencyId(
          currencyListForm.getCurrencyId());
      specForm.setCurrencyListForm(currencyListForm);

      unitForm = new UnitForm(
          unitService)
          .setUnitFormByDB(
              specForm.getUnitId());
      specForm.setUnitId(unitForm.getUnitId());
      specForm.setUnitForm(unitForm);

      taxTypeForm = new TaxTypeForm(
          taxTypeService)
          .setTaxTypeFormByDB(
              specForm.getTaxTypeId());
      specForm.setTaxTypeId(taxTypeForm.getTaxTypeId());
      specForm.setTaxTypeForm(taxTypeForm);

      taxRateForm = new TaxRateForm(
          taxRateService)
          .setTaxRateFormByDB(
              specForm.getTaxRateId());
      specForm.setTaxRateId(taxRateForm.getTaxRateId());
      specForm.setTaxRateForm(taxRateForm);

      if (taxTypeForm.getTaxTypeId() == 1) {
        if (currencyListForm.getCurrencyId() == 1) {
          totalAmount += (specForm.getPrice()
              * specForm.getQuantity()
              * (1.0 + ((double) taxRateForm.getTaxRate() / 100)));
        }
      } else {
        if (currencyListForm.getCurrencyId() == 1) {
          totalAmount += (specForm.getPrice()
              * specForm.getQuantity());
        }
      }
    }

    balanceType
        = balanceTypeService.findById(
        previousBalanceTypeId);
    groupForm.setBalanceTypeId(balanceType.getBalanceTypeId());
    groupForm.setBalanceTypeToForm(balanceType);

    model.addAttribute(
        "specificationGroupForm", groupForm);
    model.addAttribute(
        "specificationForms", detailForms);
    model.addAttribute(
        "currencyListForm", currencyListForm);
    model.addAttribute("unitForm", unitForm);
    model.addAttribute("taxTypeForm", taxTypeForm);
    model.addAttribute("taxRateForm", taxRateForm);
    model.addAttribute("userDetails", userDetails);
    model.addAttribute(
        "accountAndBalanceForm", accountAndBalanceForm);
    model.addAttribute("shopForm", shopForm);
    model.addAttribute(
        "balanceTypes", balanceTypeService.findAll());
    model.addAttribute(
        "selectedBalanceTypeId",
        groupForm.getBalanceTypeId());
    model.addAttribute(
        "accountSourceForm", accountSourceForm);
    model.addAttribute(
        "accountDestinationForm",
        accountDestinationForm);
    model.addAttribute("totalAmount", Math.round(totalAmount));

    return "/spec/editGroup";
  }

  @GetMapping("/{specificationGroupId}/{userId}/{accountAndBalanceId}/{shopId}/{balanceTypeId}/{accountSourceId}/{accountDestinationId}/searchShop")
  String searchShop(
      @PathVariable("specificationGroupId") Long specificationGroupId,
      @PathVariable("userId") Long userId,
      @PathVariable("accountAndBalanceId") Long accountAndBalanceId,
      @PathVariable("shopId") Long shopId,
      @PathVariable("balanceTypeId") Long balanceTypeId,
      @PathVariable("accountSourceId") Long accountSourceId,
      @PathVariable("accountDestinationId") Long accountDestinationId,
      @ModelAttribute SpecificationGroupForm groupForm,
      @AuthenticationPrincipal UserDetails userDetails,
      Model model) {

    SpecificationGroup group
        = specificationGroupService
        .findBySpecificationGroupIdAndUserIdAndDeleted(
            specificationGroupId,
            userId,
            false);
    String groupMemo = group.getGroupMemo();

    groupForm.setUserId(userId);

    AccountAndBalance accountAndBalance
        = accountAndBalanceService.getById(accountAndBalanceId);

    List<Shop> shops = shopService.findAll();

    BalanceType balanceType
        = balanceTypeService.findById(
        balanceTypeId);
    groupForm.setBalanceTypeId(balanceType.getBalanceTypeId());
    groupForm.setBalanceTypeToForm(balanceType);

    AccountSource accountSource
        = accountSourceService.findById(accountSourceId);
    AccountDestination accountDestination
        = accountDestinationService.findById(accountDestinationId);

    groupForm.setGroupMemo(groupMemo);

    model.addAttribute(
        "specificationGroupForm", groupForm);
    model.addAttribute(
        "accountAndBalanceId", accountAndBalanceId);
    model.addAttribute("shops", shops);
    model.addAttribute(
        "balanceTypeForm", groupForm.getBalanceTypeForm());
    model.addAttribute(
        "accountSource", accountSource);
    model.addAttribute(
        "accountDestination", accountDestination);
    model.addAttribute(
        "userDetails", userDetails);

    return "/shop/showList";
  }

  @GetMapping("/{specificationGroupId}/{userId}/{accountAndBalanceId}/{shopId}/{balanceTypeId}/{accountSourceId}/{accountDestinationId}/searchAccountSource")
  String searchAccountSource(
      @PathVariable("specificationGroupId") Long specificationGroupId,
      @PathVariable("userId") Long userId,
      @PathVariable("accountAndBalanceId") Long accountAndBalanceId,
      @PathVariable("shopId") Long shopId,
      @PathVariable("balanceTypeId") Long balanceTypeId,
      @PathVariable("accountSourceId") Long accountSourceId,
      @PathVariable("accountDestinationId") Long accountDestinationId,
      @ModelAttribute SpecificationGroupForm groupForm,
      @AuthenticationPrincipal UserDetails userDetails,
      @ModelAttribute BalanceTypeForm balanceTypeForm,
      Model model) {

    SpecificationGroup group
        = specificationGroupService
        .findBySpecificationGroupIdAndUserIdAndDeleted(
            specificationGroupId,
            userId,
            false);
    String groupMemo = group.getGroupMemo();

    groupForm.setUserId(userId);

    Shop shop = shopService.findById(shopId);
    groupForm.setShopId(shop.getShopId());
    ShopForm shopForm = new ShopForm(shopService);
    shopForm = shopForm.setShopToForm(shop);
    groupForm.setShopForm(shopForm);

    AccountAndBalance accountAndBalance
        = accountAndBalanceService.getById(accountAndBalanceId);

    List<AccountSource> accountSources = accountSourceService.findAll();
    AccountDestinationForm accountDestinationForm
        = new AccountDestinationForm(
        accountDestinationService)
        .setAccountDestinationFormByDB(
            accountDestinationId);

    groupForm.setGroupMemo(groupMemo);

    model.addAttribute(
        "accountAndBalanceId", accountAndBalanceId);
    model.addAttribute("shopForm", shopForm);
    model.addAttribute(
        "balanceTypeForm", balanceTypeForm);
    model.addAttribute(
        "accountSources", accountSources);
    model.addAttribute(
        "accountDestinationForm",
        accountDestinationForm);
    model.addAttribute(
        "userDetails", userDetails);

    return "/account/showSourceList";
  }

  @GetMapping("/{specificationGroupId}/{userId}/{accountAndBalanceId}/{shopId}/{balanceTypeId}/{accountSourceId}/{accountDestinationId}/searchAccountDestination")
  String searchAccountDestination(
      @PathVariable("specificationGroupId") Long specificationGroupId,
      @PathVariable("userId") Long userId,
      @PathVariable("accountAndBalanceId") Long accountAndBalanceId,
      @PathVariable("shopId") Long shopId,
      @PathVariable("balanceTypeId") Long balanceTypeId,
      @PathVariable("accountSourceId") Long accountSourceId,
      @PathVariable("accountDestinationId") Long accountDestinationId,
      @ModelAttribute SpecificationGroupForm groupForm,
      @AuthenticationPrincipal UserDetails userDetails,
      Model model) {

    SpecificationGroup group
        = specificationGroupService
        .findBySpecificationGroupIdAndUserIdAndDeleted(
            specificationGroupId,
            userId,
            false);
    String groupMemo = group.getGroupMemo();

    groupForm.setUserId(userId);

    AccountAndBalance accountAndBalance
        = accountAndBalanceService.getById(accountAndBalanceId);

    Shop shop = shopService.findById(shopId);
    groupForm.setShopId(shop.getShopId());
    ShopForm shopForm = new ShopForm(shopService);
    shopForm = shopForm.setShopToForm(shop);
    groupForm.setShopForm(shopForm);

    BalanceType balanceType
        = balanceTypeService.findById(
        balanceTypeId);
    groupForm.setBalanceTypeId(balanceType.getBalanceTypeId());
    groupForm.setBalanceTypeToForm(balanceType);

    List<BalanceType> balanceTypes = balanceTypeService.findAll();


    AccountSourceForm accountSourceForm
        = new AccountSourceForm(
        accountSourceService)
        .setAccountSourceFormByDB(
            accountSourceId);
    List<AccountDestination> accountDestinations
        = accountDestinationService.findAll();

    groupForm.setGroupMemo(groupMemo);

    model.addAttribute(
        "accountAndBalanceId", accountAndBalanceId);
    model.addAttribute(
        "shopForm", shopForm);
    model.addAttribute(
        "balanceTypeForm", groupForm.getBalanceTypeForm());
    model.addAttribute(
        "accountSourceForm", accountSourceForm);
    model.addAttribute(
        "accountDestinations", accountDestinations);
    model.addAttribute(
        "userDetails", userDetails);

    return "/account/showDestinationList";
  }

  @RequestMapping(value = "/{specificationGroupId}/{userId}/{accountAndBalanceId}/{shopId}/{balanceTypeId}/{accountSourceId}/{accountDestinationId}/setShop",
      method = RequestMethod.GET)
  String setShopToSpecificationGroup(
      @PathVariable("specificationGroupId") Long specificationGroupId,
      @PathVariable("userId") Long userId,
      @PathVariable("accountAndBalanceId") Long accountAndBalanceId,
      @PathVariable("shopId") Long shopId,
      @PathVariable("balanceTypeId") Long balanceTypeId,
      @PathVariable("accountSourceId") Long accountSourceId,
      @PathVariable("accountDestinationId") Long accountDestinationId,
      @ModelAttribute SpecificationGroupForm groupForm,
      @ModelAttribute CurrencyListForm currencyListForm,
      @AuthenticationPrincipal UserDetails userDetails,
      @ModelAttribute UnitForm unitForm,
      @ModelAttribute TaxTypeForm taxTypeForm,
      @ModelAttribute TaxRateForm taxRateForm,
      Model model) {

    double totalAmount = Double.parseDouble("0.0");

    SpecificationGroup group
        = specificationGroupService
        .findBySpecificationGroupIdAndUserIdAndDeleted(
            specificationGroupId,
            userId,
            false);
    String groupMemo = group.getGroupMemo();

//    groupForm.setUsersDetail(
//        convertUserDetailsToKakeiPonUsersDetails(
//            userDetails));

    Shop shop = shopService.findById(shopId);
    groupForm.setShopId(shop.getShopId());
    ShopForm shopForm = new ShopForm(shopService);
    shopForm = shopForm.setShopToForm(shop);
    groupForm.setShopForm(shopForm);

    BalanceType balanceType
        = balanceTypeService.findById(
        groupForm.getBalanceTypeId());
    groupForm.setBalanceTypeId(balanceType.getBalanceTypeId());
    groupForm.setBalanceTypeToForm(balanceType);

    List<BalanceType> balanceTypes = balanceTypeService.findAll();

    AccountAndBalance accountAndBalance
        = accountAndBalanceService.getById(accountAndBalanceId);
    groupForm.setAccountAndBalanceId(
        accountAndBalance.getAccountAndBalanceId());
    groupForm.setAccountAndBalanceToForm(accountAndBalance);

    groupForm.getAccountAndBalanceForm().setAccountSourceId(accountSourceId);
    AccountSourceForm accountSourceForm
        = new AccountSourceForm(
        accountSourceService)
        .setAccountSourceFormByDB(
            accountSourceId);
    groupForm.getAccountAndBalanceForm().setAccountSourceForm(
        accountSourceForm);

    groupForm.getAccountAndBalanceForm().setAccountDestinationId(
        accountDestinationId);
    AccountDestinationForm accountDestinationForm
        = new AccountDestinationForm(
        accountDestinationService)
        .setAccountDestinationFormByDB(
            accountDestinationId);
    groupForm.getAccountAndBalanceForm().setAccountDestinationForm(
        accountDestinationForm);

    groupForm.setGroupMemo(groupMemo);

    List<SpecificationForm> detailForms
        = specificationService.findBySpecificationGroupIdToForm(
        specificationGroupId,
        userId,
        false);

    for (var specForm : detailForms) {
      currencyListForm = new CurrencyListForm(
          currencyListService,
          specForm.getCurrencyId());
      specForm.setCurrencyId(
          currencyListForm.getCurrencyId());
      specForm.setCurrencyListForm(currencyListForm);

      unitForm = new UnitForm(
          unitService)
          .setUnitFormByDB(
              specForm.getUnitId());
      specForm.setUnitId(unitForm.getUnitId());
      specForm.setUnitForm(unitForm);

      taxTypeForm = new TaxTypeForm(
          taxTypeService)
          .setTaxTypeFormByDB(
              specForm.getTaxTypeId());
      specForm.setTaxTypeId(taxTypeForm.getTaxTypeId());
      specForm.setTaxTypeForm(taxTypeForm);

      taxRateForm = new TaxRateForm(
          taxRateService)
          .setTaxRateFormByDB(
              specForm.getTaxRateId());
      specForm.setTaxRateId(taxRateForm.getTaxRateId());
      specForm.setTaxRateForm(taxRateForm);

      if (taxTypeForm.getTaxTypeId() == 1) {
        if (currencyListForm.getCurrencyId() == 1) {
          totalAmount += (specForm.getPrice()
              * specForm.getQuantity()
              * (1.0 + ((double) taxRateForm.getTaxRate() / 100)));
        }
      } else {
        if (currencyListForm.getCurrencyId() == 1) {
          totalAmount += (specForm.getPrice()
              * specForm.getQuantity());
        }
      }
    }

    model.addAttribute("groupForm", groupForm);
    model.addAttribute(
        "specificationForms", detailForms);
    model.addAttribute(
        "accountAndBalanceId", accountAndBalanceId);
    model.addAttribute("shopForm", shopForm);
    model.addAttribute(
        "accountSourceForm", accountSourceForm);
    model.addAttribute(
        "accountDestinationForm",
        accountDestinationForm);
    model.addAttribute("userDetails", userDetails);
    model.addAttribute(
        "balanceTypes", balanceTypes);
    model.addAttribute(
        "selectedBalanceTypeId",
        groupForm.getBalanceTypeId());
    model.addAttribute(
        "currencyListForm", currencyListForm);
    model.addAttribute("unitForm", unitForm);
    model.addAttribute(
        "taxTypeForm", taxTypeForm);
    model.addAttribute(
        "taxRateForm", taxRateForm);
    model.addAttribute(
        "totalAmount", Math.round(totalAmount));

    if (specificationGroupId == null) {
      return "/spec/createGroup";
    } else {
      return "/spec/editGroup";
    }
  }

  @RequestMapping(
      value = "/{specificationGroupId}/{userId}/{accountAndBalanceId}/{shopId}/{balanceTypeId}/{accountSourceId}/{accountDestinationId}/setAccountSource",
      method = RequestMethod.GET)
  String setAccountSourceToAccountAndBalance(
      @PathVariable("specificationGroupId") Long specificationGroupId,
      @PathVariable("userId") Long userId,
      @PathVariable("accountAndBalanceId") Long accountAndBalanceId,
      @PathVariable("shopId") Long shopId,
      @PathVariable("balanceTypeId") Long balanceTypeId,
      @PathVariable("accountSourceId") Long accountSourceId,
      @PathVariable("accountDestinationId") Long accountDestinationId,
      @ModelAttribute SpecificationGroupForm groupForm,
      @ModelAttribute CurrencyListForm currencyListForm,
      @AuthenticationPrincipal UserDetails userDetails,
      @ModelAttribute UnitForm unitForm,
      @ModelAttribute TaxTypeForm taxTypeForm,
      @ModelAttribute TaxRateForm taxRateForm,
      Model model) {

    double totalAmount = Double.parseDouble("0.0");

    SpecificationGroup group
        = specificationGroupService
        .findBySpecificationGroupIdAndUserIdAndDeleted(
            specificationGroupId,
            userId,
            false);
    String groupMemo = group.getGroupMemo();

//    groupForm.setUsersDetail(
//        convertUserDetailsToKakeiPonUsersDetails(
//            userDetails));

    Shop shop = shopService.findById(shopId);
    groupForm.setShopId(shop.getShopId());
    ShopForm shopForm = new ShopForm(shopService);
    shopForm = shopForm.setShopToForm(shop);
    groupForm.setShopForm(shopForm);

    BalanceType balanceType
        = balanceTypeService.findById(
        balanceTypeId);
    groupForm.setBalanceTypeId(balanceType.getBalanceTypeId());
    groupForm.setBalanceTypeToForm(balanceType);

    List<BalanceType> balanceTypes = balanceTypeService.findAll();

    AccountAndBalance accountAndBalance
        = accountAndBalanceService.getById(accountAndBalanceId);
    groupForm.setAccountAndBalanceId(
        accountAndBalance.getAccountAndBalanceId());
    groupForm.setAccountAndBalanceToForm(accountAndBalance);

    groupForm.getAccountAndBalanceForm().setAccountSourceId(accountSourceId);
    AccountSourceForm accountSourceForm
        = new AccountSourceForm(
        accountSourceService)
        .setAccountSourceFormByDB(
            accountSourceId);
    groupForm.getAccountAndBalanceForm().setAccountSourceForm(
        accountSourceForm);

    groupForm.getAccountAndBalanceForm().setAccountDestinationId(
        accountDestinationId);
    AccountDestinationForm accountDestinationForm
        = new AccountDestinationForm(
        accountDestinationService)
        .setAccountDestinationFormByDB(
            accountDestinationId);
    groupForm.getAccountAndBalanceForm().setAccountDestinationForm(
        accountDestinationForm);

    groupForm.setGroupMemo(groupMemo);

    List<SpecificationForm> detailForms
        = specificationService.findBySpecificationGroupIdToForm(
        specificationGroupId,
        userId,
        false);

    for (var specForm : detailForms) {
      currencyListForm = new CurrencyListForm(
          currencyListService,
          specForm.getCurrencyId());
      specForm.setCurrencyId(
          currencyListForm.getCurrencyId());
      specForm.setCurrencyListForm(currencyListForm);

      unitForm = new UnitForm(
          unitService)
          .setUnitFormByDB(
              specForm.getUnitId());
      specForm.setUnitId(unitForm.getUnitId());
      specForm.setUnitForm(unitForm);

      taxTypeForm = new TaxTypeForm(
          taxTypeService)
          .setTaxTypeFormByDB(
              specForm.getTaxTypeId());
      specForm.setTaxTypeId(taxTypeForm.getTaxTypeId());
      specForm.setTaxTypeForm(taxTypeForm);

      taxRateForm = new TaxRateForm(
          taxRateService)
          .setTaxRateFormByDB(
              specForm.getTaxRateId());
      specForm.setTaxRateId(taxRateForm.getTaxRateId());
      specForm.setTaxRateForm(taxRateForm);

      if (taxTypeForm.getTaxTypeId() == 1) {
        if (currencyListForm.getCurrencyId() == 1) {
          totalAmount += (specForm.getPrice()
              * specForm.getQuantity()
              * (1.0 + ((double) taxRateForm.getTaxRate() / 100)));
        }
      } else {
        if (currencyListForm.getCurrencyId() == 1) {
          totalAmount += (specForm.getPrice()
              * specForm.getQuantity());
        }
      }
    }

    model.addAttribute(
        "groupForm", groupForm);
    model.addAttribute(
        "specificationForms", detailForms);
    model.addAttribute(
        "accountAndBalanceId", accountAndBalanceId);
    model.addAttribute("shopForm", shopForm);
    model.addAttribute(
        "accountSourceForm", accountSourceForm);
    model.addAttribute(
        "accountDestinationForm",
        accountDestinationForm);
    model.addAttribute("userDetails", userDetails);
    model.addAttribute("balanceTypes", balanceTypes);
    model.addAttribute(
        "selectedBalanceTypeId",
        groupForm.getBalanceTypeId());
    model.addAttribute(
        "currencyListForm", currencyListForm);
    model.addAttribute("unitForm", unitForm);
    model.addAttribute(
        "taxTypeForm", taxTypeForm);
    model.addAttribute(
        "taxRateForm", taxRateForm);
    model.addAttribute(
        "totalAmount", Math.round(totalAmount));

    if (specificationGroupId == null) {
      return "/spec/createGroup";
    } else {
      return "/spec/editGroup";
    }
  }

  @RequestMapping(
      value = "/{specificationGroupId}/{userId}/{accountAndBalanceId}/{shopId}/{balanceTypeId}/{accountSourceId}/{accountDestinationId}/setAccountDestination",
      method = RequestMethod.GET)
  String setAccountDestinationToAccountAndBalance(
      @PathVariable("specificationGroupId") Long specificationGroupId,
      @PathVariable("userId") Long userId,
      @PathVariable("accountAndBalanceId") Long accountAndBalanceId,
      @PathVariable("shopId") Long shopId,
      @PathVariable("balanceTypeId") Long balanceTypeId,
      @PathVariable("accountSourceId") Long accountSourceId,
      @PathVariable("accountDestinationId") Long accountDestinationId,
      @ModelAttribute SpecificationGroupForm groupForm,
      @ModelAttribute CurrencyListForm currencyListForm,
      @AuthenticationPrincipal UserDetails userDetails,
      @ModelAttribute UnitForm unitForm,
      @ModelAttribute TaxTypeForm taxTypeForm,
      @ModelAttribute TaxRateForm taxRateForm,
      Model model) {

    double totalAmount = Double.parseDouble("0.0");

    SpecificationGroup group
        = specificationGroupService
        .findBySpecificationGroupIdAndUserIdAndDeleted(
            specificationGroupId,
            userId,
            false);
    String groupMemo = group.getGroupMemo();

//    groupForm.setUsersDetail(
//        convertUserDetailsToKakeiPonUsersDetails(
//            userDetails));

    Shop shop = shopService.findById(shopId);
    groupForm.setShopId(shop.getShopId());
    ShopForm shopForm = new ShopForm(shopService);
    shopForm = shopForm.setShopToForm(shop);
    groupForm.setShopForm(shopForm);

    BalanceType balanceType
        = balanceTypeService.findById(
        balanceTypeId);
    groupForm.setBalanceTypeId(balanceType.getBalanceTypeId());
    groupForm.setBalanceTypeToForm(balanceType);

    List<BalanceType> balanceTypes = balanceTypeService.findAll();

    groupForm.setAccountAndBalanceId(
        accountAndBalanceId);
    AccountAndBalance accountAndBalance
        = accountAndBalanceService.getById(accountAndBalanceId);
    groupForm.setAccountAndBalanceToForm(accountAndBalance);

    groupForm.getAccountAndBalanceForm()
        .setAccountSourceId(
            accountSourceId);
    AccountSourceForm accountSourceForm
        = new AccountSourceForm(
        accountSourceService)
        .setAccountSourceFormByDB(
            accountSourceId);
    groupForm.getAccountAndBalanceForm()
        .setAccountSourceForm(
            accountSourceForm);

    groupForm.getAccountAndBalanceForm()
        .setAccountDestinationId(
            accountDestinationId);
    AccountDestinationForm accountDestinationForm
        = new AccountDestinationForm(
        accountDestinationService)
        .setAccountDestinationFormByDB(
            accountDestinationId);
    groupForm
        .getAccountAndBalanceForm()
        .setAccountDestinationForm(
            accountDestinationForm);

    groupForm.setGroupMemo(groupMemo);

    List<SpecificationForm> specificationForms
        = specificationService.findBySpecificationGroupIdToForm(
        specificationGroupId,
        userId,
        false);

    for (var specForm : specificationForms) {
      currencyListForm = new CurrencyListForm(
          currencyListService,
          specForm.getCurrencyId());
      specForm.setCurrencyId(
          currencyListForm.getCurrencyId());
      specForm.setCurrencyListForm(currencyListForm);

      unitForm = new UnitForm(
          unitService)
          .setUnitFormByDB(
              specForm.getUnitId());
      specForm.setUnitId(unitForm.getUnitId());
      specForm.setUnitForm(unitForm);

      taxTypeForm = new TaxTypeForm(
          taxTypeService)
          .setTaxTypeFormByDB(
              specForm.getTaxTypeId());
      specForm.setTaxTypeId(taxTypeForm.getTaxTypeId());
      specForm.setTaxTypeForm(taxTypeForm);

      taxRateForm = new TaxRateForm(
          taxRateService)
          .setTaxRateFormByDB(
              specForm.getTaxRateId());
      specForm.setTaxRateId(taxRateForm.getTaxRateId());
      specForm.setTaxRateForm(taxRateForm);

      if (taxTypeForm.getTaxTypeId() == 1) {
        if (currencyListForm.getCurrencyId() == 1) {
          totalAmount += (specForm.getPrice()
              * specForm.getQuantity()
              * (1.0 + ((double) taxRateForm.getTaxRate() / 100)));
        }
      } else {
        if (currencyListForm.getCurrencyId() == 1) {
          totalAmount += (specForm.getPrice()
              * specForm.getQuantity());
        }
      }
    }

    model.addAttribute("groupForm", groupForm);
    model.addAttribute(
        "specificationForms", specificationForms);
    model.addAttribute(
        "accountAndBalanceId", accountAndBalanceId);
    model.addAttribute("shopForm", shopForm);
    model.addAttribute(
        "accountSourceForm", accountSourceForm);
    model.addAttribute(
        "accountDestinationForm",
        accountDestinationForm);
    model.addAttribute("userDetails", userDetails);
    model.addAttribute("balanceTypes", balanceTypes);
    model.addAttribute(
        "selectedBalanceTypeId",
        groupForm.getBalanceTypeId());
    model.addAttribute(
        "currencyListForm", currencyListForm);
    model.addAttribute("unitForm", unitForm);
    model.addAttribute(
        "taxTypeForm", taxTypeForm);
    model.addAttribute(
        "taxRateForm", taxRateForm);
    model.addAttribute(
        "totalAmount", Math.round(totalAmount));

    if (specificationGroupId == null) {
      return "/spec/createGroup";
    } else {
      return "/spec/editGroup";
    }
  }

  @PostMapping(value = "/spec/delete/group/{specificationGroupId}/{userId}")
  String deleteGroup(
      @PathVariable("specificationGroupId") Long specificationGroupId,
      @PathVariable("userId") Long userId,
      @AuthenticationPrincipal UserDetails userDetails,
      Model model) {

    List<Specification> specifications
        = specificationService.findBySpecificationGroupIdAndUserIdAndDeleted(
        specificationGroupId,
        userId,
        false);
    for (var spec : specifications) {
      spec.setDeleted(true);
    }
    specificationService.saveAllSpecifications(specifications);

    SpecificationGroupForm specificationGroupForm
        = new SpecificationGroupForm(
        specificationGroupService,
        specificationService,
        accountAndBalanceService,
        accountSourceService,
        accountDestinationService,
        usersDetailService,
//        authoritiesService,
        usersExtService,
        shopService,
        balanceTypeService,
        currencyListService,
        unitService,
        taxTypeService,
        taxRateService)
        .setSpecificationGroupFormByDB(
            specificationGroupId,
            userId,
            false);

    specificationGroupForm.setDeleted(true);

    specificationGroupService
        .saveAndFlushSpecificationGroup(
            specificationGroupForm.toEntity());

    return "redirect:/spec";
  }

  @PostMapping(value = "/spec/delete/detail/{specificationGroupId}/{specificationId}/{userId}/{accountAndBalanceId}/{shopId}/{balanceTypeId}/{accountSourceId}/{accountDestinationId}")
  String deleteDetail(
      @PathVariable("specificationGroupId") Long specificationGroupId,
      @PathVariable("specificationId") Long specificationId,
      @PathVariable("userId") Long userId,
      @PathVariable("accountAndBalanceId") Long accountAndBalanceId,
      @PathVariable("shopId") Long shopId,
      @PathVariable("balanceTypeId") Long balanceTypeId,
      @PathVariable("accountSourceId") Long accountSourceId,
      @PathVariable("accountDestinationId") Long accountDestinationId,
      @ModelAttribute SpecificationGroupForm groupForm,
      @ModelAttribute SpecificationForm detailForm,
      @ModelAttribute AccountAndBalanceForm accountAndBalanceForm,
      @ModelAttribute UsersExtForm usersExtForm,
      @ModelAttribute ShopForm shopForm,
      @ModelAttribute BalanceTypeForm balanceTypeForm,
      @ModelAttribute AccountSourceForm accountSourceForm,
      @ModelAttribute AccountDestinationForm accountDestinationForm,
      @AuthenticationPrincipal UserDetails userDetails,
      Model model) {

    double totalAmount = Double.parseDouble("0.0");

    Long previousBalanceTypeId = groupForm.getBalanceTypeId();

    SpecificationGroup specificationGroup
        = specificationGroupService
        .findBySpecificationGroupIdAndUserIdAndDeleted(
            specificationGroupId,
            userId,
            false);

//    Authorities authorities
//        = authoritiesService.findByUsername(
//        userDetails.getUsername());
//    groupForm.setUsersDetail(
//        convertUserDetailsToKakeiPonUsersDetails(
//            userDetails));

    usersExtForm = usersExtService.findByUserIdToForm(userId);
    groupForm.setUsersExtForm(usersExtForm);

    Shop shop = shopService.findById(shopForm.getShopId());
    groupForm.setShopId(shop.getShopId());
    shopForm = shopForm.setShopToForm(shop);
    groupForm.setShopForm(shopForm);

    BalanceType balanceType
        = balanceTypeService.findById(
        balanceTypeId);
    groupForm.setBalanceTypeId(balanceType.getBalanceTypeId());
    groupForm.setBalanceTypeToForm(balanceType);

    groupForm.setReceivingAndPaymentDate(
        specificationGroup.getReceivingAndPaymentDate());
    groupForm.setReceivingAndPaymentTime(
        specificationGroup.getReceivingAndPaymentTime());

    accountSourceForm = new AccountSourceForm(
        accountSourceService)
        .setAccountSourceFormByDB(
            accountSourceId);
    accountAndBalanceForm.setAccountSourceId(accountSourceId);
    accountAndBalanceForm.setAccountSourceForm(accountSourceForm);

    accountDestinationForm = new AccountDestinationForm(
        accountDestinationService)
        .setAccountDestinationFormByDB(
            accountDestinationId);
    accountAndBalanceForm.setAccountDestinationId(
        accountDestinationId);
    accountAndBalanceForm.setAccountDestinationForm(
        accountDestinationForm);

    groupForm.setAccountAndBalanceId(
        accountAndBalanceForm.getAccountAndBalanceId());
    groupForm.setAccountAndBalanceForm(
        accountAndBalanceForm);

    groupForm.setBalanceTypeForm(balanceTypeForm);

    groupForm.setGroupMemo(
        specificationGroup.getGroupMemo());

    groupForm.setEntryDate(
        specificationGroup.getEntryDate());
    groupForm.setUpdateDate(
        specificationGroup.getUpdateDate());

    Specification specification
        = specificationService
        .findBySpecificationGroupIdAndSpecificationIdAndIdAndDeleted(
            specificationGroupId,
            specificationId,
            userDetails.getUsername(),
            false);
    detailForm = detailForm.setSpecificationToForm(
        specification);
    detailForm.setDeleted(true);
    specificationService.saveAndFlushSpecification(detailForm.toEntity());

    List<SpecificationForm> detailForms
        = specificationService.findBySpecificationGroupIdToForm(
        specificationGroupId,
        userId,
        false);

    for (var specForm : detailForms) {
      CurrencyListForm currencyListForm = new CurrencyListForm(
          currencyListService,
          specForm.getCurrencyId());
      specForm.setCurrencyId(currencyListForm.getCurrencyId());
      specForm.setCurrencyListForm(currencyListForm);

      UnitForm unitForm = new UnitForm(
          unitService)
          .setUnitFormByDB(
              specForm.getUnitId());
      specForm.setUnitId(unitForm.getUnitId());
      specForm.setUnitForm(unitForm);

      TaxTypeForm taxTypeForm = new TaxTypeForm(
          taxTypeService)
          .setTaxTypeFormByDB(
              specForm.getTaxTypeId());
      specForm.setTaxTypeId(taxTypeForm.getTaxTypeId());
      specForm.setTaxTypeForm(taxTypeForm);

      TaxRateForm taxRateForm = new TaxRateForm(
          taxRateService)
          .setTaxRateFormByDB(
              specForm.getTaxRateId());
      specForm.setTaxRateId(taxRateForm.getTaxRateId());
      specForm.setTaxRateForm(taxRateForm);

      if (taxTypeForm.getTaxTypeId() == 1) {
        if (currencyListForm.getCurrencyId() == 1) {
          totalAmount += (specForm.getPrice()
              * specForm.getQuantity()
              * (1.0 + ((double) taxRateForm.getTaxRate() / 100)));
        }
      } else {
        if (currencyListForm.getCurrencyId() == 1) {
          totalAmount += (specForm.getPrice()
              * specForm.getQuantity());
        }
      }
    }

    balanceType
        = balanceTypeService.findById(
        previousBalanceTypeId);
    groupForm.setBalanceTypeId(balanceType.getBalanceTypeId());
    groupForm.setBalanceTypeToForm(balanceType);

    model.addAttribute(
        "specificationGroupForm", groupForm);
    model.addAttribute(
        "specificationForms", detailForms);
    model.addAttribute(
        "balanceTypes", balanceTypeService.findAll());
    model.addAttribute(
        "selectedBalanceTypeId",
        groupForm.getBalanceTypeId());
    model.addAttribute(
        "accountAndBalanceId", accountAndBalanceId);
    model.addAttribute("shopForm", shopForm);
    model.addAttribute(
        "accountSourceForm", accountSourceForm);
    model.addAttribute(
        "accountDestinationForm",
        accountDestinationForm);
    model.addAttribute(
        "totalAmount", Math.round(totalAmount));

    return "/spec/editGroup";
  }
}
