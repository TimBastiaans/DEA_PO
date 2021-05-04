package me.tim.oose.dea.controllers;

import me.tim.oose.dea.mo.Track;
import me.tim.oose.dea.controllers.dto.track.TrackResponse;
import me.tim.oose.dea.exceptions.UnauthorizedUserException;
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

@RunWith(MockitoJUnitRunner.class)
public class TrackControllerTest {

    @Mock
    private TrackService trackService;

    @InjectMocks
    private TrackController trackController;

    private String token;

    @Before
    public void setup() {
        token = "0892-bva2-he7d";
    }

    @Test
    public void getTracksNotInPlaylistReturnsOkTest() throws UnauthorizedUserException {
        List<Track> tracks = new ArrayList<>();
        TrackResponse expected = new TrackResponse(new ArrayList<>());
        Mockito.when(trackService.getTracksNotInPlaylist(1, token)).thenReturn(tracks);
        Response actual = trackController.getTracksNotInPlaylist(1, token);
        Assert.assertEquals(Response.ok(expected).build().getStatus(), actual.getStatus());
    }
}
