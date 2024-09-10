package com.imti.UserRegistrationAndLoginSystem.controller;

import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationEvents {

    @EventListener
    public void onSuccess(AuthenticationSuccessEvent successEvent) {
        System.out.println(successEvent.getAuthentication().getAuthorities());
    }

}
