package me.tim.oose.dea.datasource.dao;

import me.tim.oose.dea.exceptions.DaoException;
import me.tim.oose.dea.mo.User;
import me.tim.oose.dea.datasource.DaoSetup;

import javax.enterprise.inject.Default;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@Default
public class UserDao extends DaoSetup implements IUserDao {

    @Override
    public User findUserByUsername(String username) throws DaoException {
        try {
            prepareStmt("SELECT user, password, token, firstname, lastname FROM user WHERE user = ?");
            preparedStatement.setString(1, username);
            return getUser();
        } catch (SQLException | IOException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public User findUserByToken(String token) throws DaoException {
        try {
            prepareStmt("SELECT user, password, token, firstname, lastname FROM user WHERE token = ?");
            preparedStatement.setString(1, token);
            return getUser();
        }catch (SQLException | IOException e) {
            throw new DaoException(e);
        }
    }

    private User getUser() throws SQLException, DaoException {
        ResultSet results = getResultSet();
        User user = null;

        if (results.next()) {
            user = mapResultSetToUser(results);
        }
        closeConnection();
        return user;
    }

    private User mapResultSetToUser(ResultSet results) throws SQLException, DaoException {
        User user = new User();
        user.setUser(results.getString("user"));
        user.setPassword(results.getString("password"));
        user.setToken(results.getString("token"));
        user.setFirstName(results.getString("firstname"));
        user.setLastName(results.getString("lastname"));
        return user;
    }

}
