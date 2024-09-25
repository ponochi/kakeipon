package org.panda.systems.kakeipon.domain.model.user;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;

@Component
@Entity
@Table(name = "authorities")
@SecondaryTable(name = "users",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "username"))
@Data
public class Authorities implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "authorities_seq", allocationSize = 1)
  @Column(name = "id")
  private Integer id;

  @Column(name = "username")
  private String username;

  @Enumerated(EnumType.STRING)
  @Column(name = "authority")
  private RoleName authority;

  public Authorities() {
  }

  public Authorities(String username, RoleName authority) {
    this.username = username;
    this.authority = authority;
  }
}
