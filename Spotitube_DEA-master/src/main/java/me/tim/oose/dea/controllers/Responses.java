package me.tim.oose.dea.controllers;

import me.tim.oose.dea.controllers.dto.Dto;
import me.tim.oose.dea.exceptions.UnauthorizedUserException;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

abstract class Responses {

    Response respondOk(Dto response) {
        return Response.ok(response).build();
    }

    Response respondCreated(Dto response) {
        return Response.status(201).entity(response).build();
    }

    Response respondBadRequest(BadRequestException e) {
        return Response.status(400).entity(e).build();
    }

    Response respondUnauthorized(UnauthorizedUserException e) {
        return Response.status(401).entity(e).build();
    }

    Response respondBadRequest(ForbiddenException e) {
        return Response.status(403).entity(e).build();
    }

    Response respondBadRequest(NotFoundException e) {
        return Response.status(404).entity(e).build();
    }

}
