package me.tim.oose.dea.datasource;

import me.tim.oose.dea.datasource.dao.IPlaylistDao;
import me.tim.oose.dea.datasource.dao.PlaylistDao;
import me.tim.oose.dea.exceptions.DaoException;
import me.tim.oose.dea.mo.Playlist;
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

public class PlaylistDaoTest {

    private IPlaylistDao playlistDao;

    @Before
    public void setUp() throws Exception {
        Properties properties = new Properties();
        properties.load(getClass().getClassLoader().getResourceAsStream("database.properties"));
        DbConnection.getInstance().setProperties(properties);
        DbConnection.getInstance().getConnectionTest().close();
        Connection conn = DbConnection.getInstance().getConnectionTest();
        RunScript.execute(conn, new FileReader("src/main/resources/spotitubeTestDb.sql"));
        playlistDao = new PlaylistDao();
    }

    @After
    public void closeConn() throws SQLException, IOException {
        Connection conn = DbConnection.getInstance().getConnectionTest();
        conn.createStatement().executeUpdate("DROP DATABASE SpotitubeTest");
        conn.close();
    }

    @Test
    public void getPlaylistsSuccessTest() throws DaoException {
        //init
        String userToken = "0890-bva2-he7d";
        List<Playlist> expected = (new ArrayList<Playlist>() {{
            add(new Playlist(1, "Death metal",0, true));
            add(new Playlist(2, "pop",0, true));
        }});

        //test
        List<Playlist> actual = playlistDao.getPlaylists(userToken);
        //check
        assertEquals(actual.get(0).getId(), expected.get(0).getId());
        assertEquals(actual.get(0).getName(), expected.get(0).getName());
        assertEquals(actual.get(0).getLength(), expected.get(0).getLength());
        assertEquals(actual.get(0).isOwner(), expected.get(0).isOwner());

        assertEquals(actual.get(1).getId(), expected.get(1).getId());
        assertEquals(actual.get(1).getName(), expected.get(1).getName());
        assertEquals(actual.get(1).getLength(), expected.get(1).getLength());
        assertEquals(actual.get(1).isOwner(), expected.get(1).isOwner());

    }
//
    @Test
    public void addPlaylistSuccessTest() throws SQLException, DaoException, IOException {
        //init
        String expected = "House";
        String userToken = "0890-bva2-he7d";
        //test
        playlistDao.addNewPlaylistByToken(userToken, expected);

        //check
        Connection conn = DbConnection.getInstance().getConnectionTest();
        ResultSet added = conn.prepareStatement("SELECT * FROM Spotitube.playlists WHERE id = 3;").executeQuery();
        added.next();
        String actual = added.getString("name");
        assertThat(actual, is(expected));
    }

    @Test
    public void deletePlaylistByTokenSuccessTest() throws SQLException, DaoException, IOException {
        //init
        int playlistId = 2;
        String userToken = "0890-bva2-he7d";
        //test
        playlistDao.deletePlaylistByToken(playlistId,userToken);
        Connection conn = DbConnection.getInstance().getConnectionTest();
        ResultSet deleted = conn.prepareStatement("SELECT * FROM Spotitube.playlists WHERE id = 2;").executeQuery();
        ResultSet notDeleted = conn.createStatement().executeQuery("SELECT * FROM Spotitube.playlists WHERE id = 1;");

        //check
        assertThat(deleted.next(), is(true));
        assertThat(notDeleted.next(), is(true));
    }

    @Test(expected = DaoException.class)
    public void addPlaylistExceptionTest() throws DaoException {
        //test
        playlistDao.addNewPlaylistByToken("0894-bva2-he7d", "Rap");
    }

}
