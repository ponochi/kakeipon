package org.panda.systems.kakeipon.app.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.panda.systems.kakeipon.domain.model.user.RoleName;
import org.panda.systems.kakeipon.domain.model.user.User;
import org.panda.systems.kakeipon.domain.service.user.UsersDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.Serial;
import java.io.Serializable;

@Table(name = "users")
@SecondaryTable(name = "users_ext",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id"))
//@SecondaryTable(name = "authorities",
//    pkJoinColumns = @PrimaryKeyJoinColumn(name = "id"))
@Data
public class UserForm implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  private final UsersDetailsService usersDetailsService;
//  private final AuthoritiesService authoritiesService;

  @Bean
  PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories
        .createDelegatingPasswordEncoder();
  }

  @Column(name = "user_id")
  private Long userId;

  @OneToOne
  @JoinColumn(name = "user_id", table = "users_ext",
      referencedColumnName = "user_id",
      insertable = false, updatable = false)
  @PrimaryKeyJoinColumn
  @Column(name = "user_id")
  private UserExtForm userExtForm;

  @NotEmpty(message = "ユーザ名は必須です")
  @Column(name = "id")
  private String id;

  @NotEmpty(message = "パスワードは必須です")
  @Column(name = "password")
  private String password;

//  @OneToOne
//  @JoinColumn(name = "username", table = "authorities",
//      referencedColumnName = "username",
//      insertable = false, updatable = false)
//  @PrimaryKeyJoinColumn
//  @Column(name = "username")
//  private AuthoritiesForm authoritiesForm;

  @Column(name = "role_name")
  @Enumerated(EnumType.STRING)
  private RoleName roleName;

  @Column(name = "enabled")
  private Boolean enabled;

  @Column(name = "accountNonExpired")
  private Boolean accountNonExpired;

  @Column(name = "accountNonLocked")
  private Boolean accountNonLocked;

  @Column(name = "credentialsNonExpired")
  private Boolean credentialsNonExpired;

  // Default constructor
  public UserForm() {
    this.usersDetailsService = null;
//    this.authoritiesService = null;
  }

  public UserForm(
      UsersDetailsService usersDetailsService) {
    //    AuthoritiesService authoritiesService) {

    this.usersDetailsService = usersDetailsService;
//    this.authoritiesService = authoritiesService;
  }

  public UserForm setUserToForm(
      UsersDetailsService usersDetailsService,
//      AuthoritiesService authoritiesService,
      User user) {

    UserForm userForm = new UserForm(
        usersDetailsService);
//        authoritiesService);

    userForm.setUserId(user.getUserId());
    userForm.setId(user.getId());
    userForm.setPassword(user.getPassword());
    userForm.setRoleName(user.getRoleName());

//    Authorities authorities = authoritiesService.findByUsername(
//        user.getId());
//    userForm.setAuthoritiesToForm(authorities);
    userForm.setEnabled(user.getEnabled());
    userForm.setAccountNonExpired(true);
    userForm.setAccountNonLocked(true);
    userForm.setCredentialsNonExpired(true);

    return userForm;
  }

//  public AuthoritiesForm setAuthoritiesToForm(
//      Authorities authorities) {
//
//    AuthoritiesForm authoritiesForm = new AuthoritiesForm();
//
//    authoritiesForm.setId(authorities.getId());
//    authoritiesForm.setUsername(authorities.getUsername());
//    authoritiesForm.setAuthority(authorities.getAuthority());
//
//    return authoritiesForm;
//  }
}
