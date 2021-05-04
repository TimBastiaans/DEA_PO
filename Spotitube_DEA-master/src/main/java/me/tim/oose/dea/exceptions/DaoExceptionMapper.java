package me.tim.oose.dea.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DaoExceptionMapper implements ExceptionMapper<DaoException> {
    @Override
    public Response toResponse(DaoException exception){
        return Response.status(Response.Status.NOT_FOUND).entity("Data access object is having this error: " + exception.getMessage()).build();
    }
}