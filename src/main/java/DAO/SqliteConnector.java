package DAO;

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
            System.out.println("SQLite 연결 성공");
        } catch (ClassNotFoundException | SQLException e) {
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
                System.out.println("SQLite 연결 종료");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
