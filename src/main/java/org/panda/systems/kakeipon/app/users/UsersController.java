package org.panda.systems.kakeipon.app.users;

import org.panda.systems.kakeipon.domain.model.users.RoleName;
import org.panda.systems.kakeipon.domain.model.users.Users;
import org.panda.systems.kakeipon.domain.model.users.UsersExt;
import org.panda.systems.kakeipon.domain.service.users.UsersDetail;
import org.panda.systems.kakeipon.domain.service.users.UsersDetailService;
import org.panda.systems.kakeipon.domain.service.users.UsersExtService;
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
  @Autowired
  private PasswordEncoder passwordEncoder;

  // Default constructor
  public UsersController() {

  }

  private void setUsers(UsersForm form, Users users) {
    users.setUsername(form.getUsername());
    users.setUserId(form.getUserId());
    users.setPassword(passwordEncoder.encode(form.getPassword()));
    users.setRoleName(form.getRoleName());
    users.setEnabled(form.getEnabled());
    users.setAccountNonExpired(form.getAccountNonExpired());
    users.setAccountNonLocked(form.getAccountNonLocked());
    users.setCredentialsNonExpired(form.getCredentialsNonExpired());
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

//  @ModelAttribute(name = "userForm")
  @ModelAttribute(name = "usersDetails")
  UsersForm setUpUserForm() {
    return new UsersForm(
        usersDetailService);
  }

  @RequestMapping(value = "", method = RequestMethod.GET)
  String list(Model model) {
    List<UsersDetail> usersDetails = usersDetailService.findAllUsersToForm();

    model.addAttribute("usersDetails", usersDetails);
    return "/users/showList";
  }

  @RequestMapping(value = "/{userId}/show", method = RequestMethod.GET)
  String show(@PathVariable Long userId, Model model) {
    Users users = usersDetailService.findByUserId(userId);
    UsersForm usersForm = new UsersForm(
        usersDetailService);

    usersForm.setUserId(users.getUserId());
    usersForm.setUsername(users.getUsername());
    usersForm.setPassword(users.getPassword());
    usersForm.setRoleName(users.getRoleName());
    usersForm.setEnabled(users.getEnabled());
    usersForm.setAccountNonExpired(true);
    usersForm.setAccountNonLocked(true);
    usersForm.setCredentialsNonExpired(true);

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
    return "/users/showDetail";
  }

  @GetMapping("/create")
  String createForm(
      @ModelAttribute UsersForm usersForm,
      @ModelAttribute UsersExtForm usersExtForm,
      Model model) {

    usersForm.setEnabled(true);
    usersForm.setAccountNonExpired(true);
    usersForm.setAccountNonLocked(true);
    usersForm.setCredentialsNonExpired(true);

    usersForm.setUsersExtForm(usersExtForm);

    List<RoleName> roleNames = RoleName.getRoleNameList();

    model.addAttribute("usersForm", usersForm);
    model.addAttribute("roleNames", roleNames);
    return "/users/createDetail";
  }

  @RequestMapping(value = "/{userId}/edit", method = RequestMethod.POST)
  String editForm(@PathVariable("userId") Long userId,
                  @ModelAttribute UsersForm usersForm,
                  Model model) {

    Users users = usersDetailService.findByUserId(userId);
    usersForm.setUsername(users.getUsername());
    usersForm.setPassword(users.getPassword());
    usersForm.setRoleName(users.getRoleName());
    usersForm.setEnabled(users.getEnabled());
    usersForm.setAccountNonExpired(users.getAccountNonExpired());
    usersForm.setAccountNonLocked(users.getAccountNonLocked());
    usersForm.setCredentialsNonExpired(users.getCredentialsNonExpired());

    UsersExtForm usersExtForm = usersExtService.findByUserIdToForm(userId);

    usersForm.setUsersExtForm(usersExtForm);

    List<RoleName> roleNames = RoleName.getRoleNameList();

    model.addAttribute("usersForm", usersForm);
    model.addAttribute("roleNames", roleNames);
    return "/users/editDetail";
  }

  @PostMapping("/createConfirm")
  String createConfirm(
      @Validated @ModelAttribute UsersForm usersForm,
      @Validated @ModelAttribute UsersExtForm usersExtForm,
      RoleName roleName,
      BindingResult result,
      Model model) {

    if (usersDetailService.existsByUsername(usersForm.getUsername())) {
      result.rejectValue("username", "error.username", "このユーザ名は既に登録されています");
      return createForm(
          usersForm, usersExtForm, model);
    }

    if (result.hasErrors()) {
      return createForm(
          usersForm, usersExtForm, model);
    }

    Users users = new Users();
    setUsers(usersForm, users);
    Users resultUsers
        = usersDetailService.saveAndFlush(users);

    UsersExt usersExt = new UsersExt();
    setUsersExt(usersExtForm, usersExt);
    usersExt.setUserId(resultUsers.getUserId());
    UsersExt resultUsersExt
        = usersExtService.saveUserExt(usersExt);

    return "redirect:/users";
  }

  @RequestMapping(value = "{userId}/confirm", method = RequestMethod.POST)
  String confirm(
      @Validated @ModelAttribute UsersForm usersForm,
      @Validated @ModelAttribute UsersExtForm usersExtForm,
      @PathVariable("userId") Long userId,
      BindingResult bindingResult,
      Model model) {

    if (bindingResult.hasErrors()) {
      return editForm(userId, usersForm, model);
    }

    Users users = usersDetailService.findByUserId(userId);
    setUsers(usersForm, users);

    usersDetailService.saveAndFlush(users);

    UsersExt usersExt = usersExtService.findByUserId(userId);
    setUsersExt(usersExtForm, usersExt);
    usersExtService.saveUserExt(usersExt);

    return "redirect:/users";
  }
}
