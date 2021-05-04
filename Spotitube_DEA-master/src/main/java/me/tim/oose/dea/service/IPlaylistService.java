package me.tim.oose.dea.service;

import me.tim.oose.dea.mo.Playlist;
import me.tim.oose.dea.exceptions.UnauthorizedUserException;

import java.util.List;

public interface IPlaylistService {
    List<Playlist> getPlaylists(String token, boolean authorized) throws UnauthorizedUserException;

    List<Playlist> deletePlaylist(int id, String token) throws UnauthorizedUserException;

    List<Playlist> addNewPlaylist(String token, String name) throws UnauthorizedUserException;

    List<Playlist> updatePlaylist(int id, String token, String name) throws UnauthorizedUserException;
}
