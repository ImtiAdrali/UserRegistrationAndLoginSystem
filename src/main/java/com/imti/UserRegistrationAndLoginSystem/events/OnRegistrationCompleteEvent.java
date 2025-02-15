package com.imti.UserRegistrationAndLoginSystem.events;

import com.imti.UserRegistrationAndLoginSystem.model.User;
import org.springframework.cglib.core.Local;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private final String appUrl;
    private final Locale locale;
    private final User user;

    public OnRegistrationCompleteEvent(final User user, final Locale locale, final String appUrl) {
        super(user);
        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public Locale getLocal() {
        return locale;
    }

    public User getUser() {
        return user;
    }
}
