package org.panda.systems.kakeipon.app.users;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.panda.systems.kakeipon.domain.model.users.RoleName;
import org.panda.systems.kakeipon.domain.model.users.Users;
import org.panda.systems.kakeipon.domain.service.users.UsersDetailService;
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
public class UsersForm implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  private final UsersDetailService usersDetailService;
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
  private UsersExtForm usersExtForm;

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
  public UsersForm() {
    this.usersDetailService = null;
//    this.authoritiesService = null;
  }

  public UsersForm(
      UsersDetailService usersDetailService) {
    //    AuthoritiesService authoritiesService) {

    this.usersDetailService = usersDetailService;
//    this.authoritiesService = authoritiesService;
  }

  public UsersForm setUserToForm(
      UsersDetailService usersDetailService,
//      AuthoritiesService authoritiesService,
      Users users) {

    UsersForm usersForm = new UsersForm(
        usersDetailService);
//        authoritiesService);

    usersForm.setUserId(users.getUserId());
    usersForm.setId(users.getId());
    usersForm.setPassword(users.getPassword());
    usersForm.setRoleName(users.getRoleName());

//    Authorities authorities = authoritiesService.findByUsername(
//        users.getId());
//    usersForm.setAuthoritiesToForm(authorities);
    usersForm.setEnabled(users.getEnabled());
    usersForm.setAccountNonExpired(true);
    usersForm.setAccountNonLocked(true);
    usersForm.setCredentialsNonExpired(true);

    return usersForm;
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
