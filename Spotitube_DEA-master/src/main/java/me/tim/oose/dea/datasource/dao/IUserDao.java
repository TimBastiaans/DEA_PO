package me.tim.oose.dea.datasource.dao;

import me.tim.oose.dea.exceptions.DaoException;
import me.tim.oose.dea.mo.User;

public interface IUserDao {
    User findUserByUsername(String username) throws DaoException;

    User findUserByToken(String token) throws DaoException;

}
