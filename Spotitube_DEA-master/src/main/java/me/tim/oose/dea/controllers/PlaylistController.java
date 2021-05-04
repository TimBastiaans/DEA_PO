package me.tim.oose.dea.controllers;

import me.tim.oose.dea.mo.Playlist;
import me.tim.oose.dea.mo.Track;
import me.tim.oose.dea.controllers.dto.playlist.PlaylistRequest;
import me.tim.oose.dea.controllers.dto.playlist.PlaylistResponse;
import me.tim.oose.dea.controllers.dto.track.TrackRequest;
import me.tim.oose.dea.controllers.dto.track.TrackResponse;
import me.tim.oose.dea.exceptions.UnauthorizedUserException;
import me.tim.oose.dea.service.PlaylistService;
import me.tim.oose.dea.service.TrackService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/playlists")
public class PlaylistController extends Responses {

    @Inject
    private PlaylistService playlistService;

    @Inject
    private TrackService trackService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response requestPlaylists(@QueryParam("token") String token) throws UnauthorizedUserException {
        List<Playlist> playlists = playlistService.getPlaylists(token, false);
        int length = playlistService.getTotalLength(playlists);
        PlaylistResponse playlistResponse = new PlaylistResponse(playlists, length);
        return respondOk(playlistResponse);
    }

    @GET
    @Path("{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getTracksFromPlaylist(@QueryParam("token") String token, @PathParam("id") int playlistId) throws UnauthorizedUserException {
        List<Track> tracks = trackService.getTracksFromPlaylist(playlistId, token, false);
        TrackResponse response = new TrackResponse(tracks);
        return respondOk(response);
    }

    @POST
    @Path("{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(@QueryParam("token") String token, @PathParam("id") int playlistId, TrackRequest request) throws UnauthorizedUserException {
        List<Track> tracks = trackService.addTrackToPlaylist(playlistId, request.getId(), request.isOfflineAvailable(), token);
        TrackResponse response = new TrackResponse(tracks);
        return respondCreated(response);
    }

    @DELETE
    @Path("{id}/tracks/{trackId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response removeTrackFromPlaylist(@QueryParam("token") String token, @PathParam("id") int playlistId, @PathParam("trackId") int trackId) throws UnauthorizedUserException {
        List<Track> tracks = trackService.removeTrackFromPlaylist(playlistId, trackId, token);
        TrackResponse response = new TrackResponse(tracks);
        return respondOk(response);
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@QueryParam("token") String token, @PathParam("id") int id) throws UnauthorizedUserException {
        List<Playlist> playlists = playlistService.deletePlaylist(id, token);
        int length = playlistService.getTotalLength(playlists);
        PlaylistResponse response = new PlaylistResponse(playlists, length);
        return respondOk(response);
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePlaylist(@QueryParam("token") String token, PlaylistRequest request) throws UnauthorizedUserException {
        List<Playlist> playlists = playlistService.updatePlaylist(request.getId(), token, request.getName());
        int length = playlistService.getTotalLength(playlists);
        PlaylistResponse response = new PlaylistResponse(playlists, length);
        return respondCreated(response);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addNewPlaylist(@QueryParam("token") String token, PlaylistRequest request) throws UnauthorizedUserException {
        List<Playlist> playlists = playlistService.addNewPlaylist(token, request.getName());
        int length = playlistService.getTotalLength(playlists);
        PlaylistResponse response = new PlaylistResponse(playlists, length);
        return respondCreated(response);
    }

}
