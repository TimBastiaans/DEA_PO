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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)

public class PlaylistControllerTest {

    @Mock
    private PlaylistService playlistService;

    @Mock
    private TrackService trackService;

    @InjectMocks
    private PlaylistController playlistController;

    private PlaylistRequest playlistRequest;
    private TrackRequest trackRequest;
    private Playlist playlist;
    private String token;

    @Before
    public void setup() {
        playlistRequest = new PlaylistRequest();
        trackRequest = new TrackRequest();
        playlist = new Playlist();
        token = "0892-bva2-he7d";
    }

    @Test
    public void requestPlaylistsReturnsOKTest() throws UnauthorizedUserException {
        PlaylistResponse expected = new PlaylistResponse(new ArrayList<>(), 0);
        Mockito.when(playlistService.getPlaylists(token, false)).thenReturn(new ArrayList<>());
        Response actual = playlistController.requestPlaylists(token);
        int expectedLength = 0;
        int actualLength = expected.getLength();
        Assert.assertEquals(expectedLength, actualLength);
        assertEquals(Response.ok(expected).build().getStatus(), actual.getStatus());
    }

    @Test
    public void getTracksFromPlaylistReturnsOKTest() throws UnauthorizedUserException {
        List<Track> tracks = new ArrayList<>();
        TrackResponse response = new TrackResponse(new ArrayList<>());
        Mockito.when(trackService.getTracksFromPlaylist(1, token, false)).thenReturn(tracks);
        Response actual = playlistController.getTracksFromPlaylist(token, 1);
        Assert.assertEquals(Response.ok(response).build().getStatus(), actual.getStatus());
    }

    @Test
    public void addTrackToPlaylistServiceCallTest() throws UnauthorizedUserException {
        trackService.addTrackToPlaylist(1, 1, true, token);
        Mockito.verify(trackService).addTrackToPlaylist(1, 1, true, token);
    }

    @Test
    public void addTrackToPlaylistReturnsCreatedTest() throws UnauthorizedUserException {
        trackRequest.setId(1);
        trackRequest.setTitle("Ocean and a rock");
        trackRequest.setPerformer("Lisa Hannigan");
        trackRequest.setDuration(337);
        trackRequest.setAlbum("Sea sew");
        trackRequest.setPublicationDate(null);
        trackRequest.setOfflineAvailable(false);
        trackRequest.setDescription(null);

        List<Track> tracks = new ArrayList<>();
        Track track = new Track();
        track.setId(1);
        track.setTitle("Ocean and a rock");
        track.setPerformer("Lisa Hannigan");
        track.setDuration(337);
        track.setAlbum("Sea sew");
        track.setPublicationDate(null);
        track.setOfflineAvailable(false);
        track.setDescription(null);
        tracks.add(track);

        TrackResponse expected = new TrackResponse(tracks);
        Mockito.when(trackService.addTrackToPlaylist(1, 1, false, token)).thenReturn(tracks);
        Response actual = playlistController.addTrackToPlaylist(token, 1, trackRequest);
        Assert.assertEquals(Response.status(201).entity(expected).build().getStatus(), actual.getStatus());
    }

    @Test
    public void updatePlaylistServiceCallTest() throws UnauthorizedUserException {
        playlistService.updatePlaylist(1, token, "One");
        Mockito.verify(playlistService).updatePlaylist(1, token, "One");
    }

    @Test
    public void renamingNewPlaylistReturnsCreatedTest() throws UnauthorizedUserException {
        playlistRequest.setId(1);
        playlistRequest.setName("Ocean and a rock");
        playlistRequest.setOwner(true);
        playlistRequest.setTracks(new int[]{1, 2});

        Playlist playlist = new Playlist();
        playlist.setId(1);
        playlist.setName("Ocean and a rock");
        playlist.setOwner(true);
        playlist.setTracks(new int[]{1, 2});

        List<Playlist> playlists = new ArrayList<>();
        playlists.add(playlist);
        PlaylistResponse expected = new PlaylistResponse(playlists, 0);
        Mockito.when(playlistService.updatePlaylist(1, token, "Rock")).thenReturn(playlists);
        Response actual = playlistController.updatePlaylist(token, playlistRequest);
        Assert.assertEquals(Response.status(201).entity(expected).build().getStatus(), actual.getStatus());
    }

    @Test
    public void deletePlaylistServiceCallTest() throws UnauthorizedUserException {
        playlistService.deletePlaylist(1, token);
        Mockito.verify(playlistService).deletePlaylist(1, token);
    }

    @Test
    public void deletePlaylistReturnsOkTest() throws UnauthorizedUserException {
        List<Playlist> playlists = new ArrayList<>();
        PlaylistResponse expected = new PlaylistResponse(new ArrayList<>(), 0);
        Mockito.when(playlistService.deletePlaylist(1, token)).thenReturn(playlists);
        Response actual = playlistController.deletePlaylist(token, 1);
        Assert.assertEquals(Response.ok(expected).build().getStatus(), actual.getStatus());
    }

    @Test
    public void removeTrackFromPlaylistServiceCallTest() throws UnauthorizedUserException {
        trackService.removeTrackFromPlaylist(1, 1, token);
        Mockito.verify(trackService).removeTrackFromPlaylist(1, 1, token);
    }

    @Test
    public void removeTrackFromPlaylistReturnsOkTest() throws UnauthorizedUserException {
        List<Track> tracks = new ArrayList<>();
        TrackResponse response = new TrackResponse(new ArrayList<>());
        Mockito.when(trackService.removeTrackFromPlaylist(1, 1, token)).thenReturn(tracks);
        Response actual = playlistController.removeTrackFromPlaylist(token, 1, 1);
        Assert.assertEquals(Response.ok(response).build().getStatus(), actual.getStatus());
    }

    @Test
    public void addNewPlaylistServiceCallTest() throws UnauthorizedUserException {
        playlistService.addNewPlaylist(token, "One");
        Mockito.verify(playlistService).addNewPlaylist(token, "One");
    }


}
