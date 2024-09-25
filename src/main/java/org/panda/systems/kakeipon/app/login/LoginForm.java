package org.panda.systems.kakeipon.app.login;

import jakarta.persistence.Column;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class LoginForm {
  @Column(name = "username")
  private String username;
  @Column(name = "password")
  private String password;
}
