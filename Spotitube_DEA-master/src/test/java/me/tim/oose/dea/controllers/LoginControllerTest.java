package me.tim.oose.dea.controllers;

import me.tim.oose.dea.mo.User;
import me.tim.oose.dea.controllers.dto.login.LoginRequest;
import me.tim.oose.dea.controllers.dto.login.LoginResponse;
import me.tim.oose.dea.exceptions.UnauthorizedUserException;
import me.tim.oose.dea.service.LoginService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Response;


@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

    @Mock
    private LoginService loginService;

    @InjectMocks
    private LoginController loginController;

    private LoginRequest loginRequest;

    @Before
    public void setup() {
        loginRequest = new LoginRequest();
    }

    @Test
    public void LoginWithCorrectUserTest() throws UnauthorizedUserException {
        loginRequest.setUser("Uwe");
        loginRequest.setPassword("Uwe");

        LoginResponse loginResponse = new LoginResponse("Uwe", "Van Heesch", "0892-bva2-he7d");
        User user = new User();

        user.setUser("Uwe");
        user.setPassword("Uwe");
        user.setToken("0892-bva2-he7d");
        user.setFirstName("Uwe");
        user.setLastName("van Heesch");

        Mockito.when(loginService.authorized("Uwe", "Uwe")).thenReturn(user);

        Response actual = loginController.login(loginRequest);

        Assert.assertEquals(Response.ok(loginResponse).build().getStatus(), actual.getStatus());
    }

    @Test
    public void LoginWithWrongUserThrowsExceptionTest() throws UnauthorizedUserException {
        loginRequest.setUser("user");
        loginRequest.setPassword("password");

        Mockito.when(loginService.authorized("user", "password")).thenThrow(UnauthorizedUserException.class);

        Response actual = loginController.login(loginRequest);
        Assert.assertEquals(401, actual.getStatus());
    }

}
