package com.example.walkaryhj.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
