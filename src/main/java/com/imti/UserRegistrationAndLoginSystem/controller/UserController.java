package com.imti.UserRegistrationAndLoginSystem.controller;

import com.imti.UserRegistrationAndLoginSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(required = true) Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<String>("User with the id " + id + " deleted successfully.", HttpStatus.OK);
    }
}
