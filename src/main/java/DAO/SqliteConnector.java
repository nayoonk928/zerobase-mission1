package DAO;

/*
    @author Nayoon
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteConnector {
    private Connection connection;
    private String driver = "org.sqlite.JDBC";
    private String location = "jdbc:sqlite:C:/dev/db/sqlite/wifi.db";

    public void connect() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(location);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void disconnect() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
