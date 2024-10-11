package org.panda.systems.kakeipon.domain.model.user;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;

@Component
@Entity
@Table(name = "users")
//@SecondaryTable(name = "authorities",
//    pkJoinColumns = @PrimaryKeyJoinColumn(name = "username"))
@Data
public class User implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @JoinColumn(name = "user_id", table = "users_ext",
      referencedColumnName = "user_id",
      insertable = false, updatable = false)
  @PrimaryKeyJoinColumn(name = "user_id")
  private Long userId;

  @Column(name = "id")
  private String id;

  @Column(name = "password")
  private String password;

  @Enumerated(EnumType.STRING)
  private RoleName roleName;

  @Column(name = "account_non_expired")
  private Boolean accountNonExpired;

  @Column(name = "account_non_locked")
  private Boolean accountNonLocked;

  @Column(name = "credentials_non_expired")
  private Boolean credentialsNonExpired;

  @Column(name = "enabled")
  private Boolean enabled;

  public User() {
    this.enabled = true;
  }

  public User(Long userId, String id, String password, Boolean enabled) {
    this.userId = userId;
    this.id = id;
    this.password = password;
    this.enabled = true;
  }
}
