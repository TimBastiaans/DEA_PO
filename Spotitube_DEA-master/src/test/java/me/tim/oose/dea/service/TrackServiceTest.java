package me.tim.oose.dea.service;

import me.tim.oose.dea.mo.Track;
import me.tim.oose.dea.datasource.dao.TrackDao;
import me.tim.oose.dea.exceptions.UnauthorizedUserException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;

@RunWith(MockitoJUnitRunner.class)
public class TrackServiceTest {

    @InjectMocks
    private TrackService trackService;

    @Mock
    private TrackDao trackDAO;

    @Mock
    private LoginService loginService;

    @Test
    public void testGetAllTracksInPlaylistReturnsEmptyList() throws UnauthorizedUserException {
        List<Track> expected = new ArrayList<>();
        Mockito.when(trackDAO.getTracksFromPlaylist(1)).thenReturn(new ArrayList<>());
        Mockito.when(loginService.authorizeByToken("0892-bva2-he7d")).thenReturn(true);
        List<Track> actual = trackService.getTracksFromPlaylist(1, "0892-bva2-he7d", false);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetAllTracksNotInPlaylistReturnsEmptyList() throws UnauthorizedUserException {
        List<Track> expected = new ArrayList<>();
        Mockito.when(trackDAO.getTracksNotInPlaylist(1)).thenReturn(new ArrayList<>());
        Mockito.when(loginService.authorizeByToken("0892-bva2-he7d")).thenReturn(true);
        List<Track> actual = trackService.getTracksNotInPlaylist(1, "0892-bva2-he7d");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testAddTrackToPlaylistReturnsEmptyList() throws UnauthorizedUserException {
        List<Track> expected = new ArrayList<>();
        Mockito.when(trackDAO.getTracksFromPlaylist(1)).thenReturn(new ArrayList<>());
        Mockito.when(loginService.authorizeByToken("0892-bva2-he7d")).thenReturn(true);
        doNothing().when(trackDAO).addTrackToPlaylist(1, 1);
        doNothing().when(trackDAO).updateTrackAvailability(1, true);
        List<Track> actual = trackService.addTrackToPlaylist(1, 1, true, "0892-bva2-he7d");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testRemoveTrackFromPlaylistReturnsEmptyList() throws UnauthorizedUserException {
        List<Track> expected = new ArrayList<>();
        Mockito.when(trackDAO.getTracksFromPlaylist(1)).thenReturn(new ArrayList<>());
        Mockito.when(loginService.authorizeByToken("0892-bva2-he7d")).thenReturn(true);
        doNothing().when(trackDAO).removeTrackFromPlaylist(1, 1);
        List<Track> actual = trackService.removeTrackFromPlaylist(1, 1, "0892-bva2-he7d");
        Assert.assertEquals(expected, actual);
    }
}
