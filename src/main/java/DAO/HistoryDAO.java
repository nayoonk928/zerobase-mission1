package DAO;
/*
    @author Nayoon
 */
import DTO.HistoryDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryDAO {
    private SqliteConnector connector;

    public HistoryDAO() {
        connector = new SqliteConnector();
        connector.connect();
    }

    public void createHistory() throws SQLException {
        connector.connect();
        Connection conn = connector.getConnection();
        String createTableQuery = "CREATE TABLE wifiHistory " +
                "(HIS_NO INTEGER PRIMARY KEY AUTOINCREMENT, LAT TEXT, LNT TEXT, LKUP_DTTM TEXT)";
        Statement createStmt = conn.createStatement();
        createStmt.execute(createTableQuery);
    }

    public void insertHistory(double myLat, double myLnt, String lkupDttm) throws SQLException {
        connector.connect();
        Connection conn = connector.getConnection();
        PreparedStatement pstmt = null;
        String query = "INSERT INTO wifiHistory (LAT, LNT, LKUP_DTTM) VALUES (?, ?, ?)";

        try {
            // 만약 wifiHistory 테이블이 없으면 생성
            if (!connector.checkTableExists("wifiHistory")) {
                createHistory();
            }

            // PreparedStatement 를 이용해 새로운 히스토리 정보를 데이터베이스에 저장
            pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, Double.toString(myLat));
            pstmt.setString(2, Double.toString(myLnt));
            pstmt.setString(3, lkupDttm);
            pstmt.executeUpdate();

            // 생성된 HIS_NO 값 구하기
            ResultSet rs = pstmt.getGeneratedKeys();
            int hisNo = 0;
            if (rs.next()) {
                hisNo = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }
    }

    public List<HistoryDTO> getAllHistory() throws SQLException {
        connector.connect();
        Connection conn = connector.getConnection();
        PreparedStatement pstmt = null;
        String query = "SELECT * FROM wifiHistory ORDER BY HIS_NO DESC;";
        List<HistoryDTO> histories = new ArrayList<>();

        try {
            // 만약 wifiHistory 테이블이 없으면 생성
            if (!connector.checkTableExists("wifiHistory")) {
                createHistory();
            }

            pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                HistoryDTO historyDTO = new HistoryDTO();

                int hisNo = rs.getInt("HIS_NO");
                String lat = rs.getString("LAT");
                String lnt = rs.getString("LNT");
                String lkupDttm = rs.getString("LKUP_DTTM");

                historyDTO.setHIS_NO(hisNo);
                historyDTO.setLAT(lat);
                historyDTO.setLNT(lnt);
                historyDTO.setLKUP_DTTM(lkupDttm);
                histories.add(historyDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }

        return histories;
    }

    public void deleteHistory(int hisNo) throws SQLException {
        connector.connect();
        Connection conn = connector.getConnection();
        PreparedStatement pstmt = null;
        String query = "DELETE FROM wifiHistory WHERE HIS_NO=?";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, hisNo);
            pstmt.executeUpdate();
        } finally {
            // 연결 및 자원 해제
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
}
