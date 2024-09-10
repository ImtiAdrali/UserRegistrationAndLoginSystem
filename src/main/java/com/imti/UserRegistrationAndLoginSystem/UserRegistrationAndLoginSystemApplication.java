package com.imti.UserRegistrationAndLoginSystem;

import com.imti.UserRegistrationAndLoginSystem.controller.RegistrationController;
import com.imti.UserRegistrationAndLoginSystem.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.io.IOException;

@SpringBootApplication
public class UserRegistrationAndLoginSystemApplication {

	public static void main(String[] args) throws MessagingException, IOException {
		ApplicationContext context = SpringApplication.run(UserRegistrationAndLoginSystemApplication.class, args);
	}

}
