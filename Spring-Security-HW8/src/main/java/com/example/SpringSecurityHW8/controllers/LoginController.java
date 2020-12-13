package com.example.SpringSecurityHW8.controllers;

import com.example.SpringSecurityHW8.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    private UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    //@GetMapping("/logout")
    public String logout(){
        return "redirect:/";
    }

}
