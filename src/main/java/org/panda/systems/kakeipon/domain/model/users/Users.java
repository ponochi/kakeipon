package org.panda.systems.kakeipon.domain.model.users;

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
public class Users implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  // Constructor with parameters
  public Users(Long userId, String username, String password, Boolean enabled) {
    this.userId = userId;
    this.username = username;
    this.password = password;
    this.enabled = true;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "users_seq", allocationSize = 1)
  @Column(name = "user_id")
  private Long userId;

  @Column(name = "username")
  private String username;

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

  public Users() {
    this.enabled = true;
  }
}
