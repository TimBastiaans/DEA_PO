package me.tim.oose.dea.datasource;

import me.tim.oose.dea.datasource.dao.IUserDao;
import me.tim.oose.dea.datasource.dao.UserDao;
import me.tim.oose.dea.exceptions.DaoException;
import me.tim.oose.dea.service.DbConnection;
import org.h2.tools.RunScript;
import me.tim.oose.dea.mo.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UserDaoTest {

    private IUserDao userDao;

    @Before
    public void setUp() throws Exception {
        Properties properties = new Properties();
        properties.load(getClass().getClassLoader().getResourceAsStream("database.properties"));
        DbConnection.getInstance().setProperties(properties);
        DbConnection.getInstance().getConnectionTest().close();
        Connection conn = DbConnection.getInstance().getConnectionTest();
        RunScript.execute(conn, new FileReader("src/main/resources/spotitubeTestDb.sql"));
        userDao = new UserDao();
    }

    @After
    public void closeConn() throws SQLException, IOException {
        Connection conn = DbConnection.getInstance().getConnectionTest();
        conn.createStatement().executeUpdate("DROP DATABASE SpotitubeTest");
        conn.close();
    }

    @Test
    public void findUserByUsernameSuccessTest() throws DaoException {
        //init
        User expected = new User("meron", "meron", "0890-bva2-he7d", "Meron", "Brouwer");

        User actual = userDao.findUserByUsername("meron");

        assertThat(actual.getUser(), is(expected.getUser()));
        assertThat(actual.getPassword(), is(expected.getPassword()));
        assertThat(actual.getToken(), is(expected.getToken()));
        assertThat(actual.getFirstName(), is(expected.getFirstName()));
        assertThat(actual.getLastName(), is(expected.getLastName()));
    }

    @Test
    public void findUserByTokenSuccessTest() throws DaoException {
        //init
        User expected = new User("meron", "meron", "0890-bva2-he7d", "Meron", "Brouwer");

        User actual = userDao.findUserByToken("0890-bva2-he7d");

        assertThat(actual.getUser(), is(expected.getUser()));
        assertThat(actual.getPassword(), is(expected.getPassword()));
        assertThat(actual.getToken(), is(expected.getToken()));
        assertThat(actual.getFirstName(), is(expected.getFirstName()));
        assertThat(actual.getLastName(), is(expected.getLastName()));
    }
}
