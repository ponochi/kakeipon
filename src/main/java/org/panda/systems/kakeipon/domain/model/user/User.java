package org.panda.systems.kakeipon.domain.model.user;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;

@Component
@Entity
@Table(name = "users")
@SecondaryTable(name = "authorities",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "username"))
@Data
public class User implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "users_seq", allocationSize = 1)
  @Column(name = "id")
  private Integer id;

  @Column(name = "username")
  private String username;

  @OneToOne
  @JoinColumn(name = "username", table = "authorities",
      referencedColumnName = "username",
      insertable = false, updatable = false)
  @PrimaryKeyJoinColumn(name = "username")
  private Authorities authorities;


  @Column(name = "password")
  private String password;

  @Column(name = "enabled")
  private Boolean enabled;

  public User() {
    this.enabled = true;
  }

  public User(String username, String password, Boolean enabled) {
    this.username = username;
    this.password = password;
    this.enabled = true;
  }
}
