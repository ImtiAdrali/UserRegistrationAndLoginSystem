package com.imti.UserRegistrationAndLoginSystem.controller;

import com.imti.UserRegistrationAndLoginSystem.dto.LoginRequest;
import com.imti.UserRegistrationAndLoginSystem.dto.RegisterRequest;
import com.imti.UserRegistrationAndLoginSystem.enums.RoleType;
import com.imti.UserRegistrationAndLoginSystem.model.User;
import com.imti.UserRegistrationAndLoginSystem.repository.UserRepository;
import com.imti.UserRegistrationAndLoginSystem.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public String users(Model model) {

        List<User> users = userService.getUsers();
        model.addAttribute("users", users);

        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping(value = "/login" , consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void authenticate(@RequestBody LoginRequest loginRequest) {
        System.out.println("Hello");
    }

    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }
}
