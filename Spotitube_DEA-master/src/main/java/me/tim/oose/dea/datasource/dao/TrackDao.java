package me.tim.oose.dea.datasource.dao;

import me.tim.oose.dea.exceptions.DaoException;
import me.tim.oose.dea.mo.Track;
import me.tim.oose.dea.datasource.DaoSetup;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrackDao extends DaoSetup implements ITrackDao {


    @Override
    public List<Track> getTracksFromPlaylist(int playListId) throws DaoException {
        try {
            prepareStmt("SELECT t.id, t.title, t.performer, t.duration, t.album, t.playcount, t.publicationDate, t.description, t.offlineAvailable\n" +
                    "FROM tracks t WHERE t.id IN (SELECT trackId FROM tracksInPlaylists WHERE playlistId = ?);");
            preparedStatement.setInt(1, playListId);
            return getTracks();
        } catch (SQLException | IOException e) {
            throw new DaoException(e);
        }
    }


    @Override
    public List<Track> getTracksNotInPlaylist(int playListId) throws DaoException {
        try {
            prepareStmt("SELECT t.id, t.title, t.performer, t.duration, t.album, t.playcount, t.publicationDate, t.description, t.offlineAvailable\n" +
                    "FROM tracks t WHERE t.id NOT IN (SELECT trackId FROM tracksInPlaylists WHERE playlistId = ?);");
            preparedStatement.setInt(1, playListId);
            return getTracks();
        } catch (SQLException | IOException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void addTrackToPlaylist(int trackId, int playlistId) throws DaoException {
        try {
            prepareStmt("INSERT INTO tracksInPlaylists (playlistId, trackId) VALUES (?, ?)");
            preparedStatement.setInt(1, playlistId);
            preparedStatement.setInt(2, trackId);
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void updateTrackAvailability(int trackId, boolean offlineAvailable) throws DaoException {
        try {
            prepareStmt("UPDATE tracks SET offlineAvailable = ? WHERE id = ?");
            preparedStatement.setBoolean(1, offlineAvailable);
            preparedStatement.setInt(2, trackId);
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void removeTrackFromPlaylist(int playlistId, int trackId) throws DaoException {
        try {
            prepareStmt("DELETE FROM tracksInPlaylists WHERE playlistId = ? AND trackId = ?");
            preparedStatement.setInt(1, playlistId);
            preparedStatement.setInt(2, trackId);
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            throw new DaoException(e);
        }
    }


    private List<Track> getTracks() throws SQLException, DaoException {
        List<Track> tracks = new ArrayList<>();
        ResultSet results = getResultSet();
        while (results.next()) {
            tracks.add(mapTrack(results));
        }
        return tracks;
    }

    private Track mapTrack(ResultSet results) throws SQLException, DaoException {
        Track track = new Track();
        track.setId(results.getInt("id"));
        track.setTitle(results.getString("title"));
        track.setPerformer(results.getString("performer"));
        track.setDuration(results.getInt("duration"));
        track.setAlbum(results.getString("album"));
        track.setPlaycount(results.getInt("playcount"));
        track.setPublicationDate(results.getString("publicationDate"));
        track.setDescription(results.getString("description"));
        track.setOfflineAvailable(results.getBoolean("offlineAvailable"));
        return track;
    }


}
