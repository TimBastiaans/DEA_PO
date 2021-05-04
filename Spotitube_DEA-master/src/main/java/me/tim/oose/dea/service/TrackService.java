package me.tim.oose.dea.service;

import me.tim.oose.dea.mo.Track;
import me.tim.oose.dea.datasource.dao.TrackDao;
import me.tim.oose.dea.exceptions.UnauthorizedUserException;

import javax.inject.Inject;
import java.util.List;

public class TrackService implements ITrackService {

    @Inject
    private TrackDao trackDAO;

    @Inject
    private LoginService loginService;

    @Override
    public List<Track> getTracksFromPlaylist(int playlistId, String token, boolean authorized) throws UnauthorizedUserException {
        if (!authorized) {
            loginService.authorizeByToken(token);
        }
        return trackDAO.getTracksFromPlaylist(playlistId);
    }

    @Override
    public List<Track> getTracksNotInPlaylist(int playlistId, String token) throws UnauthorizedUserException {
        loginService.authorizeByToken(token);
        return trackDAO.getTracksNotInPlaylist(playlistId);
    }

    @Override
    public List<Track> addTrackToPlaylist(int playlistId, int trackId, boolean offlineAvailable, String token) throws UnauthorizedUserException {
        loginService.authorizeByToken(token);
        trackDAO.updateTrackAvailability(trackId, offlineAvailable);
        trackDAO.addTrackToPlaylist(trackId, playlistId);
        return this.getTracksFromPlaylist(playlistId, token, true);
    }

    @Override
    public List<Track> removeTrackFromPlaylist(int playlistId, int trackId, String token) throws UnauthorizedUserException {
        loginService.authorizeByToken(token);
        trackDAO.removeTrackFromPlaylist(playlistId, trackId);
        return this.getTracksFromPlaylist(playlistId, token, true);
    }

}
