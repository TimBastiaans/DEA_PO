package me.tim.oose.dea.service;

import me.tim.oose.dea.mo.User;
import me.tim.oose.dea.exceptions.UnauthorizedUserException;

public interface ILoginService {
    User authorized(String username, String password) throws UnauthorizedUserException;

    boolean authorizeByToken(String token) throws UnauthorizedUserException;
}
