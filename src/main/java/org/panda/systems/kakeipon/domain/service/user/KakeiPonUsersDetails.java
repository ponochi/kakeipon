package org.panda.systems.kakeipon.domain.service.user;

import jakarta.persistence.*;
import lombok.Data;
import org.panda.systems.kakeipon.domain.model.user.Authorities;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Entity
@Table(name = "users")
@SecondaryTable(name = "authorities",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "username"))
@Data
public class KakeiPonUsersDetails implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "users_seq", allocationSize = 1)
  @Column(name = "id")
  private Integer id;

  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

  @Column(name = "enabled")
  private boolean enabled;

  @Column(name = "accountNonExpired")
  private boolean accountNonExpired;

  @Column(name = "accountNonLocked")
  private boolean accountNonLocked;

  @Column(name = "credentialsNonExpired")
  private boolean credentialsNonExpired;

  public KakeiPonUsersDetails(KakeiPonUsersDetails usersDetails) {
    this.setId(usersDetails.getId());
    this.setUsername(usersDetails.getUsername());
    this.setPassword(usersDetails.getPassword());
    this.setAuthorities(usersDetails.getAuthorities());
    this.setEnabled(true);
    this.setAccountNonExpired(true);
    this.setAccountNonLocked(true);
    this.setCredentialsNonExpired(true);
  }

  @OneToOne
  @JoinColumn(name = "username", table = "authorities",
      referencedColumnName = "username",
      insertable = false, updatable = false)
  @PrimaryKeyJoinColumn(name = "username")
  private Authorities authorities;

  // Default constructor
  public KakeiPonUsersDetails() {

  }

  public boolean isAccountNonExpired() {
    return true;
  }

  public boolean isAccountNonLocked() {
    return true;
  }

  public boolean isCredentialsNonExpired() {
    return true;
  }
}
