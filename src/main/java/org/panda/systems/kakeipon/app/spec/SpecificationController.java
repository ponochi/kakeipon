package org.panda.systems.kakeipon.app.spec;

import org.panda.systems.kakeipon.app.account.AccountDestinationForm;
import org.panda.systems.kakeipon.app.account.AccountSourceForm;
import org.panda.systems.kakeipon.app.common.*;
import org.panda.systems.kakeipon.app.currency.CurrencyListForm;
import org.panda.systems.kakeipon.app.shop.ShopForm;
import org.panda.systems.kakeipon.app.user.UserExtForm;
import org.panda.systems.kakeipon.domain.model.account.AccountDestination;
import org.panda.systems.kakeipon.domain.model.account.AccountSource;
import org.panda.systems.kakeipon.domain.model.common.*;
import org.panda.systems.kakeipon.domain.model.spec.Specification;
import org.panda.systems.kakeipon.domain.model.spec.SpecificationGroup;
import org.panda.systems.kakeipon.domain.model.user.Authorities;
import org.panda.systems.kakeipon.domain.model.user.RoleName;
import org.panda.systems.kakeipon.domain.model.user.User;
import org.panda.systems.kakeipon.domain.model.user.UserExt;
import org.panda.systems.kakeipon.domain.service.account.AccountDestinationService;
import org.panda.systems.kakeipon.domain.service.account.AccountSourceService;
import org.panda.systems.kakeipon.domain.service.common.*;
import org.panda.systems.kakeipon.domain.service.currency.CurrencyListService;
import org.panda.systems.kakeipon.domain.service.spec.SpecificationGroupService;
import org.panda.systems.kakeipon.domain.service.spec.SpecificationService;
import org.panda.systems.kakeipon.domain.service.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

@Controller
@RequestMapping("")
public class SpecificationController {
  @Autowired
  SpecificationGroupService specificationGroupService;
  @Autowired
  SpecificationService specificationService;
  @Autowired
  KakeiPonUsersDetailsService userDetailsService;
  @Autowired
  AuthoritiesService authoritiesService;
  @Autowired
  UserExtService userExtService;
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
  KakeiPonUsersDetails kakeiPonUsersDetails;
  @Autowired
  private KakeiPonUsersDetailsService kakeiPonUsersDetailsService;

  public KakeiPonUsersDetails convertUserDetailsToKakeiPonUsersDetails(
      UserDetails userDetails) {

    // Lambda expression to get RoleName from authority
    Function<String, RoleName> getRoleName
        = (authority) -> {
      for (RoleName allRoles : RoleName.values()) {
        if (allRoles.name().equals(authority)) {
          return allRoles;
        }
      }
      return RoleName.USER;
    };

    KakeiPonUsersDetails kakeiPonUsersDetails
        = new KakeiPonUsersDetails();

    kakeiPonUsersDetails.setUsername(
        userDetails.getUsername());
    kakeiPonUsersDetails.setPassword(
        userDetails.getPassword());
    kakeiPonUsersDetails.setAuthorities(
        new Authorities());

    for (var authority : userDetails.getAuthorities()) {
      kakeiPonUsersDetails.getAuthorities().setAuthority(
          getRoleName.apply(authority.getAuthority())
      );
    }

    kakeiPonUsersDetails.setAccountNonExpired(
        userDetails.isAccountNonExpired());
    kakeiPonUsersDetails.setAccountNonLocked(
        userDetails.isAccountNonLocked());
    kakeiPonUsersDetails.setCredentialsNonExpired(
        userDetails.isCredentialsNonExpired());
    kakeiPonUsersDetails.setEnabled(
        userDetails.isEnabled());

    return kakeiPonUsersDetails;
  }

  @GetMapping("/spec")
  String index(
      @AuthenticationPrincipal UserDetails userDetails,
      @ModelAttribute UserExtForm userExtForm,
      Model model) {

    List<SpecificationGroupForm> groupForms
        = specificationGroupService.findAllToForm(false);

    for (SpecificationGroupForm groupForm : groupForms) {
      groupForm.setKakeiPonUsersDetails(
          convertUserDetailsToKakeiPonUsersDetails(
              userDetails));
      groupForm.setId(
          userDetailsService.convertUsernameToId(
              userDetails.getUsername()));
      UserExt userExt
          = userExtService.findById(groupForm.getId());
      userExtForm = userExtForm.setUserExtToForm(userExt);
      groupForm.setUserExtForm(userExtForm);

      AccountAndBalanceForm accountAndBalanceForm
          = new AccountAndBalanceForm(
          accountAndBalanceService,
          accountSourceService,
          accountDestinationService,
          groupForm.getAccountAndBalanceId(),
          groupForm.getAccountAndBalanceForm().getAccountSourceId(),
          groupForm.getAccountAndBalanceForm().getAccountDestinationId());
//      groupForm.setAccountAndBalanceForm(accountAndBalanceForm);

      groupForm.setAccountAndBalanceId(
          accountAndBalanceForm.getAccountAndBalanceId());
      groupForm.setAccountAndBalanceForm(
          accountAndBalanceForm);

      AccountSourceForm accountSourceForm = new AccountSourceForm(
          accountSourceService,
          groupForm.getAccountAndBalanceForm().getAccountSourceId());
      accountAndBalanceForm
          .setAccountSourceId(
              accountSourceForm.getAccountSourceId());
      accountAndBalanceForm.setAccountSourceForm(accountSourceForm);

      AccountDestinationForm accountDestinationForm
          = new AccountDestinationForm(
          accountDestinationService,
          groupForm.getAccountAndBalanceForm()
              .getAccountDestinationId());
      accountAndBalanceForm.setAccountDestinationId(
          accountDestinationForm.getAccountDestinationId());
      accountAndBalanceForm.setAccountDestinationForm(
          accountDestinationForm);

      ShopForm shopForm = new ShopForm(
          shopService,
          groupForm.getShopId());
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

  @GetMapping(value = "/spec/create/group/{id}")
  String createSpecificationGroup(
      @PathVariable("id") Integer id,
      @ModelAttribute SpecificationGroupForm groupForm,
      @ModelAttribute AccountAndBalanceForm accountAndBalanceForm,
      @AuthenticationPrincipal UserDetails userDetails,
      @ModelAttribute ShopForm shopForm,
      @ModelAttribute BalanceTypeForm balanceTypeForm,
      @ModelAttribute AccountSourceForm accountSourceForm,
      @ModelAttribute AccountDestinationForm accountDestinationForm,
      Model model) {

    User user = userDetailsService.findById(id);

    groupForm = new SpecificationGroupForm(
        specificationGroupService,
        specificationService,
        accountAndBalanceService,
        accountSourceService,
        accountDestinationService,
        userDetailsService,
        authoritiesService,
        userExtService,
        shopService,
        balanceTypeService,
        currencyListService,
        unitService,
        taxTypeService,
        taxRateService,
        id);

    SpecificationGroup group
        = specificationGroupService
        .findBySpecificationGroupIdAndUsernameAndDeleted(
            specificationGroupService.getMaxGroupId(),
            userDetails.getUsername(),
            false);

    groupForm = groupForm.setSpecificationGroupToForm(
        kakeiPonUsersDetailsService,
        shopService,
        balanceTypeService,
        accountAndBalanceService,
        accountSourceService,
        accountDestinationService,
        group);

    groupForm.setId(id);
    groupForm.setUsername(userDetails.getUsername());
    groupForm.setUsersToForm(
        kakeiPonUsersDetailsService,
        authoritiesService,
        user);
    groupForm.setKakeiPonUsersDetails(
        convertUserDetailsToKakeiPonUsersDetails(
            userDetails));

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
        .findBySpecificationGroupIdAndUsernameAndDeleted(
            specificationGroupService.getMaxGroupId(),
            userDetails.getUsername(),
            false);
    groupForm = groupForm.setSpecificationGroupToForm(
        kakeiPonUsersDetailsService,
        shopService,
        balanceTypeService,
        accountAndBalanceService,
        accountSourceService,
        accountDestinationService,
        specificationGroup);

    model.addAttribute("specificationGroupForm", groupForm);
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
    model.addAttribute("userDetails", userDetails);
    model.addAttribute("balanceTypes", balanceTypeService.findAll());
    model.addAttribute("selectedBalanceTypeId", groupForm.getBalanceTypeId());

    return "/spec/createGroup";
  }

  @PostMapping(value = "/spec/edit/group/{specificationGroupId}/{id}/{accountAndBalanceId}/{shopId}/{balanceTypeId}/{accountSourceId}/{accountDestinationId}")
  String editSpecificationGroup(
      @PathVariable("specificationGroupId") Long specificationGroupId,
      @PathVariable("id") Integer id,
      @PathVariable("accountAndBalanceId") Long accountAndBalanceId,
      @PathVariable("shopId") Long shopId,
      @PathVariable("balanceTypeId") Long balanceTypeId,
      @PathVariable("accountSourceId") Long accountSourceId,
      @PathVariable("accountDestinationId") Long accountDestinationId,
      @ModelAttribute SpecificationGroupForm groupForm,
      @ModelAttribute SpecificationForm detailForm,
      @ModelAttribute AccountAndBalanceForm accountAndBalanceForm,
      @AuthenticationPrincipal UserDetails userDetails,
      @ModelAttribute ShopForm shopForm,
      @ModelAttribute BalanceTypeForm balanceTypeForm,
      @ModelAttribute AccountSourceForm accountSourceForm,
      @ModelAttribute AccountDestinationForm accountDestinationForm,
      @ModelAttribute CurrencyListForm currencyListForm,
      @ModelAttribute UnitForm unitForm,
      @ModelAttribute TaxTypeForm taxTypeForm,
      @ModelAttribute TaxRateForm taxRateForm,
      Model model) {

    Double totalAmount = Double.parseDouble("0.0");

    SpecificationGroup group
        = specificationGroupService
        .findBySpecificationGroupIdAndUsernameAndDeleted(
            specificationGroupId,
            userDetails.getUsername(),
            false);
    groupForm
        = groupForm.setSpecificationGroupToForm(
        kakeiPonUsersDetailsService,
        shopService,
        balanceTypeService,
        accountAndBalanceService,
        accountSourceService,
        accountDestinationService,
        group);

    groupForm.setKakeiPonUsersDetails(
        convertUserDetailsToKakeiPonUsersDetails(
            userDetails));

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

    List<SpecificationForm> detailForms
        = specificationService.findBySpecificationGroupIdToForm(
        specificationGroupId,
        userDetails.getUsername(),
        false);

    for (var specForm : detailForms) {
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

    System.out.println("XXXXXXXX >>>> groupForm : " + groupForm.toString());
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
        "totalAmount", (long) Math.round(totalAmount));

    return "/spec/editGroup";
  }

  @PostMapping(value = "/spec/create/detail/{specificationGroupId}/{id}/{accountAndBalanceId}/{shopId}/{balanceTypeId}/{accountSourceId}/{accountDestinationId}")
  String createSpecification(
      @PathVariable("specificationGroupId") Long specificationGroupId,
      @PathVariable("id") Integer id,
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

    shopForm = new ShopForm(shopService, shopId);
    groupForm.setShopId(shopForm.getShopId());
    groupForm.setShopForm(shopForm);

    BalanceType balanceType
        = balanceTypeService.findById(
        balanceTypeId);
    groupForm.setBalanceTypeId(balanceType.getBalanceTypeId());
    groupForm.setBalanceTypeToForm(balanceType);

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

    groupForm.setAccountAndBalanceId(
        accountAndBalanceForm.getAccountAndBalanceId());
    groupForm.setAccountAndBalanceForm(
        accountAndBalanceForm);

    groupForm.setKakeiPonUsersDetails(
        convertUserDetailsToKakeiPonUsersDetails(
            userDetails));

    detailForm
        = new SpecificationForm(
        specificationService,
        specificationGroupId);
    detailForm.setDeleted(false);

    List<SpecificationForm> specificationForms
        = specificationService.findBySpecificationGroupIdToForm(
        specificationGroupId,
        userDetails.getUsername(),
        false);

    if (specificationForms.size() == 1) {
      currencyListForm = new CurrencyListForm(
          currencyListService,
          specificationForms.getFirst().getCurrencyId());
      specificationForms.getFirst().setCurrencyId(
          currencyListForm.getCurrencyId());
      specificationForms.getFirst().setCurrencyListForm(currencyListForm);

      unitForm = new UnitForm(
          unitService,
          specificationForms.getFirst().getUnitId());
      specificationForms.getFirst().setUnitId(unitForm.getUnitId());
      specificationForms.getFirst().setUnitForm(unitForm);

      taxTypeForm = new TaxTypeForm(
          taxTypeService,
          specificationForms.getFirst().getTaxTypeId());
      specificationForms.getFirst().setTaxTypeId(taxTypeForm.getTaxTypeId());
      specificationForms.getFirst().setTaxTypeForm(taxTypeForm);

      taxRateForm = new TaxRateForm(
          taxRateService,
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

  @PostMapping(value = "/spec/edit/detail/{specificationGroupId}/{specificationId}/{id}/{accountAndBalanceId}/{shopId}/{balanceTypeId}/{accountSourceId}/{accountDestinationId}")
  String editSpecification(
      @PathVariable("specificationGroupId") Long specificationGroupId,
      @PathVariable("specificationId") Long specificationId,
      @PathVariable("id") Integer id,
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
      @AuthenticationPrincipal UserDetails userDetails,
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
        = specificationService.findBySpecificationGroupIdAndSpecificationIdAndIdAndDeleted(
        specificationGroupId,
        specificationId,
        id,
        false);

    detailForm = detailForm.setSpecificationToForm(
        specification);

    groupForm.setKakeiPonUsersDetails(
        convertUserDetailsToKakeiPonUsersDetails(
            userDetails));

    shopForm = new ShopForm(shopService, shopId);
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

    Double totalAmount = Double.parseDouble("0.0");

    Long balanceTypeId = groupForm.getBalanceTypeId();
    String groupMemo = groupForm.getGroupMemo();

    SpecificationGroup group
        = specificationGroupService.findBySpecificationGroupIdAndUsernameAndDeleted(
        specificationGroupService.getMaxGroupId(),
        userDetails.getUsername(),
        false);
    groupForm = groupForm.setSpecificationGroupToForm(
        kakeiPonUsersDetailsService,
        shopService,
        balanceTypeService,
        accountAndBalanceService,
        accountSourceService,
        accountDestinationService,
        group);

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

    groupForm.setKakeiPonUsersDetails(
        convertUserDetailsToKakeiPonUsersDetails(
            userDetails));

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
        userDetails.getUsername(),
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
            accountSourceService,
            groupForm.getAccountAndBalanceForm().getAccountSourceId()));
    model.addAttribute(
        "accountDestinationForm",
        new AccountDestinationForm(
            accountDestinationService,
            groupForm.getAccountAndBalanceForm().getAccountDestinationId()));
    model.addAttribute("userDetails", userDetails);
    model.addAttribute("balanceTypes", balanceTypeService.findAll());
    model.addAttribute("totalAmount", (long) Math.round(totalAmount));

    return "/spec/editGroup";
  }

  @PostMapping(value = "/spec/update/group/{specificationGroupId}/{id}")
  String updateGroup(
      @PathVariable("specificationGroupId") Long specificationGroupId,
      @PathVariable("id") Integer id,
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

    Double totalAmount = Double.parseDouble("0.0");

    Long balanceTypeId = groupForm.getBalanceTypeId();
    String groupMemo = groupForm.getGroupMemo();

    SpecificationGroup group
        = specificationGroupService
        .findBySpecificationGroupIdAndUsernameAndDeleted(
            specificationGroupId,
            userDetails.getUsername(),
            false);
    groupForm = groupForm.setSpecificationGroupToForm(
        kakeiPonUsersDetailsService,
        shopService,
        balanceTypeService,
        accountAndBalanceService,
        accountSourceService,
        accountDestinationService,
        group);

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

    groupForm.setKakeiPonUsersDetails(
        convertUserDetailsToKakeiPonUsersDetails(
            userDetails));

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
        userDetails.getUsername(),
        false);

    for (var specForm : detailForms) {
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
            accountSourceService,
            groupForm.getAccountAndBalanceForm().getAccountSourceId()));
    model.addAttribute(
        "accountDestinationForm",
        new AccountDestinationForm(
            accountDestinationService,
            groupForm.getAccountAndBalanceForm().getAccountDestinationId()));
    model.addAttribute("userDetails", userDetails);
    model.addAttribute("balanceTypes", balanceTypeService.findAll());
    model.addAttribute("selectedBalanceTypeId", groupForm.getBalanceTypeId());
    model.addAttribute("totalAmount", (long) Math.round(totalAmount));

    return "/spec/editGroup";
  }

  @PostMapping(value = "/spec/save/create/detail/{specificationGroupId}/{id}/{accountAndBalanceId}/{shopId}/{balanceTypeId}/{accountSourceId}/{accountDestinationId}")
  String saveCreateDetail(
      @PathVariable("specificationGroupId") Long specificationGroupId,
      @PathVariable("id") Integer id,
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

    Double totalAmount = Double.parseDouble("0.0");

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
        detailForm.getCurrencyId());
    detailForm.setCurrencyId(
        currencyListForm.getCurrencyId());

    unitForm = new UnitForm(
        unitService,
        detailForm.getUnitId());
    detailForm.setUnitId(unitForm.getUnitId());

    taxTypeForm = new TaxTypeForm(
        taxTypeService,
        detailForm.getTaxTypeId());
    detailForm.setTaxTypeId(taxTypeForm.getTaxTypeId());

    taxRateForm = new TaxRateForm(
        taxRateService,
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
        userDetails.getUsername(),
        false);

    for (var specForm : detailForms) {
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
        .findBySpecificationGroupIdAndUsernameAndDeleted(
            specificationGroupId,
            userDetails.getUsername(),
            false);

    System.out.println("XXXXXXXX >>>> group : " + group.toString());
    groupForm = groupForm.setSpecificationGroupToForm(
        kakeiPonUsersDetailsService,
        shopService,
        balanceTypeService,
        accountAndBalanceService,
        accountSourceService,
        accountDestinationService,
        group);

    balanceType
        = balanceTypeService.findById(
        previousBalanceTypeId);
    groupForm.setBalanceTypeId(balanceType.getBalanceTypeId());
    groupForm.setBalanceTypeToForm(balanceType);

    groupForm.setGroupMemo(group.getGroupMemo());

    System.out.println("XXXXXXXX >>>> groupForm : " + groupForm.toString());
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
    model.addAttribute("totalAmount", (long) Math.round(totalAmount));

    return "/spec/editGroup";
  }

  @PostMapping(value = "/spec/save/edit/detail/{specificationGroupId}/{specificationId}/{id}/{accountAndBalanceId}/{shopId}/{balanceTypeId}/{accountSourceId}/{accountDestinationId}")
  String saveEditDetail(
      @PathVariable("specificationGroupId") Long specificationGroupId,
      @PathVariable("specificationId") Long specificationId,
      @PathVariable("id") Integer id,
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

    Double totalAmount = Double.parseDouble("0.0");

    Long previousBalanceTypeId = groupForm.getBalanceTypeId();

    groupForm.setAccountAndBalanceId(
        accountAndBalanceForm.getAccountAndBalanceId());
    groupForm.setAccountAndBalanceForm(
        accountAndBalanceForm);

    Shop shop = shopService.findById(shopId);
    groupForm.setShopId(shop.getShopId());
    ShopForm shopForm = new ShopForm();
    shopForm = shopForm.setShopToForm(shop);
    groupForm.setShopForm(shopForm);

    BalanceType balanceType
        = balanceTypeService.findById(
        balanceTypeId);
    groupForm.setBalanceTypeId(balanceType.getBalanceTypeId());
    groupForm.setBalanceTypeToForm(balanceType);

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
    groupForm.setAccountAndBalanceForm(
        accountAndBalanceForm);

    currencyListForm = new CurrencyListForm(
        currencyListService,
        detailForm.getCurrencyId());
    detailForm.setCurrencyId(
        currencyListForm.getCurrencyId());

    unitForm = new UnitForm(
        unitService,
        detailForm.getUnitId());
    detailForm.setUnitId(unitForm.getUnitId());

    taxTypeForm = new TaxTypeForm(
        taxTypeService,
        detailForm.getTaxTypeId());
    detailForm.setTaxTypeId(taxTypeForm.getTaxTypeId());

    taxRateForm = new TaxRateForm(
        taxRateService,
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
        userDetails.getUsername(),
        false);

    for (var specForm : detailForms) {
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
    model.addAttribute("totalAmount", (long) Math.round(totalAmount));

    return "/spec/editGroup";
  }

  @GetMapping("/{specificationGroupId}/{id}/{accountAndBalanceId}/{shopId}/{balanceTypeId}/{accountSourceId}/{accountDestinationId}/searchShop")
  String searchShop(
      @PathVariable("specificationGroupId") Long specificationGroupId,
      @PathVariable("id") Integer id,
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
        .findBySpecificationGroupIdAndUsernameAndDeleted(
            specificationGroupId,
            userDetails.getUsername(),
            false);
    String groupMemo = group.getGroupMemo();

    groupForm.setId(id);

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

  @GetMapping("/{specificationGroupId}/{id}/{accountAndBalanceId}/{shopId}/{balanceTypeId}/{accountSourceId}/{accountDestinationId}/searchAccountSource")
  String searchAccountSource(
      @PathVariable("specificationGroupId") Long specificationGroupId,
      @PathVariable("id") Integer id,
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
        .findBySpecificationGroupIdAndUsernameAndDeleted(
            specificationGroupId,
            userDetails.getUsername(),
            false);
    String groupMemo = group.getGroupMemo();

    groupForm.setId(id);

    Shop shop = shopService.findById(shopId);
    groupForm.setShopId(shop.getShopId());
    ShopForm shopForm = new ShopForm();
    shopForm = shopForm.setShopToForm(shop);
    groupForm.setShopForm(shopForm);

    AccountAndBalance accountAndBalance
        = accountAndBalanceService.getById(accountAndBalanceId);

    List<AccountSource> accountSources = accountSourceService.findAll();
    AccountDestinationForm accountDestinationForm
        = new AccountDestinationForm(
        accountDestinationService,
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

  @GetMapping("/{specificationGroupId}/{id}/{accountAndBalanceId}/{shopId}/{balanceTypeId}/{accountSourceId}/{accountDestinationId}/searchAccountDestination")
  String searchAccountDestination(
      @PathVariable("specificationGroupId") Long specificationGroupId,
      @PathVariable("id") Integer id,
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
        .findBySpecificationGroupIdAndUsernameAndDeleted(
            specificationGroupId,
            userDetails.getUsername(),
            false);
    String groupMemo = group.getGroupMemo();

    groupForm.setId(id);

    AccountAndBalance accountAndBalance
        = accountAndBalanceService.getById(accountAndBalanceId);

    Shop shop = shopService.findById(shopId);
    groupForm.setShopId(shop.getShopId());
    ShopForm shopForm = new ShopForm();
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
        accountSourceService,
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

  @RequestMapping(value = "/{specificationGroupId}/{id}/{accountAndBalanceId}/{shopId}/{balanceTypeId}/{accountSourceId}/{accountDestinationId}/setShop",
      method = RequestMethod.GET)
  String setShopToSpecificationGroup(
      @PathVariable("specificationGroupId") Long specificationGroupId,
      @PathVariable("id") Integer id,
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

    Double totalAmount = Double.parseDouble("0.0");

    SpecificationGroup group
        = specificationGroupService
        .findBySpecificationGroupIdAndUsernameAndDeleted(
            specificationGroupId,
            userDetails.getUsername(),
            false);
    String groupMemo = group.getGroupMemo();

    groupForm.setKakeiPonUsersDetails(
        convertUserDetailsToKakeiPonUsersDetails(
            userDetails));

    Shop shop = shopService.findById(shopId);
    groupForm.setShopId(shop.getShopId());
    ShopForm shopForm = new ShopForm();
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

    List<SpecificationForm> detailForms
        = specificationService.findBySpecificationGroupIdToForm(
        specificationGroupId,
        userDetails.getUsername(),
        false);

    for (var specForm : detailForms) {
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
        "totalAmount", (long) Math.round(totalAmount));

    if (specificationGroupId == null) {
      return "/spec/createGroup";
    } else {
      return "/spec/editGroup";
    }
  }

  @RequestMapping(
      value = "/{specificationGroupId}/{id}/{accountAndBalanceId}/{shopId}/{balanceTypeId}/{accountSourceId}/{accountDestinationId}/setAccountSource",
      method = RequestMethod.GET)
  String setAccountSourceToAccountAndBalance(
      @PathVariable("specificationGroupId") Long specificationGroupId,
      @PathVariable("id") Integer id,
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

    Double totalAmount = Double.parseDouble("0.0");

    SpecificationGroup group
        = specificationGroupService
        .findBySpecificationGroupIdAndUsernameAndDeleted(
            specificationGroupId,
            userDetails.getUsername(), false);
    String groupMemo = group.getGroupMemo();

    groupForm.setKakeiPonUsersDetails(
        convertUserDetailsToKakeiPonUsersDetails(
            userDetails));

    Shop shop = shopService.findById(shopId);
    groupForm.setShopId(shop.getShopId());
    ShopForm shopForm = new ShopForm();
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

    List<SpecificationForm> detailForms
        = specificationService.findBySpecificationGroupIdToForm(
        specificationGroupId,
        userDetails.getUsername(),
        false);

    for (var specForm : detailForms) {
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
        "totalAmount", (long) Math.round(totalAmount));

    if (specificationGroupId == null) {
      return "/spec/createGroup";
    } else {
      return "/spec/editGroup";
    }
  }

  @RequestMapping(
      value = "/{specificationGroupId}/{id}/{accountAndBalanceId}/{shopId}/{balanceTypeId}/{accountSourceId}/{accountDestinationId}/setAccountDestination",
      method = RequestMethod.GET)
  String setAccountDestinationToAccountAndBalance(
      @PathVariable("specificationGroupId") Long specificationGroupId,
      @PathVariable("id") Integer id,
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

    Double totalAmount = Double.parseDouble("0.0");

    SpecificationGroup group
        = specificationGroupService
        .findBySpecificationGroupIdAndUsernameAndDeleted(
            specificationGroupId,
            userDetails.getUsername(),
            false);
    String groupMemo = group.getGroupMemo();

    groupForm.setKakeiPonUsersDetails(
        convertUserDetailsToKakeiPonUsersDetails(
            userDetails));

    Shop shop = shopService.findById(shopId);
    groupForm.setShopId(shop.getShopId());
    ShopForm shopForm = new ShopForm();
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
        accountSourceService,
        accountSourceId);
    groupForm.getAccountAndBalanceForm()
        .setAccountSourceForm(
            accountSourceForm);

    groupForm.getAccountAndBalanceForm()
        .setAccountDestinationId(
            accountDestinationId);
    AccountDestinationForm accountDestinationForm
        = new AccountDestinationForm(
        accountDestinationService,
        accountDestinationId);
    groupForm
        .getAccountAndBalanceForm()
        .setAccountDestinationForm(
            accountDestinationForm);

    groupForm.setGroupMemo(groupMemo);

    List<SpecificationForm> specificationForms
        = specificationService.findBySpecificationGroupIdToForm(
        specificationGroupId,
        userDetails.getUsername(),
        false);

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
        "totalAmount", (long) Math.round(totalAmount));

    if (specificationGroupId == null) {
      return "/spec/createGroup";
    } else {
      return "/spec/editGroup";
    }
  }

  @PostMapping(value = "/spec/delete/group/{specificationGroupId}/{id}")
  String deleteGroup(
      @PathVariable("specificationGroupId") Long specificationGroupId,
      @PathVariable("id") Integer id,
      @AuthenticationPrincipal UserDetails userDetails,
      Model model) {

    List<Specification> specifications
        = specificationService.findBySpecificationGroupIdAndUsernameAndDeleted(
        specificationGroupId,
        userDetails.getUsername(),
        false);
    for (var spec : specifications) {
      spec.setDeleted(true);
    }
    specificationService.saveAllSpecifications(specifications);

    SpecificationGroupForm specificationGroupForm
        = new SpecificationGroupForm(
        specificationGroupService,
        specificationGroupId,
        userDetails.getUsername(),
        false);

    specificationGroupForm.setDeleted(true);

    specificationGroupService
        .saveAndFlushSpecificationGroup(
            specificationGroupForm.toEntity());

    return "redirect:/spec";
  }

  @PostMapping(value = "/spec/delete/detail/{specificationGroupId}/{specificationId}/{id}/{accountAndBalanceId}/{shopId}/{balanceTypeId}/{accountSourceId}/{accountDestinationId}")
  String deleteDetail(
      @PathVariable("specificationGroupId") Long specificationGroupId,
      @PathVariable("specificationId") Long specificationId,
      @PathVariable("id") Integer id,
      @PathVariable("accountAndBalanceId") Long accountAndBalanceId,
      @PathVariable("shopId") Long shopId,
      @PathVariable("balanceTypeId") Long balanceTypeId,
      @PathVariable("accountSourceId") Long accountSourceId,
      @PathVariable("accountDestinationId") Long accountDestinationId,
      @ModelAttribute SpecificationGroupForm groupForm,
      @ModelAttribute SpecificationForm detailForm,
      @ModelAttribute AccountAndBalanceForm accountAndBalanceForm,
      @AuthenticationPrincipal UserDetails userDetails,
      @ModelAttribute UserExtForm userExtForm,
      @ModelAttribute ShopForm shopForm,
      @ModelAttribute BalanceTypeForm balanceTypeForm,
      @ModelAttribute AccountSourceForm accountSourceForm,
      @ModelAttribute AccountDestinationForm accountDestinationForm,
      Model model) {

    Double totalAmount = Double.parseDouble("0.0");

    Long previousBalanceTypeId = groupForm.getBalanceTypeId();

    SpecificationGroup specificationGroup
        = specificationGroupService
        .findBySpecificationGroupIdAndUsernameAndDeleted(
            specificationGroupId,
            userDetails.getUsername(),
            false);

    Authorities authorities
        = authoritiesService.findByUsername(
        userDetails.getUsername());
    groupForm.setKakeiPonUsersDetails(
        convertUserDetailsToKakeiPonUsersDetails(
            userDetails));

    userExtForm = userExtService.findByIdToForm(id);
    groupForm.setUserExtForm(userExtForm);

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
            id,
            false);
    detailForm = detailForm.setSpecificationToForm(
        specification);
    detailForm.setDeleted(true);
    specificationService.saveAndFlushSpecification(detailForm.toEntity());

    List<SpecificationForm> detailForms
        = specificationService.findBySpecificationGroupIdToForm(
        specificationGroupId,
        userDetails.getUsername(),
        false);

    for (var specForm : detailForms) {
      CurrencyListForm currencyListForm = new CurrencyListForm(
          currencyListService,
          specForm.getCurrencyId());
      specForm.setCurrencyId(currencyListForm.getCurrencyId());
      specForm.setCurrencyListForm(currencyListForm);

      UnitForm unitForm = new UnitForm(
          unitService,
          specForm.getUnitId());
      specForm.setUnitId(unitForm.getUnitId());
      specForm.setUnitForm(unitForm);

      TaxTypeForm taxTypeForm = new TaxTypeForm(
          taxTypeService,
          specForm.getTaxTypeId());
      specForm.setTaxTypeId(taxTypeForm.getTaxTypeId());
      specForm.setTaxTypeForm(taxTypeForm);

      TaxRateForm taxRateForm = new TaxRateForm(
          taxRateService,
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
        "totalAmount", (long) Math.round(totalAmount));

    return "/spec/editGroup";
  }
}
