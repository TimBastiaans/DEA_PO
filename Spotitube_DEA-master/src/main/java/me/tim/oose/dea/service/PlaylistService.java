package me.tim.oose.dea.service;


import me.tim.oose.dea.mo.Playlist;
import me.tim.oose.dea.datasource.dao.PlaylistDao;
import me.tim.oose.dea.exceptions.UnauthorizedUserException;

import javax.inject.Inject;
import java.util.List;

public class PlaylistService implements IPlaylistService {

    public PlaylistService() {

    }

    @Inject
    private PlaylistDao playlistDAO;

    @Inject
    private LoginService loginService;

    @Override
    public List<Playlist> getPlaylists(String token, boolean authorized) throws UnauthorizedUserException {
        if (!authorized) {
            loginService.authorizeByToken(token);
        }
        return playlistDAO.getPlaylists(token);
    }

    @Override
    public List<Playlist> deletePlaylist(int id, String token) throws UnauthorizedUserException {
        playlistDAO.deletePlaylistByToken(id, token);
        return this.getPlaylists(token, true);
    }

    @Override
    public List<Playlist> addNewPlaylist(String token, String name) throws UnauthorizedUserException {
        playlistDAO.addNewPlaylistByToken(token, name);
        return this.getPlaylists(token, true);
    }

    @Override
    public List<Playlist> updatePlaylist(int id, String token, String name) throws UnauthorizedUserException {
        playlistDAO.updatePlaylistByToken(id, token, name);
        return this.getPlaylists(token, true);
    }

    public int getTotalLength(List<Playlist> playlists){
        int length = 0;
        for (Playlist playlist: playlists){
            length+=playlist.getLength();
        }
        return length;
    }


}
