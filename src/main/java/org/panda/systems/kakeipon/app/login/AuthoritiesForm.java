package org.panda.systems.kakeipon.app.login;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.panda.systems.kakeipon.domain.model.user.Authorities;
import org.panda.systems.kakeipon.domain.model.user.RoleName;
import org.panda.systems.kakeipon.domain.service.user.AuthoritiesService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serial;
import java.io.Serializable;

@Table(name = "authorities")
@SecondaryTable(name = "users",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "username"))
@Data
public class AuthoritiesForm implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "authorities_seq", allocationSize = 1)
  @Column(name = "id")
  private Integer id;

  @NotEmpty(message = "ユーザ名は必須です")
  @Column(name = "username")
  private String username;

  @Enumerated(EnumType.STRING)
  @Column(name = "authority")
  private RoleName authority;

  // Default constructor
  public AuthoritiesForm() {

  }

  public AuthoritiesForm(AuthoritiesService service,
                         String username) {
    Authorities authorities = service.findByUsername(username);

    this.setUsername(authorities.getUsername());
    if (authorities.getId() == null) {
      this.id = 0;
      this.username = username;
      this.authority = RoleName.USER;
    } else {
      this.id = authorities.getId();
      this.username = authorities.getUsername();
      this.authority = authorities.getAuthority();
    }
  }

  @SuppressWarnings("ClassEscapesDefinedScope")
  public AuthoritiesForm setAuthoritiesToForm(
      AuthoritiesService service, String username) {
    Authorities authorities = service.findByUsername(username);

    AuthoritiesForm authoritiesForm = new AuthoritiesForm();

    authoritiesForm.setId(authorities.getId());
    authoritiesForm.setUsername(authorities.getUsername());
    authoritiesForm.setAuthority(authorities.getAuthority());

    return authoritiesForm;
  }
}
