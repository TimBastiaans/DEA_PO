package me.tim.oose.dea.controllers.dto.login;

import me.tim.oose.dea.controllers.dto.Dto;

public class LoginResponse implements Dto {
    private String user;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public LoginResponse(String firstName, String lastName, String token) {
        this.user = firstName + " " + lastName;
        this.token = token;
    }

    public String getUser() {
        return user;
    }
}

