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
@RequestMapping( "user" )
public class UsersController {
  @Autowired
  UserService userService;
  @Autowired
  RoleService roleService;

  @ModelAttribute
  UserForm setUpForm( )
  {
    UserForm form = new UserForm( );
    // form.setRoleId( Long.parseLong( "2" ) );
    if (form.getRole() == null ) {
      form.setRole(roleService.findByRoleId( form.getRoleId() ) );
    }
    return form;
  }

  @GetMapping( "" )
  String list( Model model ) {
    List<User> users = userService.findAll( );
    model.addAttribute( "users", users );
    return "/user/showList";
  }

  @GetMapping( "{id}/show" )
  String show( @PathVariable Long id, Model model ) {
    User user = userService.findByUserId( id );
    // user.setRole( roleService.findByRoleId( user.getRoleId() ) );
    model.addAttribute( "user", user );
    return "/user/showDetail";
  }

  // ToDo: Implements create new user function.
  @GetMapping( "create" )
  String createForm(UserForm form, Model model ) {
    List<Role> roles = roleService.findAll( );
    form.setRoleId(Long.parseLong("1"));
    model.addAttribute( "form", form );
    model.addAttribute( "roles", roles );
    return "/user/createDetail";
  }

  // ToDo: Implements create new user function.
  @PostMapping( "createConfirm" )
  String createConfirm( @Validated UserForm form, BindingResult result,
                        Model model ) {
    if ( result.hasErrors( ) ) {
      return createForm( form, model );
    }
    Role role = roleService.findByRoleId( form.getRoleId() );
    if (role == null) {
      role = new Role();
      role.setRoleId( form.getRoleId() );
    }
    User user = new User( );
    setUser(form, user);
    user.setRoleId( 2L );
//     user.setRole( role );
    user.setEntryDate( form.getEntryDate() );
    user.setUpdateDate( LocalDateTime.now() );
    User resultUser = userService.saveUser( user );
    resultUser = userService.findByUserId( resultUser.getUserId() );
    return "redirect:/user/" + resultUser.getUserId() + "/show";
  }

  @GetMapping( "{id}/edit" )
  String editForm( @PathVariable Long id, Model model ) {
    User user = userService.findByUserId( id );
    List<Role> roles = roleService.findAll( );
    model.addAttribute( "user", user );
    model.addAttribute( "roles", roles );
    return "/user/editDetail";
  }

  @PostMapping( "{id}/confirm" )
  String confirm(@Validated UserForm form, BindingResult bindingResult,
                 @PathVariable Long id, Model model ) {
    if ( bindingResult.hasErrors( ) ) {
      return editForm( id, model );
    }
    User user = userService.findByUserId( id );
    setUser(form, user);
    user.setRoleId( form.getRoleId() );
    user.setRole( form.getRole() );
    user.setEntryDate( form.getEntryDate() );
    user.setUpdateDate( LocalDateTime.now() );
    User result = userService.saveUser( user );
    return "redirect:/user/" + result.getUserId() + "/show";
  }

  private static void setUser(UserForm form, User user) {
    user.setUserId( form.getUserId() );
    user.setNickName( form.getNickName() );
    user.setLastName( form.getLastName() );
    user.setFirstName( form.getFirstName() );
    user.setPassword( form.getPassword() );
    user.setEmail( form.getEmail() );
    user.setBirthday( form.getBirthday() );
    user.setPhoneNumber( form.getPhoneNumber() );
  }
}
