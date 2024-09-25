package org.panda.systems.kakeipon.app.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.panda.systems.kakeipon.app.login.AuthoritiesForm;
import org.panda.systems.kakeipon.domain.model.user.Authorities;
import org.panda.systems.kakeipon.domain.model.user.User;
import org.panda.systems.kakeipon.domain.service.user.AuthoritiesService;
import org.panda.systems.kakeipon.domain.service.user.KakeiPonUsersDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.Serial;
import java.io.Serializable;

@Table(name = "users")
@SecondaryTable(name = "users_ext",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "id"))
@SecondaryTable(name = "authorities",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "username"))
@Data
public class UserForm implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  private KakeiPonUsersDetailsService kakeiPonUsersDetailsService;
  private AuthoritiesService authoritiesService;

  @Bean
  PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories
        .createDelegatingPasswordEncoder();
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "users_seq", allocationSize = 1)
  @Column(name = "id")
  private Integer id;

  @OneToOne
  @JoinColumn(name = "id", table = "users_ext",
      referencedColumnName = "id",
      insertable = false, updatable = false)
  @PrimaryKeyJoinColumn
  @Column(name = "id")
  private UserExtForm userExtForm;

  @NotEmpty(message = "ユーザ名は必須です")
  @Column(name = "username")
  private String username;

  @NotEmpty(message = "パスワードは必須です")
  @Column(name = "password")
  private String password;

  @OneToOne
  @JoinColumn(name = "username", table = "authorities",
      referencedColumnName = "username",
      insertable = false, updatable = false)
  @PrimaryKeyJoinColumn
  @Column(name = "username")
  private AuthoritiesForm authoritiesForm;

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
//    this.setId(Integer.parseInt("1"));
  }

  public UserForm(
      KakeiPonUsersDetailsService kakeiPonUsersDetailsService,
      AuthoritiesService authoritiesService) {

    this.kakeiPonUsersDetailsService = kakeiPonUsersDetailsService;
    this.authoritiesService = authoritiesService;
  }

  public UserForm setUserToForm(
      KakeiPonUsersDetailsService kakeiPonUsersDetailsService,
      AuthoritiesService authoritiesService,
      User user) {
    UserForm userForm = new UserForm();

    userForm.setId(user.getId());
    userForm.setUsername(user.getUsername());
    userForm.setPassword(user.getPassword());

    Authorities authorities = authoritiesService.findByUsername(
        user.getUsername());
    userForm.setAuthoritiesToForm(authorities);
    userForm.setEnabled(user.getEnabled());
    userForm.setAccountNonExpired(true);
    userForm.setAccountNonLocked(true);
    userForm.setCredentialsNonExpired(true);

    return userForm;
  }

  public AuthoritiesForm setAuthoritiesToForm(
      Authorities authorities) {

    AuthoritiesForm authoritiesForm = new AuthoritiesForm();

    authoritiesForm.setId(authorities.getId());
    authoritiesForm.setUsername(authorities.getUsername());
    authoritiesForm.setAuthority(authorities.getAuthority());

    return authoritiesForm;
  }
}
