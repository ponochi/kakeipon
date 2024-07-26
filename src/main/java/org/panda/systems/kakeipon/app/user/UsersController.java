package org.panda.systems.kakeipon.app.user;

import jakarta.validation.Valid;
import org.panda.systems.kakeipon.domain.model.user.User;
import org.panda.systems.kakeipon.domain.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
// @RequestMapping( "user" )
public class UsersController {
  @Autowired
  UserService userService;

  @ModelAttribute
  UserForm setUpForm( ) {
    return new UserForm( );
  }

  @GetMapping( "user" )
  String list( Model model ) {
    List<User> users = userService.findAll( );
    model.addAttribute( "users", users );
    return "user/showList";
  }

  @GetMapping( "user/{id}/show" )
  String show( @PathVariable Long id, Model model ) {
    User user = userService.findByUserId( id );
    model.addAttribute( "user", user );
    return "user/showDetail";
  }

  @GetMapping( "user/create" )
  String create( Model model ) {
    return "user/create";
  }

  @PostMapping( "user/create" )
  String create( @Valid UserForm form, BindingResult result, Model model ) {
    if ( result.hasErrors( ) ) {
      return create( model );
    }
    User user = new User( );
    user.setUserId( form.getUserId( ) );
    user.setNickName( form.getNickName( ) );
    user.setPassword( form.getPassword( ) );
    user.setPhoneNumber( form.getPhoneNumber( ) );
    user.setRoleName( form.getRoleName( ) );
    user.setEntryDate( form.getEntryDate( ) );
    userService.save( user );
    return "redirect:/user";
  }

  @GetMapping( "user/{id}/edit" )
  String edit( @PathVariable Long id, Model model ) {
    User user = userService.findByUserId( id );
    model.addAttribute( "user", user );
    return "user/editDetail";
  }

  @PostMapping( "user/{id}/commit" )
  String edit( @PathVariable Long id, @Validated UserForm form, BindingResult result, Model model ) {
    if ( result.hasErrors( ) ) {
      System.out.println( "commit: " );
      System.out.println( result );
      return edit( id, model );
    }
    User user = userService.findByUserId( id );
    user.setUserId( form.getUserId( ) );
    user.setNickName( form.getNickName( ) );
    user.setLastName( form.getLastName( ) );
    user.setFirstName( form.getFirstName( ) );
    user.setPassword( form.getPassword( ) );
    user.setEmail( form.getEmail( ) );
    user.setBirthday( form.getBirthday() );
    user.setPhoneNumber( form.getPhoneNumber( ) );
    user.setRoleName( form.getRoleName( ) );
    user.setEntryDate( form.getEntryDate( ) );
    user.setUpdateDate( form.getUpdateDate( ) );
    userService.save( user );
    return "redirect:/user/{id}/show";
  }
}
