package org.panda.systems.kakeipon.app.user;

import org.panda.systems.kakeipon.domain.model.user.Role;
import org.panda.systems.kakeipon.domain.model.user.User;
import org.panda.systems.kakeipon.domain.service.user.RoleService;
import org.panda.systems.kakeipon.domain.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
  @Autowired
  UserService userService;
  @Autowired
  RoleService roleService;

  // Default constructor
  public UserController() {
  }

  private void setUser(UserForm form, User user) {
    user.setUserId(form.getUserId());
    user.setNickName(form.getNickName());
    user.setLastName(form.getLastName());
    user.setFirstName(form.getFirstName());
    user.setPassword(form.getPassword());
    user.setEmail(form.getEmail());
    user.setBirthDay(form.getBirthDay());
    user.setPhoneNumber(form.getPhoneNumber());

  }

  @ModelAttribute(name = "userForm")
  UserForm setUpUserForm() {
    UserForm form = new UserForm();
    form.setRoleForm(new RoleForm());
    form.getRoleForm().setRoleName(
        roleService.findByRoleId(Long.parseLong("2")).getRoleName());
    return form;
  }

  @RequestMapping(value = "", method = RequestMethod.GET)
  String list(Model model) {
    List<UserForm> userForms = userService.findAllUserForm();
    model.addAttribute("userForms", userForms);
    return "/user/showList";
  }

  @RequestMapping(value = "{userId}/show", method = RequestMethod.GET)
  String show(@PathVariable Long userId, Model model) {
    User user = userService.findById(userId);
    UserForm userForm = new UserForm();
    UserForm form = userForm.setUserToForm(
        user.getUserId(),
        user.getNickName(),
        user.getLastName(),
        user.getFirstName(),
        user.getBirthDay(),
        user.getPassword(),
        user.getPhoneNumber(),
        user.getEmail(),
        user.getRoleId(),
        roleService.findByRoleId(user.getRoleId()).getRoleName(),
        user.getEntryDate(),
        user.getUpdateDate());
    model.addAttribute("userForm", form);
    return "/user/showDetail";
  }

  // ToDo: Implements create new user function.
  @GetMapping("create")
  String createForm(UserForm form, Model model) {
    List<Role> roles = roleService.findAll();
    form.getRoleForm().setRoleId(form.getRoleId());
    form.getRoleForm().setRoleName(
        roleService.findByRoleId(Long.parseLong("2")).getRoleName());
    model.addAttribute("form", form);
    model.addAttribute("roles", roles);
    return "/user/createDetail";
  }

  @RequestMapping(value = "{userId}/edit", method = RequestMethod.POST)
  String editForm(@PathVariable Long userId,
                  Model model) {
    User user = userService.findById(userId);
    List<Role> roles = roleService.findAll();

    model.addAttribute("user", user);
    model.addAttribute("roles", roles);

    return "/user/editDetail";
  }

  // ToDo: Implements create new user function.
  @PostMapping("createConfirm")
  String createConfirm(@Validated UserForm form, BindingResult result,
                       Model model) {
    if (result.hasErrors()) {
      return createForm(form, model);
    }
    User user = new User();
    setUser(form, user);
    user.setRoleId(form.getRoleId());
    user.setEntryDate(LocalDateTime.now());
    user.setUpdateDate(LocalDateTime.now());
    User resultUser = userService.saveUser(user);

    resultUser = userService.findById(resultUser.getUserId());

    model.addAttribute("user", resultUser);
    return "redirect:/user/" + resultUser.getUserId() + "/show";
  }

  @RequestMapping(value = "{userId}/confirm", method = RequestMethod.POST)
  String confirm(@Validated UserForm form, BindingResult bindingResult,
                 @PathVariable Long userId, Model model) {
    if (bindingResult.hasErrors()) {
      return editForm(userId, model);
    }
    User user = userService.findById(userId);
    setUser(form, user);
    user.setRoleId(form.getRoleId());
    user.setEntryDate(form.getEntryDate());
    user.setUpdateDate(LocalDateTime.now());

    User result = userService.saveUser(user);

    return "redirect:/user/" + result.getUserId() + "/show";
  }
}
