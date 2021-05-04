package me.tim.oose.dea.controllers;

import me.tim.oose.dea.mo.User;
import me.tim.oose.dea.controllers.dto.login.LoginRequest;
import me.tim.oose.dea.controllers.dto.login.LoginResponse;
import me.tim.oose.dea.exceptions.UnauthorizedUserException;
import me.tim.oose.dea.service.ILoginService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/login")
public class LoginController extends Responses {

    @Inject
    private ILoginService iloginService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(LoginRequest loginRequest) {
        try {
            User user = iloginService.authorized(loginRequest.getUser(), loginRequest.getPassword());
            LoginResponse loginResponse = new LoginResponse(user.getFirstName(), user.getLastName(), user.getToken());
            return respondOk(loginResponse);
        } catch (UnauthorizedUserException e) {
            return respondUnauthorized(e);
        }

    }
}
