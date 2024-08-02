package org.panda.systems.kakeipon.app.common;

import org.panda.systems.kakeipon.domain.model.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MainController {
  @GetMapping( "top")
  String top( Model model ) {
    // model.addAttribute( "sers", users );
    return "/top";
  }
}
