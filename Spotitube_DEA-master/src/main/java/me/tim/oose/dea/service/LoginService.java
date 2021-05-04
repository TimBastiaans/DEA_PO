package me.tim.oose.dea.service;

import me.tim.oose.dea.mo.User;
import me.tim.oose.dea.datasource.dao.UserDao;
import me.tim.oose.dea.exceptions.UnauthorizedUserException;

import javax.inject.Inject;

public class LoginService implements ILoginService {

    public LoginService() {
    }

    @Inject
    private UserDao userdao;

    public User authorized(String username, String password) throws UnauthorizedUserException {
        User user = userdao.findUserByUsername(username);

        if (user == null) {
            throw new UnauthorizedUserException();
        }
        if (!user.getPassword().equals(password)) {
            throw new UnauthorizedUserException();
        }
        return user;
    }

    public boolean authorizeByToken(String Token) throws UnauthorizedUserException {
        User user = userdao.findUserByToken(Token);
        if (user == null) {
            throw new UnauthorizedUserException();
        }
        return true;
    }
}
