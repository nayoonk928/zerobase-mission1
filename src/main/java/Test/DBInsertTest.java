package Test;

/*
    @author Nayoon
 */

import API.ApiConfig;
import API.ApiClient;
import DAO.SqliteConnector;
import DAO.WifiDAO;

import java.io.IOException;
import java.sql.SQLException;

public class DBInsertTest {
    public static void main(String[] args) {
        // API 클라이언트 생성
        ApiClient apiClient = ApiClient.getInstance(ApiConfig.DOMAIN, ApiConfig.KEY, ApiConfig.TYPE, ApiConfig.SERVICE);

        // SQLite 연결
        SqliteConnector connector = new SqliteConnector();
        connector.connect();

        // WifiDAO 생성
        WifiDAO wifiDAO = new WifiDAO(apiClient, connector);

        try {
            wifiDAO.insertWifiToDB();
            System.out.println("Insertion complete.");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
