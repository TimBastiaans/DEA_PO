package me.tim.oose.dea.datasource.dao;

import me.tim.oose.dea.exceptions.DaoException;
import me.tim.oose.dea.mo.Playlist;

import java.util.List;

public interface IPlaylistDao {
    List<Playlist> getPlaylists(String token) throws DaoException;

    void deletePlaylistByToken(int id, String token) throws DaoException;

    void addNewPlaylistByToken(String token, String name) throws DaoException;

    void updatePlaylistByToken(int id, String token, String name) throws DaoException;
}
