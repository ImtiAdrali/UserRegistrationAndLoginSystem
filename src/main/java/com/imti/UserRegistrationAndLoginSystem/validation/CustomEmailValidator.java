package com.imti.UserRegistrationAndLoginSystem.validation;

import com.imti.UserRegistrationAndLoginSystem.annotation.ValidEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomEmailValidator implements ConstraintValidator<ValidEmail, String> {

    private Pattern pattern;
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";

    @Override
    public void initialize(ValidEmail constraintAnnotation) {
        this.pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return validateEmail(email);
    }

    public boolean validateEmail(String email) {
        if (email.isEmpty()) {
            return false;
        }

        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
