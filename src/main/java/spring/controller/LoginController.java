package spring.controller;

import spring.model.User;
import spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "login")
    public String loginPage() {
        return "login";
    }

    @GetMapping(value = "user")
    public String userPage(Authentication authentication, Model model) {
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user);
        return "user";
    }
}
