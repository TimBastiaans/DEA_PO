package me.tim.oose.dea.datasource;

import me.tim.oose.dea.datasource.dao.ITrackDao;
import me.tim.oose.dea.datasource.dao.TrackDao;
import me.tim.oose.dea.exceptions.DaoException;
import me.tim.oose.dea.mo.Track;
import me.tim.oose.dea.service.DbConnection;
import org.h2.tools.RunScript;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TrackDaoTest {

    private ITrackDao trackDao;

    @Before
    public void setUp() throws Exception {
        Properties properties = new Properties();
        properties.load(getClass().getClassLoader().getResourceAsStream("database.properties"));
        DbConnection.getInstance().setProperties(properties);
        Connection conn = DbConnection.getInstance().getConnectionTest();

        RunScript.execute(conn, new FileReader("src/main/resources/spotitubeTestDb.sql"));
        trackDao = new TrackDao();
    }

    @After
    public void closeConn() throws SQLException, IOException {
        Connection conn = DbConnection.getInstance().getConnectionTest();
        conn.createStatement().executeUpdate("DROP DATABASE SpotitubeTest");
        conn.close();
    }

    @Test
    public void getTracksFromPlaylistSuccessTest() throws DaoException {
        //init
        List<Track> expected = (new ArrayList<Track>() {{
            add(new Track(1, "Ocean and a rock", "Lisa Hannigan", 337,"Sea sew", 0, null, null, false));
            add(new Track(2, "So Long, Marianne", "Leonard Cohen", 546, "Songs of Leonard Cohen", 0 ,null, null,  false));
        }});

        //test
        List<Track> actual = trackDao.getTracksFromPlaylist(1);

        //check
        assertEquals(actual.get(0).getId(), expected.get(0).getId());
        assertEquals(actual.get(0).getTitle(), expected.get(0).getTitle());
        assertEquals(actual.get(0).getPerformer(), expected.get(0).getPerformer());
        assertEquals(actual.get(0).getDuration(), expected.get(0).getDuration());
        assertEquals(actual.get(0).getPlaycount(), expected.get(0).getPlaycount());
        assertEquals(actual.get(0).getDescription(), expected.get(0).getDescription());
        assertEquals(actual.get(0).isOfflineAvailable(), expected.get(0).isOfflineAvailable());

        assertEquals(actual.get(1).getId(), expected.get(1).getId());
        assertEquals(actual.get(1).getTitle(), expected.get(1).getTitle());
        assertEquals(actual.get(1).getPerformer(), expected.get(1).getPerformer());
        assertEquals(actual.get(1).getDuration(), expected.get(1).getDuration());
        assertEquals(actual.get(1).getAlbum(), expected.get(1).getAlbum());
        assertEquals(actual.get(1).getPlaycount(), expected.get(1).getPlaycount());
        assertEquals(actual.get(1).isOfflineAvailable(), expected.get(1).isOfflineAvailable());
    }

    @Test
    public void getTracksNotInPlaylistSuccessTest() throws DaoException {
        //init
        List<Track> expected = (new ArrayList<Track>() {{
            add(new Track(3, "One", "Metallica", 423,"Sea sew", 37, "2001-11-01", "Long version", true));
        }});
        //test
        List<Track> actual = trackDao.getTracksNotInPlaylist(1);

        //check
        assertEquals(actual.get(0).getId(), expected.get(0).getId());
        assertEquals(actual.get(0).getTitle(), expected.get(0).getTitle());
        assertEquals(actual.get(0).getPerformer(), expected.get(0).getPerformer());
        assertEquals(actual.get(0).getDuration(), expected.get(0).getDuration());
        assertEquals(actual.get(0).getPlaycount(), expected.get(0).getPlaycount());
        assertEquals(actual.get(0).getDescription(), expected.get(0).getDescription());
        assertEquals(actual.get(0).isOfflineAvailable(), expected.get(0).isOfflineAvailable());
        assertEquals(actual.get(0).isOfflineAvailable(), expected.get(0).isOfflineAvailable());
    }

    @Test
    public void removeTrackToPlaylistSuccessTest() throws SQLException, DaoException, IOException {
        //test
        trackDao.removeTrackFromPlaylist(1, 2);

        //check
        Connection conn = DbConnection.getInstance().getConnectionTest();
        ResultSet added = conn.prepareStatement("SELECT * FROM Spotitube.tracksInPlaylists WHERE playlistId = 1 AND trackId = 2;").executeQuery();

        assertThat(added.next(), is(true));
    }

    @Test
    public void addTrackToPlaylistSuccessTest() throws SQLException, DaoException, IOException {
        //test
        trackDao.addTrackToPlaylist(3, 1);

        //check
        Connection conn = DbConnection.getInstance().getConnectionTest();
        ResultSet added = conn.prepareStatement("SELECT * FROM Spotitube.tracksInPlaylists WHERE playlistId = 1 AND trackId = 3;").executeQuery();

        assertThat(added.next(), is(false));
    }

    @Test
    public void updateTrackAvailabilitySuccessTest() throws SQLException, DaoException, IOException {
        //test
        trackDao.updateTrackAvailability(1, true);

        //check
        Connection conn = DbConnection.getInstance().getConnectionTest();
        ResultSet added = conn.prepareStatement("SELECT * FROM Spotitube.tracks WHERE id = 3 AND offlineAvailable = true;").executeQuery();

        assertThat(added.next(), is(true));
    }

    @Test(expected = DaoException.class)
    public void addTrackToPlaylistExceptionTest() throws DaoException {
        //test
        trackDao.addTrackToPlaylist(5, 3);
    }

}
