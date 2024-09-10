package com.imti.UserRegistrationAndLoginSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/private")
public class PrivateControllerExample {

    @GetMapping
    public String privateEndPoint() {
        return "private";
    }
}
