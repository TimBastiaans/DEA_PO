package me.tim.oose.dea.datasource.dao;

import me.tim.oose.dea.exceptions.DaoException;
import me.tim.oose.dea.mo.Track;

import java.util.List;

public interface ITrackDao {
    List<Track> getTracksFromPlaylist(int playListId) throws DaoException;

    List<Track> getTracksNotInPlaylist(int playListId) throws DaoException;

    void addTrackToPlaylist(int trackId, int playlistId) throws DaoException;

    void updateTrackAvailability(int trackId, boolean offlineAvailable) throws DaoException;

    void removeTrackFromPlaylist(int playlistId, int trackId) throws DaoException;
}
