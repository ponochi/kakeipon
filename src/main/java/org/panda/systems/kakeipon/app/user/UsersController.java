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

//  // ToDo: Implements create new user function.
//  @GetMapping( "user/create" )
//  String create( Model model ) {
//    return "user/create";
//  }

//  // ToDo: Implements create new user function.
//  @PostMapping( "user/create" )
//  String create( @Valid UserForm form,
//                 BindingResult result, Model model ) {
//    if ( result.hasErrors( ) ) {
//      return create( model );
//    }
//    User user = new User( );
//    user.setUserId( form.getUserId( ) );
//    user.setNickName( form.getNickName( ) );
//    user.setPassword( form.getPassword( ) );
//    user.setPhoneNumber( form.getPhoneNumber( ) );
//    user.setRoleName( form.getRoleName( ) );
//    user.setEntryDate( form.getEntryDate( ) );
//    userService.save( user );
//    return "redirect:/user";
//  }

  @GetMapping( "user/{id}/edit" )
  String editForm( @PathVariable Long id, Model model ) {
    User user = userService.findByUserId( id );
    model.addAttribute( "user", user );
    return "user/editDetail";
  }

  @PostMapping( "user/{id}/confirm" )
  String confirm( @Validated UserInfo form, BindingResult bindingResult,
               @PathVariable Long id, Model model ) {
    if ( bindingResult.hasErrors( ) ) {
      return editForm( id, model );
    }
    User user = userService.findByUserId( id );
    user.setUserId( form.getUserId( ) );
    user.setNickName( form.getNickName( ) );
    user.setLastName( form.getLastName( ) );
    user.setFirstName( form.getFirstName( ) );
    user.setPassword( form.getPassword( ) );
    user.setEmail( form.getEmail( ) );
    LocalDateTime dt = LocalDateTime.parse(
        form.getBirthdayString(),
        DateTimeFormatter.ISO_DATE_TIME );
    user.setBirthday( dt );
    user.setPhoneNumber( form.getPhoneNumber( ) );
    user.setRoleName( form.getRoleName( ) );
    user.setEntryDate( form.getEntryDate( ) );
    user.setUpdateDate( LocalDateTime.now() );
    userService.save( user );
    return "redirect:/user/{id}/show";
  }
}
