package org.panda.systems.kakeipon.app.user;

import org.panda.systems.kakeipon.domain.model.user.RoleName;
import org.panda.systems.kakeipon.domain.model.user.User;
import org.panda.systems.kakeipon.domain.model.user.UserExt;
import org.panda.systems.kakeipon.domain.service.user.*;
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
  private UsersDetailsService usersDetailsService;
  @Autowired
  private UserExtService userExtService;
//  @Autowired
//  private AuthoritiesService authoritiesService;
  @Autowired
  private PasswordEncoder passwordEncoder;

  // Default constructor
  public UsersController() {

  }

  private void setUsers(UserForm form, User user) {
    user.setId(form.getId());
    user.setUserId(form.getUserId());
    user.setPassword(passwordEncoder.encode(form.getPassword()));
    user.setEnabled(true);
//    user.setAccountNonExpired(true);
//    user.setAccountNonLocked(true);
//    user.setCredentialsNonExpired(true);
  }

  private void setUsersExt(UserExtForm form, UserExt userExt) {
    userExt.setUserId(form.getUserId());
    userExt.setLastName(form.getLastName());
    userExt.setFirstName(form.getFirstName());
    userExt.setEmail(form.getEmail());
    userExt.setBirthDay(form.getBirthDay());
    userExt.setPhoneNumber(form.getPhoneNumber());
    if (form.getEntryDate() == null) {
      userExt.setEntryDate(LocalDateTime.now());
      userExt.setUpdateDate(userExt.getUpdateDate());
    } else {
      userExt.setEntryDate(form.getEntryDate());
      userExt.setUpdateDate(LocalDateTime.now());
    }
  }

//  private void setAuthorities(AuthoritiesForm form, Authorities authorities) {
//    authorities.setId(form.getId());
//    authorities.setUsername(form.getUsername());
//    authorities.setAuthority(form.getAuthority());
//  }

//  @ModelAttribute(name = "userForm")
  @ModelAttribute(name = "usersDetails")
  UserForm setUpUserForm() {
    return new UserForm(
        usersDetailsService);
//        authoritiesService);
  }

  @RequestMapping(value = "", method = RequestMethod.GET)
  String list(Model model) {
    List<UsersDetails> usersDetails = usersDetailsService.findAllUsersToForm();
    for (UsersDetails usersDetail : usersDetails) {
      User user = usersDetailsService.findById(usersDetail.getUsername()).getUser();
//      usersDetail.setUserToForm(
//          usersDetailsService,
//          authoritiesService,
//          user);
//      AuthoritiesForm authoritiesForm = new AuthoritiesForm(
//          authoritiesService)
//          .setAuthorityById(user.getId());
//      user.setAuthoritiesForm(authoritiesForm);
    }
    model.addAttribute("usersDetails", usersDetails);
    return "/user/showList";
  }

  @RequestMapping(value = "/{userId}/show", method = RequestMethod.GET)
  String show(@PathVariable Long userId, Model model) {
    User user = usersDetailsService.findByUserId(userId).getUser();
    UserForm userForm = new UserForm(
        usersDetailsService);
//        authoritiesService);
    userForm.setUserId(user.getUserId());
    userForm.setId(user.getId());
    userForm.setPassword(user.getPassword());
    userForm.setEnabled(user.getEnabled());
    userForm.setAccountNonExpired(true);
    userForm.setAccountNonLocked(true);
    userForm.setCredentialsNonExpired(true);

//    Authorities authorities
//        = authoritiesService.findById(userForm.getId());
//    AuthoritiesForm authoritiesForm = new AuthoritiesForm();
//    authoritiesForm.setId(authorities.getId());
//    authoritiesForm.setUsername(authorities.getUsername());
//    authoritiesForm.setAuthority(authorities.getAuthority());

//    userForm.setAuthoritiesForm(authoritiesForm);

    UserExt userExt = userExtService.findByUserId(userId);
    UserExtForm userExtForm = new UserExtForm(userExtService);
    userExtForm.setUserId(userExt.getUserId());
    userExtForm.setLastName(userExt.getLastName());
    userExtForm.setFirstName(userExt.getFirstName());
    userExtForm.setEmail(userExt.getEmail());
    userExtForm.setBirthDay(userExt.getBirthDay());
    userExtForm.setPhoneNumber(userExt.getPhoneNumber());
    userExtForm.setEntryDate(userExt.getEntryDate());
    userExtForm.setUpdateDate(userExt.getUpdateDate());

    userForm.setUserExtForm(userExtForm);

    model.addAttribute("usersForm", userForm);
    return "/user/showDetail";
  }

  @GetMapping("/create")
  String createForm(
      @ModelAttribute UserForm userForm,
      @ModelAttribute UserExtForm userExtForm,
//      @ModelAttribute AuthoritiesForm authoritiesForm,
      Model model) {

//    List<RoleName> authorityList = RoleName.getRoleNameList();

    userForm.setUserExtForm(userExtForm);
//    userForm.setAuthoritiesForm(authoritiesForm);

    model.addAttribute("usersForm", userForm);
//    model.addAttribute("authorityList", authorityList);
    return "/user/createDetail";
  }

  @RequestMapping(value = "/{userId}/edit", method = RequestMethod.POST)
  String editForm(@PathVariable("userId") Long userId,
                  Model model) {

    UsersDetails usersDetails = usersDetailsService.findByUserId(userId);
    UserExtForm userExtForm = userExtService.findByUserIdToForm(userId);

//    usersDetails.setUserExtForm(userExtForm);
//    AuthoritiesForm authoritiesForm = new AuthoritiesForm(
//        authoritiesService)
//        .setAuthorityById(usersDetails.getUser().getId());
//    usersDetails.setAuthoritiesForm(authoritiesForm);

//    List<RoleName> authorityList = RoleName.getRoleNameList();

    model.addAttribute("usersDetails", usersDetails);
//    model.addAttribute("authorityList", authorityList);

    return "/user/editDetail";
  }

  @PostMapping("/createConfirm")
  String createConfirm(
      @Validated @ModelAttribute UserForm userForm,
      @Validated @ModelAttribute UserExtForm userExtForm,
//      @Validated @ModelAttribute AuthoritiesForm authoritiesForm,
      @Validated @RequestParam("authoritiesForm.authority")
      RoleName authority,
      BindingResult result,
      Model model) {

    if (usersDetailsService.existsById(userForm.getId())) {
      result.rejectValue("username", "error.username", "このユーザ名は既に登録されています");
      return createForm(
//          userForm, userExtForm, authoritiesForm, model);
          userForm, userExtForm, model);
    }

    if (result.hasErrors()) {
      return createForm(
//          userForm, userExtForm, authoritiesForm, model);
          userForm, userExtForm, model);
    }

    User user = new User();
    UsersDetails usersDetails = new UsersDetails(user);
    setUsers(userForm, user);
    usersDetails.getUser().setId(usersDetails.getUser().getId());
    User resultUser
        = usersDetailsService.saveAndFlush(user);

    UserExt userExt = new UserExt();
    setUsersExt(userExtForm, userExt);
    userExt.setUserId(resultUser.getUserId());
    UserExt resultUserExt
        = userExtService.saveUserExt(userExt);

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
      @Validated @ModelAttribute UserForm userForm,
      @Validated @ModelAttribute UserExtForm userExtForm,
//      @Validated @ModelAttribute AuthoritiesForm authoritiesForm,
      @Validated @RequestParam("authority") RoleName authority,
      BindingResult bindingResult,
      @PathVariable("userId") Long userId,
      Model model) {
    if (bindingResult.hasErrors()) {
      return editForm(userId, model);
    }
    User user = usersDetailsService.findByUserId(userId).getUser();
    String username = user.getId();
    setUsers(userForm, user);
    user = usersDetailsService.saveAndFlush(user);

    UserExt userExt = userExtService.findByUserId(userId);
    setUsersExt(userExtForm, userExt);
    userExtService.saveUserExt(userExt);

//    Authorities authorities
//        = authoritiesService.findByUsername(username);
//    authoritiesForm.setAuthority(authority);
//    setAuthorities(authoritiesForm, authorities);
//    authoritiesService.saveAuthorities(authorities);


    return "redirect:/users";
  }
}
