package org.panda.systems.kakeipon.app.user;

import org.panda.systems.kakeipon.domain.model.User;
import org.panda.systems.kakeipon.domain.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("user")
public class UsersController {
    @Autowired
    UserService userService;
    private Long userId;
    private Model model;

    @GetMapping("")
    String listUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        System.out.println("Debug-1!!!");
        return "user/showListUsers";
    }

    @RequestMapping(path = "/showUserDetail/{userId}", method = RequestMethod.GET)
    public String listUsers(@PathVariable(value = "userId") Long userId, Model model) {
        User user = userService.findByUserId(userId);
        System.out.println("Debug-2!!!");
        if (user != null) {
            model.addAttribute("user", user);
            return "user/showUserDetail";
        } else {
            return "user/showListUsers";
        }
    }

    @RequestMapping(path = "/editUserDetail/{userId}", method = RequestMethod.GET)
    public String editUser(@PathVariable(value = "userId") Long userId, Model model) {
        this.userId = userId;
        this.model = model;
        System.out.println("Debug-3!!!");
        User user = userService.findByUserId(userId);
        if (user != null) {
            model.addAttribute("user", user);
            return "user/editUserDetail";
        } else {
            return "user/showUserDetail";
        }
    }
}
