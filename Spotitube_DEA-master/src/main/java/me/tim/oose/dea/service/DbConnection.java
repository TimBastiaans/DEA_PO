package me.tim.oose.dea.service;

import me.tim.oose.dea.exceptions.DatabaseConnectionException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbConnection {
    private Logger logger = Logger.getLogger(getClass().getName());
    private Properties properties;
    private Connection connection;
    private static DbConnection instance;

    private Properties getProperties() throws IOException, DatabaseConnectionException {
        Properties connection = new Properties();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream("database.properties");

        if(input == null){
            throw new DatabaseConnectionException("Required classpath" + classLoader + "is missing");
        }

        connection.load(input);
        return connection;
    }


    private DbConnection() {
        try {
            getProperties();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Can't access property file database.properties", e);
        }
    }

    public synchronized static DbConnection getInstance() {
        if (instance == null) {
            instance = new DbConnection();
        }
        return instance;
    }

    public Connection getConnectionTest() throws IOException, DatabaseConnectionException {
        try {
            if (connection == null || this.connection.isClosed()) {
                Properties connection = getProperties();

                String driver = connection.getProperty("jdbc.driverClassName");
                String url = connection.getProperty("jdbc.url");
                String user = connection.getProperty("jdbc.username");
                String password = connection.getProperty("jdbc.password");

                Class.forName(driver);

                this.connection = DriverManager.getConnection(url, user, password);
            }
        } catch (SQLException | ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.toString(), e);
        }
        return this.connection;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }


}
