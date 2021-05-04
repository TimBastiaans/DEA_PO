package me.tim.oose.dea.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
public class DatabaseConnectionExceptionMapper implements ExceptionMapper<DatabaseConnectionException> {
    @Override
    public Response toResponse(DatabaseConnectionException exception){
        return Response.status(Response.Status.NOT_FOUND).entity("Error: " + exception.getMessage()).build();
    }
}
