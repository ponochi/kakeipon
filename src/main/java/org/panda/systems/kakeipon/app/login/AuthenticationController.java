package org.panda.systems.kakeipon.app.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {

  @GetMapping("/login")
  String viewLoginForm(Model model) {
    return "loginForm";
  }
}
