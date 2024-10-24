package org.panda.systems.kakeipon.app.currency;

import org.panda.systems.kakeipon.domain.model.currency.CurrencyList;
import org.panda.systems.kakeipon.domain.service.currency.CurrencyListService;
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
  CurrencyListService currencyService;

  @GetMapping("")
  String list(Model model) {
    List<CurrencyList> currencies = currencyService.findAll();

    model.addAttribute("currencies", currencies);

    return "/currency/showList";
  }

  @GetMapping("add")
  String add(CurrencyListForm form, Model model) {
    List<CurrencyList> currencies = currencyService.findAll();
    CurrencyListForm currencyListForm = new CurrencyListForm();

    model.addAttribute("currencyListForm", currencyListForm);
    model.addAttribute("currencies", currencies);

    return "/currency/add";
  }

  @PostMapping("list")
  String confirm(CurrencyListForm form, Model model) {
    CurrencyList currency = new CurrencyList();
    currency.setCurrencyName(form.getCurrencyName());

    currency = currencyService.saveAndFlush(currency);
    CurrencyListForm currencyListForm = new CurrencyListForm();
    currencyListForm.setCurrencyId(currency.getCurrencyId());
    currencyListForm.setCurrencyName(currency.getCurrencyName());
    List<CurrencyList> currencies = currencyService.findAll();

    model.addAttribute("currencyListForm", currencyListForm);
    model.addAttribute("currencies", currencies);

    return "/currency/showList";
  }

  @RequestMapping(value = "{currencyId}/edit", method = RequestMethod.GET)
  String edit(
      @PathVariable("currencyId") Long currencyId,
      @ModelAttribute CurrencyListForm form, Model model) {

    CurrencyList currncyList = currencyService.findById(currencyId);
    form.setCurrencyId(currncyList.getCurrencyId());
    form.setCurrencyName(currncyList.getCurrencyName());

    model.addAttribute("currncyList", currncyList);

    return "/currency/editDetail";
  }

  @RequestMapping(value = "{currencyId}/edit", method = RequestMethod.POST)
  String editForm(@PathVariable("currencyId") Long currencyId,
                  Model model) {
    CurrencyList currency = currencyService.findById(currencyId);

    model.addAttribute("currency", currency);

    return "/currency/editDetail";
  }

  @RequestMapping(value = "{currencyId}/confirm", method = RequestMethod.POST)
  String confirm(@Validated CurrencyListForm form, BindingResult bindingResult,
                 @PathVariable("currencyId") Long currencyId,
                 Model model) {
    if (bindingResult.hasErrors()) {
      return editForm(currencyId, model);
    }
    CurrencyList currency = new CurrencyList();
    currency.setCurrencyId(form.getCurrencyId());
    currency.setCurrencyName(form.getCurrencyName());
    currencyService.saveAndFlush(currency);

    List<CurrencyList> currencies = currencyService.findAll();

    model.addAttribute("currencies", currencies);

    return "/currency/showList";
  }
}
