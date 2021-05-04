package me.tim.oose.dea.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UnauthororizedUserExceptionMapper implements ExceptionMapper<UnauthorizedUserException> {
    @Override
    public Response toResponse(UnauthorizedUserException exception){
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).entity("The User is not authorized " + exception.getMessage()).build();
    }
}