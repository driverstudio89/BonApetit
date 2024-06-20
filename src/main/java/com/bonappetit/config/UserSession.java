package com.bonappetit.config;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class UserSession {


    private long id;

    private String username;


    public boolean isLoggedIn() {
        if (username != null) {
            return true;
        }
        return false;
    }

    public void login(long id, String username) {
        this.id = id;
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void logout() {
        username = null;
        id = 0;
    }
}
