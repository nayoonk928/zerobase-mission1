package Test;

import java.sql.Connection;
import java.sql.SQLException;

import DAO.SqliteConnector;

public class SqliteConnectorTest {
    public static void main(String[] args) {
        SqliteConnector sqliteConnector = new SqliteConnector();
        Connection connection = null;

        try {
            // 연결
            sqliteConnector.connect();
            connection = sqliteConnector.getConnection();
            System.out.println("SQLite database에 연결되었습니다.");

            // 연결 종료
            sqliteConnector.disconnect();
            System.out.println("SQLite database와의 연결이 종료되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 자원 반환
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
