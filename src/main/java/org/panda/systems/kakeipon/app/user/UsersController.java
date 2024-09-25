package org.panda.systems.kakeipon.app.user;

import org.panda.systems.kakeipon.app.login.AuthoritiesForm;
import org.panda.systems.kakeipon.domain.model.user.Authorities;
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
  private KakeiPonUsersDetailsService kakeiPonUsersDetailsService;
  @Autowired
  private UserExtService userExtService;
  @Autowired
  private AuthoritiesService authoritiesService;
  @Autowired
  private PasswordEncoder passwordEncoder;

  // Default constructor
  public UsersController() {

  }

  private void setUsers(UserForm form, User user) {
    user.setId(form.getId());
    user.setUsername(form.getUsername());
    user.setPassword(passwordEncoder.encode(form.getPassword()));
    user.setEnabled(true);
//    user.setAccountNonExpired(true);
//    user.setAccountNonLocked(true);
//    user.setCredentialsNonExpired(true);
  }

  private void setUsersExt(UserExtForm form, UserExt userExt) {
    userExt.setId(form.getId());
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

  private void setAuthorities(AuthoritiesForm form, Authorities authorities) {
    authorities.setId(form.getId());
    authorities.setUsername(form.getUsername());
    authorities.setAuthority(form.getAuthority());
  }

//  @ModelAttribute(name = "userForm")
  @ModelAttribute(name = "usersDetails")
  UserForm setUpUserForm() {
    UserForm form = new UserForm(
        kakeiPonUsersDetailsService,
        authoritiesService);
    return form;
  }

  @RequestMapping(value = "", method = RequestMethod.GET)
  String list(Model model) {
    List<UserForm> userForms = kakeiPonUsersDetailsService.findAllUsersToForm();
    for (UserForm userForm : userForms) {
      User user = kakeiPonUsersDetailsService.findById(userForm.getId());
      userForm.setUserToForm(
          kakeiPonUsersDetailsService,
          authoritiesService,
          user);
      AuthoritiesForm authoritiesForm = new AuthoritiesForm(
          authoritiesService,
          user.getUsername()
      );
      userForm.setAuthoritiesForm(authoritiesForm);
    }
    model.addAttribute("usersForms", userForms);
    return "/user/showList";
  }

  @RequestMapping(value = "/{id}/show", method = RequestMethod.GET)
  String show(@PathVariable Integer id, Model model) {
    User user = kakeiPonUsersDetailsService.findById(id);
    UserForm userForm = new UserForm();
    userForm.setId(user.getId());
    userForm.setUsername(user.getUsername());
    userForm.setPassword(user.getPassword());
    userForm.setEnabled(user.getEnabled());
    userForm.setAccountNonExpired(true);
    userForm.setAccountNonLocked(true);
    userForm.setCredentialsNonExpired(true);

    Authorities authorities
        = authoritiesService.findByUsername(userForm.getUsername());
    AuthoritiesForm authoritiesForm = new AuthoritiesForm();
    authoritiesForm.setId(authorities.getId());
    authoritiesForm.setUsername(authorities.getUsername());
    authoritiesForm.setAuthority(authorities.getAuthority());

    userForm.setAuthoritiesForm(authoritiesForm);

    UserExt userExt = userExtService.findById(id);
    UserExtForm userExtForm = new UserExtForm();
    userExtForm.setId(userExt.getId());
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

  // ToDo: Implements create new user function.
  @GetMapping("/create")
  String createForm(
      @ModelAttribute UserForm userForm,
      @ModelAttribute UserExtForm userExtForm,
      @ModelAttribute AuthoritiesForm authoritiesForm,
      Model model) {

    List<RoleName> authorityList = RoleName.getRoleNameList();

    userForm.setUserExtForm(userExtForm);
    userForm.setAuthoritiesForm(authoritiesForm);

    model.addAttribute("usersForm", userForm);
    model.addAttribute("authorityList", authorityList);
    return "/user/createDetail";
  }

  @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
  String editForm(@PathVariable Integer id,
                  Model model) {

    UserForm userForm = kakeiPonUsersDetailsService.findByIdToForm(id);
    UserExtForm userExtForm = userExtService.findByIdToForm(id);
    userForm.setUserExtForm(userExtForm);
    AuthoritiesForm authoritiesForm = new AuthoritiesForm(
        authoritiesService,
        userForm.getUsername()
    );
    userForm.setAuthoritiesForm(authoritiesForm);
    List<RoleName> authorityList = RoleName.getRoleNameList();

    model.addAttribute("usersForm", userForm);
    model.addAttribute("authorityList", authorityList);

    return "/user/editDetail";
  }

  // ToDo: Implements create new user function.
  @PostMapping("/createConfirm")
  String createConfirm(
      @Validated @ModelAttribute UserForm userForm,
      @Validated @ModelAttribute UserExtForm userExtForm,
      @Validated @ModelAttribute AuthoritiesForm authoritiesForm,
      @Validated @RequestParam("authoritiesForm.authority")
      RoleName authority,
      BindingResult result,
      Model model) {

    // ToDo: Implements validator and validation logic.
    if (kakeiPonUsersDetailsService.existsByUsername(userForm.getUsername())) {
      result.rejectValue("username", "error.username", "このユーザ名は既に登録されています");
      return createForm(
          userForm, userExtForm, authoritiesForm, model);
    }

    if (result.hasErrors()) {
      return createForm(
          userForm, userExtForm, authoritiesForm, model);
    }

    KakeiPonUsersDetails usersDetails = new KakeiPonUsersDetails();
    User user = new User();
    setUsers(userForm, user);
    usersDetails.setId(usersDetails.getId());
    User resultUser
        = kakeiPonUsersDetailsService.saveAndFlush(user);

    UserExt userExt = new UserExt();
    setUsersExt(userExtForm, userExt);
    userExt.setId(resultUser.getId());
    UserExt resultUserExt
        = userExtService.saveUserExt(userExt);

    Authorities authorities = new Authorities();
    authoritiesForm.setAuthority(authority);
    setAuthorities(authoritiesForm, authorities);
    authorities.setId(authoritiesForm.getId());
    Authorities resultAuthorities
        = authoritiesService.saveAuthorities(authorities);

    return "redirect:/users";
  }

  @RequestMapping(value = "{id}/confirm", method = RequestMethod.POST)
  String confirm(
      @Validated @ModelAttribute UserForm userForm,
      @Validated @ModelAttribute UserExtForm userExtForm,
      @Validated @ModelAttribute AuthoritiesForm authoritiesForm,
      @Validated @RequestParam("authoritiesForm.authority") RoleName authority,
      BindingResult bindingResult,
      @PathVariable Integer id,
      Model model) {
    if (bindingResult.hasErrors()) {
      return editForm(id, model);
    }
    User user = kakeiPonUsersDetailsService.findById(id);
    String username = user.getUsername();
    setUsers(userForm, user);
    user = kakeiPonUsersDetailsService.saveAndFlush(user);

    UserExt userExt = userExtService.findById(id);
    setUsersExt(userExtForm, userExt);
    userExtService.saveUserExt(userExt);

    Authorities authorities
        = authoritiesService.findByUsername(username);
    authoritiesForm.setAuthority(authority);
    setAuthorities(authoritiesForm, authorities);
    authoritiesService.saveAuthorities(authorities);


    return "redirect:/users";
  }
}
