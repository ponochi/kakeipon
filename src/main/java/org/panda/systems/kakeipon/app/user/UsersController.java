package org.panda.systems.kakeipon.app.user;

import org.panda.systems.kakeipon.domain.model.User;

import org.panda.systems.kakeipon.domain.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Controller
@RequestMapping("user")
public class UsersController {
    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    String listUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        System.out.println("Debug-1!!!");
        return "user/listUsers";
    }

    @RequestMapping(path="userDetail/{userId}", method = RequestMethod.GET)
    public String listUsers(@PathVariable(value = "userId") Long userId, Model model) {
        User user = userService.findByUserId(userId);
        System.out.println("Debug-2!!!");
        if (user != null) {
            model.addAttribute("user", user);
            return "user/userDetail";
        } else {
            return "user/listUsers";
        }
    }
}
