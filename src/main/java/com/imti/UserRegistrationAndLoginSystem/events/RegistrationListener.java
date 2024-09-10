package com.imti.UserRegistrationAndLoginSystem.events;

import com.imti.UserRegistrationAndLoginSystem.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    @Autowired
    private EmailService emailService;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        try {
            emailService.senEmail(event.getUser().getEmail(), "Registration Confirmation Email");
        } catch (IOException | MessagingException e) {
            e.printStackTrace();
        }
    }
}
