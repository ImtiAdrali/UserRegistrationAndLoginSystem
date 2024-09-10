package com.imti.UserRegistrationAndLoginSystem.controller;

import com.imti.UserRegistrationAndLoginSystem.dto.RegisterRequest;
import com.imti.UserRegistrationAndLoginSystem.events.OnRegistrationCompleteEvent;
import com.imti.UserRegistrationAndLoginSystem.model.User;
import com.imti.UserRegistrationAndLoginSystem.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;


    @GetMapping(value = "/register")
    public String register(HttpServletRequest request, Model model) {
        RegisterRequest registerRequest = new RegisterRequest();
        model.addAttribute("user", registerRequest);
        return "register";
    }

    @PostMapping(value = "/registerUser", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ModelAndView registerUser(@Valid @ModelAttribute("user") RegisterRequest registerRequest, BindingResult result, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("register");

        if (result.hasErrors()) {
            modelAndView.addObject("message", "Please correct the errors in the form.");
            return modelAndView;
        }

        try {
            User user = userService.register(registerRequest);
            applicationEventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, request.getLocale(), getAppUrl(request)));
            modelAndView.addObject("message", "User register successfully.");
            modelAndView.setViewName("redirect:/index");
        } catch (IllegalArgumentException e) {
            modelAndView.addObject("message", "An account for that username/email already exists.");
            modelAndView.setViewName("register");
        }

        return modelAndView;
    }

    public String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
