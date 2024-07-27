package org.panda.systems.kakeipon.app.user;

import org.panda.systems.kakeipon.domain.model.user.User;
import org.panda.systems.kakeipon.domain.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
// @RequestMapping( "user" )
public class UsersController {
  @Autowired
  UserService userService;

  @ModelAttribute
  UserInfo setUpForm( ) {
    return new UserInfo( );
  }


  @GetMapping( "" )
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

  // ToDo: Implements create new user function.
  @GetMapping( "user/create" )
  String createForm( UserInfo info, Model model ) {
    model.addAttribute( "info", info );
    return "user/createDetail";
  }

  // ToDo: Implements create new user function.
  @PostMapping( "user/createConfirm" )
  String createConfirm( @Validated UserInfo info,
                 BindingResult result, Model model ) {
    if ( result.hasErrors( ) ) {
      return createForm( info, model );
    }

    User user = userService.save( info );
    return "redirect:/user/" + user.getUserId() + "/show";
  }

  @GetMapping( "user/{id}/edit" )
  String editForm( @PathVariable Long id, Model model ) {
    User user = userService.findByUserId( id );
    model.addAttribute( "user", user );
    return "user/editDetail";
  }

  @PostMapping( "user/{id}/confirm" )
  String confirm(@Validated UserInfo info, BindingResult bindingResult,
                 @PathVariable Long id, Model model ) {
    if ( bindingResult.hasErrors( ) ) {
      return editForm( id, model );
    }
    User user = userService.findByUserId( id );
    user.setUserId( info.getUserId( ) );
    user.setNickName( info.getNickName( ) );
    user.setLastName( info.getLastName( ) );
    user.setFirstName( info.getFirstName( ) );
    user.setPassword( info.getPassword( ) );
    user.setEmail( info.getEmail( ) );
    user.setBirthday( info.getBirthday() );
    user.setPhoneNumber( info.getPhoneNumber( ) );
    user.setRoleName( info.getRoleName( ) );
    user.setEntryDate( info.getEntryDate( ) );
    user.setUpdateDate( LocalDateTime.now() );
    user = userService.save( info );
    return "redirect:/user/{id}/show";
  }
}
