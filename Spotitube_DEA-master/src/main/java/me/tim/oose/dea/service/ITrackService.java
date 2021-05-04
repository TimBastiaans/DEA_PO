package me.tim.oose.dea.service;

import me.tim.oose.dea.mo.Track;
import me.tim.oose.dea.exceptions.UnauthorizedUserException;

import java.util.List;

public interface ITrackService {
    List<Track> getTracksFromPlaylist(int playlistId, String token, boolean authorized) throws UnauthorizedUserException;

    List<Track> getTracksNotInPlaylist(int playlistId, String token) throws UnauthorizedUserException;

    List<Track> addTrackToPlaylist(int playlistId, int trackId, boolean offlineAvailable, String token) throws UnauthorizedUserException;

    List<Track> removeTrackFromPlaylist(int playlistId, int trackId, String token) throws UnauthorizedUserException;
}
