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
@Data
public class UsersForm implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  private final UsersDetailService usersDetailService;

  @Bean
  PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories
        .createDelegatingPasswordEncoder();
  }

  @NotEmpty(message = "ユーザ名は必須です")
  @Column(name = "username")
  private String username;

  @Column(name = "user_id")
  private Long userId;

  @OneToOne
  @JoinColumn(name = "user_id", table = "users_ext",
      referencedColumnName = "user_id",
      insertable = false, updatable = false)
  @PrimaryKeyJoinColumn
  @Column(name = "user_id")
  private UsersExtForm usersExtForm;

  @NotEmpty(message = "パスワードは必須です")
  @Column(name = "password")
  private String password;

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
  }

  public UsersForm(
      UsersDetailService usersDetailService) {

    this.usersDetailService = usersDetailService;
  }

  public UsersForm setUserToForm(
      UsersDetailService usersDetailService,
      Users users) {

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

    return usersForm;
  }
}
