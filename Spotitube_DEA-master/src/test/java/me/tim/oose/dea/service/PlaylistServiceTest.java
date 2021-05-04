package me.tim.oose.dea.service;

import me.tim.oose.dea.mo.Playlist;
import me.tim.oose.dea.datasource.dao.PlaylistDao;
import me.tim.oose.dea.exceptions.UnauthorizedUserException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PlaylistServiceTest {

    @InjectMocks
    private PlaylistService playlistService;

    @Mock
    private PlaylistDao playlistDAO;

    @Mock
    LoginService loginService;

    @Test
    public void getPlaylistsReturnsEmptyListTest() throws UnauthorizedUserException {
        List<Playlist> expected = new ArrayList<>();
        Mockito.when(playlistDAO.getPlaylists("0892-bva2-he7d")).thenReturn(new ArrayList<>());
        Mockito.when(loginService.authorizeByToken("0892-bva2-he7d")).thenReturn(true);
        List<Playlist> actual = playlistService.getPlaylists("0892-bva2-he7d", false);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addNewPlaylistByTokenReturnsEmptyListTest() throws UnauthorizedUserException {
        List<Playlist> expected = new ArrayList<>();
        doNothing().when(playlistDAO).addNewPlaylistByToken("0892-bva2-he7d", "Ocean and a rock");
        Mockito.when(playlistDAO.getPlaylists("0891-bva2-he7d")).thenReturn(new ArrayList<>());
        List<Playlist> actual = playlistService.addNewPlaylist("0891-bva2-he7d", "Rock");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void updatePlaylistByTokenReturnsEmptyListTest() throws UnauthorizedUserException {
        List<Playlist> expected = new ArrayList<>();
        doNothing().when(playlistDAO).updatePlaylistByToken(1, "0891-bva2-he7d", "Rock");
        Mockito.when(playlistDAO.getPlaylists("0891-bva2-he7d")).thenReturn(new ArrayList<>());
        List<Playlist> actual = playlistService.updatePlaylist(1, "0891-bva2-he7d", "Rock");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void deletePlaylistReturnsEmptyListTest() throws UnauthorizedUserException {
        List<Playlist> expected = new ArrayList<>();
        doNothing().when(playlistDAO).deletePlaylistByToken(1, "0891-bva2-he7d");
        Mockito.when(playlistDAO.getPlaylists("0891-bva2-he7d")).thenReturn(new ArrayList<>());
        List<Playlist> actual = playlistService.deletePlaylist(1, "0891-bva2-he7d");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getTotalLengthTest() {
        Playlist playlist1 = new Playlist();
        Playlist playlist2 = new Playlist();
        playlist1.setLength(123);
        playlist2.setLength(456);
        List<Playlist> playlists = new ArrayList<>();
        playlists.add(playlist1);
        playlists.add(playlist2);
        int expected = 579;
        int actual = playlistService.getTotalLength(playlists);
        Assert.assertEquals(expected, actual);
    }


}
