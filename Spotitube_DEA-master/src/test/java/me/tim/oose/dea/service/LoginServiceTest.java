package me.tim.oose.dea.service;

import me.tim.oose.dea.mo.User;
import me.tim.oose.dea.datasource.dao.UserDao;
import me.tim.oose.dea.exceptions.UnauthorizedUserException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {

    @InjectMocks
    private LoginService loginService;

    @Mock
    private UserDao userDAO;

    @Test
    public void authorizeByNamePasswordTest() throws UnauthorizedUserException {
        User expected = new User();

        expected.setUser("Uwe");
        expected.setPassword("Uwe");
        expected.setToken("0892-bva2-he7d");
        expected.setFirstName("Uwe");
        expected.setLastName("van Heesch");

        Mockito.when(userDAO.findUserByUsername("Uwe")).thenReturn(expected);
        User actual = loginService.authorized("Uwe", "Uwe");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void authorizeByTokenIsTrueTest() throws UnauthorizedUserException {
        User user = new User();

        user.setUser("Uwe");
        user.setPassword("Uwe");
        user.setToken("0892-bva2-he7d");
        user.setFirstName("Uwe");
        user.setLastName("van Heesch");

        Mockito.when(userDAO.findUserByToken("0892-bva2-he7d")).thenReturn(user);
        boolean actual = loginService.authorizeByToken("0892-bva2-he7d");
        Assert.assertTrue(actual);
    }


    @Test(expected = UnauthorizedUserException.class)
    public void authenticateWrongPasswordTest() throws UnauthorizedUserException {
        User user = new User();

        user.setUser("Uwe");
        user.setPassword("Uwe");
        user.setToken("0892-bva2-he7d");
        user.setFirstName("Uwe");
        user.setLastName("van Heesch");

        Mockito.when(userDAO.findUserByUsername("Uwe")).thenReturn(user);
        User actual = loginService.authorized("Uwe", "abcd");
    }

    @Test(expected = UnauthorizedUserException.class)
    public void testAuthorizeByTokenThrowsException() throws UnauthorizedUserException {
        Mockito.when(userDAO.findUserByToken("0892-bva2-he7d")).thenReturn(null);
        boolean actual = loginService.authorizeByToken("0891-bva2-he7d");
    }


}
