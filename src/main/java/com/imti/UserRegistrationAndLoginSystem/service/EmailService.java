package com.imti.UserRegistrationAndLoginSystem.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("{email.from}")
    private String emailFrom;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void senEmail(String to, String subject) throws MessagingException, IOException {
        SimpleMailMessage message = new SimpleMailMessage();
        MimeMessage mailMessage = mailSender.createMimeMessage();
        mailMessage.setRecipients(MimeMessage.RecipientType.TO, to);
        mailMessage.setFrom(emailFrom);
        mailMessage.setSubject(subject);

        String htmlTextMessage = readEmailTemplate("email.html");

        mailMessage.setContent(htmlTextMessage, "text/html; charset=utf-8");

        mailSender.send(mailMessage);
    }

    public String readEmailTemplate(String filesName) throws IOException {
        return new String(Files.readAllBytes(Paths.get("src/main/resources/templates/" + filesName)));
    }
}
