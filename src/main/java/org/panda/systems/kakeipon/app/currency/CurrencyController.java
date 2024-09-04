package org.panda.systems.kakeipon.app.currency;

import org.panda.systems.kakeipon.app.user.UserForm;
import org.panda.systems.kakeipon.domain.model.currency.Currency;
import org.panda.systems.kakeipon.domain.model.user.Role;
import org.panda.systems.kakeipon.domain.model.user.User;
import org.panda.systems.kakeipon.domain.service.currency.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("currency")
public class CurrencyController {
  @Autowired
  CurrencyService currencyService;

  @GetMapping("")
  String list(Model model) {
    List<Currency> currencies = currencyService.findAll();

    model.addAttribute("currencies", currencies);

    return "/currency/showList";
  }

  @GetMapping("add")
  String add(CurrencyForm form, Model model) {
    List<Currency> currencies = currencyService.findAll();
    CurrencyForm currencyForm = new CurrencyForm();

    model.addAttribute("form", form);
    model.addAttribute("currencies", currencies);

    return "/currency/add";
  }

  @PostMapping("list")
  String confirm(CurrencyForm form, Model model) {
    Currency currency = new Currency();
    currency.setCurrencyName(form.getCurrencyName());

    currency = currencyService.saveAndFlush(currency);

    List<Currency> currencies = currencyService.findAll();

    model.addAttribute("form", form);
    model.addAttribute("currencies", currencies);

    return "/currency/showList";
  }

  @RequestMapping(value = "{currencyId}/edit", method = RequestMethod.GET)
  String edit(
      @PathVariable("currencyId") Long currencyId,
      @ModelAttribute CurrencyForm form, Model model) {

    Currency currency = currencyService.findById(currencyId);
    form.setCurrencyId(currency.getCurrencyId());
    form.setCurrencyName(currency.getCurrencyName());

    model.addAttribute("currency", currency);

    return "/currency/editDetail";
  }

  @RequestMapping(value = "{currencyId}/edit", method = RequestMethod.POST)
  String editForm(@PathVariable("currencyId") Long currencyId,
                  Model model) {
    Currency currency = currencyService.findById(currencyId);

    model.addAttribute("currency", currency);

    return "/currency/editDetail";
  }

  @RequestMapping(value = "{currencyId}/confirm", method = RequestMethod.POST)
  String confirm(@Validated CurrencyForm form, BindingResult bindingResult,
                 @PathVariable("currencyId") Long currencyId,
                 Model model) {
    if (bindingResult.hasErrors()) {
      return editForm(currencyId, model);
    }
    Currency currency = new Currency();
    currency.setCurrencyId(form.getCurrencyId());
    currency.setCurrencyName(form.getCurrencyName());
    currencyService.saveAndFlush(currency);

    List<Currency> currencies = currencyService.findAll();

    model.addAttribute("currencies", currencies);

    return "/currency/showList";
  }
}
