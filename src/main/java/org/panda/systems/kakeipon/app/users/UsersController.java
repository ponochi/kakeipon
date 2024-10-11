package org.panda.systems.kakeipon.app.users;

import org.panda.systems.kakeipon.domain.model.users.RoleName;
import org.panda.systems.kakeipon.domain.model.users.Users;
import org.panda.systems.kakeipon.domain.model.users.UsersExt;
import org.panda.systems.kakeipon.domain.service.users.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {
  @Autowired
  private UsersDetailService usersDetailService;
  @Autowired
  private UsersExtService usersExtService;
//  @Autowired
//  private AuthoritiesService authoritiesService;
  @Autowired
  private PasswordEncoder passwordEncoder;

  // Default constructor
  public UsersController() {

  }

  private void setUsers(UsersForm form, Users users) {
    users.setId(form.getId());
    users.setUserId(form.getUserId());
    users.setPassword(passwordEncoder.encode(form.getPassword()));
    users.setEnabled(true);
//    users.setAccountNonExpired(true);
//    users.setAccountNonLocked(true);
//    users.setCredentialsNonExpired(true);
  }

  private void setUsersExt(UsersExtForm form, UsersExt usersExt) {
    usersExt.setUserId(form.getUserId());
    usersExt.setLastName(form.getLastName());
    usersExt.setFirstName(form.getFirstName());
    usersExt.setEmail(form.getEmail());
    usersExt.setBirthDay(form.getBirthDay());
    usersExt.setPhoneNumber(form.getPhoneNumber());
    if (form.getEntryDate() == null) {
      usersExt.setEntryDate(LocalDateTime.now());
      usersExt.setUpdateDate(usersExt.getUpdateDate());
    } else {
      usersExt.setEntryDate(form.getEntryDate());
      usersExt.setUpdateDate(LocalDateTime.now());
    }
  }

//  private void setAuthorities(AuthoritiesForm form, Authorities authorities) {
//    authorities.setId(form.getId());
//    authorities.setUsername(form.getUsername());
//    authorities.setAuthority(form.getAuthority());
//  }

//  @ModelAttribute(name = "userForm")
  @ModelAttribute(name = "usersDetails")
  UsersForm setUpUserForm() {
    return new UsersForm(
        usersDetailService);
//        authoritiesService);
  }

  @RequestMapping(value = "", method = RequestMethod.GET)
  String list(Model model) {
    List<UsersDetail> usersDetails = usersDetailService.findAllUsersToForm();
    for (UsersDetail usersDetail : usersDetails) {
      Users users = usersDetailService.findById(usersDetail.getUsername()).getUsers();
//      usersDetail.setUserToForm(
//          usersDetailsService,
//          authoritiesService,
//          users);
//      AuthoritiesForm authoritiesForm = new AuthoritiesForm(
//          authoritiesService)
//          .setAuthorityById(users.getId());
//      users.setAuthoritiesForm(authoritiesForm);
    }
    model.addAttribute("usersDetails", usersDetails);
    return "/user/showList";
  }

  @RequestMapping(value = "/{userId}/show", method = RequestMethod.GET)
  String show(@PathVariable Long userId, Model model) {
    Users users = usersDetailService.findByUserId(userId).getUsers();
    UsersForm usersForm = new UsersForm(
        usersDetailService);
//        authoritiesService);
    usersForm.setUserId(users.getUserId());
    usersForm.setId(users.getId());
    usersForm.setPassword(users.getPassword());
    usersForm.setEnabled(users.getEnabled());
    usersForm.setAccountNonExpired(true);
    usersForm.setAccountNonLocked(true);
    usersForm.setCredentialsNonExpired(true);

//    Authorities authorities
//        = authoritiesService.findById(usersForm.getId());
//    AuthoritiesForm authoritiesForm = new AuthoritiesForm();
//    authoritiesForm.setId(authorities.getId());
//    authoritiesForm.setUsername(authorities.getUsername());
//    authoritiesForm.setAuthority(authorities.getAuthority());

//    usersForm.setAuthoritiesForm(authoritiesForm);

    UsersExt usersExt = usersExtService.findByUserId(userId);
    UsersExtForm usersExtForm = new UsersExtForm(usersExtService);
    usersExtForm.setUserId(usersExt.getUserId());
    usersExtForm.setLastName(usersExt.getLastName());
    usersExtForm.setFirstName(usersExt.getFirstName());
    usersExtForm.setEmail(usersExt.getEmail());
    usersExtForm.setBirthDay(usersExt.getBirthDay());
    usersExtForm.setPhoneNumber(usersExt.getPhoneNumber());
    usersExtForm.setEntryDate(usersExt.getEntryDate());
    usersExtForm.setUpdateDate(usersExt.getUpdateDate());

    usersForm.setUsersExtForm(usersExtForm);

    model.addAttribute("usersForm", usersForm);
    return "/user/showDetail";
  }

  @GetMapping("/create")
  String createForm(
      @ModelAttribute UsersForm usersForm,
      @ModelAttribute UsersExtForm usersExtForm,
//      @ModelAttribute AuthoritiesForm authoritiesForm,
      Model model) {

//    List<RoleName> authorityList = RoleName.getRoleNameList();

    usersForm.setUsersExtForm(usersExtForm);
//    usersForm.setAuthoritiesForm(authoritiesForm);

    model.addAttribute("usersForm", usersForm);
//    model.addAttribute("authorityList", authorityList);
    return "/user/createDetail";
  }

  @RequestMapping(value = "/{userId}/edit", method = RequestMethod.POST)
  String editForm(@PathVariable("userId") Long userId,
                  Model model) {

    UsersDetail usersDetail = usersDetailService.findByUserId(userId);
    UsersExtForm usersExtForm = usersExtService.findByUserIdToForm(userId);

//    usersDetails.setUsersExtForm(usersExtForm);
//    AuthoritiesForm authoritiesForm = new AuthoritiesForm(
//        authoritiesService)
//        .setAuthorityById(usersDetails.getUsers().getId());
//    usersDetails.setAuthoritiesForm(authoritiesForm);

//    List<RoleName> authorityList = RoleName.getRoleNameList();

    model.addAttribute("usersDetails", usersDetail);
//    model.addAttribute("authorityList", authorityList);

    return "/user/editDetail";
  }

  @PostMapping("/createConfirm")
  String createConfirm(
      @Validated @ModelAttribute UsersForm usersForm,
      @Validated @ModelAttribute UsersExtForm usersExtForm,
//      @Validated @ModelAttribute AuthoritiesForm authoritiesForm,
      @Validated @RequestParam("authoritiesForm.authority")
      RoleName authority,
      BindingResult result,
      Model model) {

    if (usersDetailService.existsById(usersForm.getId())) {
      result.rejectValue("username", "error.username", "このユーザ名は既に登録されています");
      return createForm(
//          usersForm, usersExtForm, authoritiesForm, model);
          usersForm, usersExtForm, model);
    }

    if (result.hasErrors()) {
      return createForm(
//          usersForm, usersExtForm, authoritiesForm, model);
          usersForm, usersExtForm, model);
    }

    Users users = new Users();
    UsersDetail usersDetail = new UsersDetail(users);
    setUsers(usersForm, users);
    usersDetail.getUsers().setId(usersDetail.getUsers().getId());
    Users resultUsers
        = usersDetailService.saveAndFlush(users);

    UsersExt usersExt = new UsersExt();
    setUsersExt(usersExtForm, usersExt);
    usersExt.setUserId(resultUsers.getUserId());
    UsersExt resultUsersExt
        = usersExtService.saveUserExt(usersExt);

//    Authorities authorities = new Authorities();
//    authoritiesForm.setAuthority(authority);
//    setAuthorities(authoritiesForm, authorities);
//    authorities.setId(authoritiesForm.getId());
//    Authorities resultAuthorities
//        = authoritiesService.saveAuthorities(authorities);

    return "redirect:/users";
  }

  @RequestMapping(value = "{userId}/confirm", method = RequestMethod.POST)
  String confirm(
      @Validated @ModelAttribute UsersForm usersForm,
      @Validated @ModelAttribute UsersExtForm usersExtForm,
//      @Validated @ModelAttribute AuthoritiesForm authoritiesForm,
      @Validated @RequestParam("authority") RoleName authority,
      BindingResult bindingResult,
      @PathVariable("userId") Long userId,
      Model model) {
    if (bindingResult.hasErrors()) {
      return editForm(userId, model);
    }
    Users users = usersDetailService.findByUserId(userId).getUsers();
    String username = users.getId();
    setUsers(usersForm, users);
    users = usersDetailService.saveAndFlush(users);

    UsersExt usersExt = usersExtService.findByUserId(userId);
    setUsersExt(usersExtForm, usersExt);
    usersExtService.saveUserExt(usersExt);

//    Authorities authorities
//        = authoritiesService.findByUsername(username);
//    authoritiesForm.setAuthority(authority);
//    setAuthorities(authoritiesForm, authorities);
//    authoritiesService.saveAuthorities(authorities);


    return "redirect:/users";
  }
}
