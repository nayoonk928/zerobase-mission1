package DAO;

/*
    @author Nayoon
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;

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

    public boolean checkTableExists(String tableName) throws SQLException {
        Connection conn = getConnection();
        ResultSet tables = conn.getMetaData().getTables(null, null, tableName, null);
        boolean tableExists = tables.next();
        tables.close();
        return tableExists;
    }
}
