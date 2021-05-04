package me.tim.oose.dea.controllers.dto.login;

import me.tim.oose.dea.controllers.dto.Dto;

public class LoginRequest implements Dto {

    private String user;
    private String password;

    public LoginRequest() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
