package me.tim.oose.dea.datasource;

import me.tim.oose.dea.service.DbConnection;

import javax.inject.Inject;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DaoSetup {

    @Inject
    private DbConnection dbConnection;
    private Connection connection;
    protected PreparedStatement preparedStatement;

    protected void prepareStmt(String s) throws SQLException, IOException {
        connection = dbConnection.getInstance().getConnectionTest();
        preparedStatement = connection.prepareStatement(s);
    }

    protected ResultSet getResultSet() throws SQLException {
        return preparedStatement.executeQuery();
    }

    protected void closeConnection() throws SQLException {
        preparedStatement.close();
        connection.close();
    }
}
