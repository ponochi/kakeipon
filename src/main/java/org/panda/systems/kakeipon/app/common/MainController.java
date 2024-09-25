package org.panda.systems.kakeipon.app.common;

import org.panda.systems.kakeipon.domain.service.user.KakeiPonUsersDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
  @GetMapping( "top")
  String top(
      @AuthenticationPrincipal UserDetails userDetails,
      Model model ) {

    model.addAttribute("userDetails", userDetails);
    return "/top";
  }
}
