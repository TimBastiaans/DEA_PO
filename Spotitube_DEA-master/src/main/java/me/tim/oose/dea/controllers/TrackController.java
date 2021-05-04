package me.tim.oose.dea.controllers;

import me.tim.oose.dea.mo.Track;
import me.tim.oose.dea.controllers.dto.track.TrackResponse;
import me.tim.oose.dea.exceptions.UnauthorizedUserException;
import me.tim.oose.dea.service.TrackService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/tracks")
public class TrackController extends Responses {


    @Inject
    private TrackService trackService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getTracksNotInPlaylist(@QueryParam("forPlaylist") int playlistId, @QueryParam("token") String token) throws UnauthorizedUserException {
        List<Track> tracks = trackService.getTracksNotInPlaylist(playlistId, token);
        TrackResponse response = new TrackResponse(tracks);
        return respondOk(response);
    }

}
