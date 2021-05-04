package me.tim.oose.dea.datasource.dao;

import me.tim.oose.dea.exceptions.DaoException;
import me.tim.oose.dea.mo.Playlist;
import me.tim.oose.dea.datasource.DaoSetup;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDao extends DaoSetup implements IPlaylistDao {

    @Override
    public List<Playlist> getPlaylists(String token) throws DaoException {
        try {
            prepareStmt("SELECT id, name ,owner_token, length FROM playlists");
            return (getPlaylist(token));
        } catch (SQLException | IOException e) {
            throw new DaoException(e);
        }
    }

    private List<Playlist> getPlaylist(String token) throws SQLException, DaoException {
        List<Playlist> playlists = new ArrayList<>();

        ResultSet resultSet = getResultSet();
        while (resultSet.next()) {
            playlists.add(mapPlaylist(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("owner_token"), token, resultSet.getInt("length")));
        }
        return playlists;
    }

    private Playlist mapPlaylist(int id, String name, String owner, String token, int length) throws DaoException {
        Playlist playlist = new Playlist();
        playlist.setId(id);
        playlist.setName(name);
        playlist.setLength(length);
        playlist.setTracks(getTracks(id));
        if (token.equals(owner)) {
            playlist.setOwner(true);
        } else {
            playlist.setOwner(false);
        }
        return playlist;
    }

    private int[] getTracks(int playlistId) throws DaoException {
        try {
            prepareStmt("SELECT trackId FROM tracksInPlaylists WHERE playlistId = ?");
            preparedStatement.setInt(1, playlistId);
            return getTrackIdFromPlaylist();
        } catch (SQLException | IOException e) {
            throw new DaoException(e);
        }
    }

    private int[] getTrackIdFromPlaylist() throws SQLException ,DaoException {
        List<Integer> trackids = new ArrayList<>();

        ResultSet resultSet = getResultSet();
        while (resultSet.next()) {
            trackids.add(resultSet.getInt("trackid"));
        }
        return trackids.stream().mapToInt(i -> i).toArray();
    }

    @Override
    public void deletePlaylistByToken(int id, String token) throws DaoException {
        try {
            prepareStmt("DELETE FROM playlists WHERE id = ? AND owner_token = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, token);
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void addNewPlaylistByToken(String token, String name) throws DaoException {
        try {
            prepareStmt("INSERT INTO playlists (name, owner_token, length) VALUES (?, ?, 0)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, token);
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void updatePlaylistByToken(int id, String token, String name) throws DaoException {

        try {
            prepareStmt("UPDATE playlists SET name = ? WHERE id = ? AND owner_token = ?");
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.setString(3, token);
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            throw new DaoException(e);
        }
    }


}







