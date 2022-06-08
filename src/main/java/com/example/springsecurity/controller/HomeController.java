package com.example.springsecurity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor(onConstructor_ =@Autowired)
public class HomeController {
    @GetMapping(value = "/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("errorMsg", "Your username and password are invalid.");

        if (logout != null)
            model.addAttribute("msg", "You have been logged out successfully.");

        return "login";
    }
    @GetMapping("/admin")
    public String admin() {
        return "<h2>Welcome Admin!</h2>";
    }

    @GetMapping("/user")
    public String user() {
        return "<h2>Welcome User!</h2>";
    }

    @GetMapping("/")
    public String all() {
        return "home";
    }

}
