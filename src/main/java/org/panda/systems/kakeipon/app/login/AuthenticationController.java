package org.panda.systems.kakeipon.app.login;

import org.panda.systems.kakeipon.domain.model.user.Authorities;
import org.panda.systems.kakeipon.domain.model.user.RoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {
  @Autowired
  private Authorities authorities;

  @GetMapping("/login")
  String viewLoginForm(Model model) {
    return "loginForm";
  }
}
