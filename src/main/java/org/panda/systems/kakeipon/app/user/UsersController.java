package org.panda.systems.kakeipon.app.user;

import org.panda.systems.kakeipon.domain.model.common.Role;
import org.panda.systems.kakeipon.domain.model.user.User;
import org.panda.systems.kakeipon.domain.service.common.RoleService;
import org.panda.systems.kakeipon.domain.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("user")
public class UsersController {
  @Autowired
  UserService userService;
  @Autowired
  RoleService roleService;

  // Default constructor
  public UsersController() {
  }

  private static void setUser(UserForm form, User user) {
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
//    form.setRoleId(Long.parseLong("2"));
//    form.setRole(roleService.findByRoleId(form.getRoleId()));
    return form;
  }

  @RequestMapping(value = "", method = RequestMethod.GET)
  String list(Model model) {
    List<User> users = userService.findAll();
    model.addAttribute("users", users);
    return "/user/showList";
  }

  @RequestMapping(value = "{userId}/show", method = RequestMethod.GET)
  String show(@PathVariable Long userId, Model model) {
    User user = userService.findById(userId);
    model.addAttribute("user", user);
    return "/user/showDetail";
  }

  // ToDo: Implements create new user function.
  @GetMapping("user/create")
  String createForm(UserForm form, Model model) {
    List<Role> roles = roleService.findAll();
//    form.setRoleId(Long.parseLong("2"));
    form.setRole(roleService.findByRoleId(Long.parseLong("2")));
    model.addAttribute("form", form);
    model.addAttribute("roles", roles);
    return "/user/createDetail";
  }

  @RequestMapping(value = "{userId}/edit", method = RequestMethod.POST)
  String editForm(@PathVariable Long userId,
                  Model model) {
    User user = userService.findById(userId);
    List<Role> roles = roleService.findAll();
    System.out.println("userForm : " + user.toString());
    model.addAttribute("user", user);
    model.addAttribute("roles", roles);
    return "/user/editDetail";
  }

  // ToDo: Implements create new user function.
  @PostMapping("user/createConfirm")
  String createConfirm(@Validated UserForm form, BindingResult result,
                       Model model) {
    if (result.hasErrors()) {
      return createForm(form, model);
    }
    Role role = roleService.findByRoleId(form.getRole().getRoleId());
    if (role == null) {
      role = new Role();
      role.setRoleId(Long.parseLong("2"));
    }
    User user = new User();
    setUser(form, user);
    user.setRole(role);
    user.setEntryDate(form.getEntryDate());
    user.setUpdateDate(LocalDateTime.now());
    User resultUser = userService.saveUser(user);
    resultUser = userService.findById(resultUser.getUserId());
    model.addAttribute("user", resultUser);
    return "redirect:/user/" + resultUser.getUserId() + "/show";
  }

  @RequestMapping(value = "{userId}/confirm", method = RequestMethod.POST)
  String confirm(@Validated UserForm form, BindingResult bindingResult,
                 @PathVariable Long userId, Model model) {
    System.out.println("confirm__ : " + form.toString());
    if (bindingResult.hasErrors()) {
      System.out.println("bindingResult.hasErrors()");
      return editForm(userId, model);
    }
    User user = userService.findById(userId);
    setUser(form, user);
    user.setRole(form.getRole());
    user.setEntryDate(form.getEntryDate());
    user.setUpdateDate(LocalDateTime.now());
    User result = userService.saveUser(user);
    return "redirect:/user/" + result.getUserId() + "/show";
  }
}
