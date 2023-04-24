package DAO;
/*
    @author Nayoon
 */
import DTO.BookmarkDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookmarkDAO {
    private SqliteConnector connector;

    public BookmarkDAO() {
        connector = new SqliteConnector();
        connector.connect();
    }

    public void insertBookmark(int bmgId, String mgrNo, String regiDttm) throws SQLException {
        connector.connect();
        Connection conn = connector.getConnection();
        PreparedStatement pstmt = null;
        String query = "INSERT INTO bookmark (BMG_ID, X_SWIFI_MGR_NO, BM_REGI_DTTM) VALUES (?, ?, ?)";

        try {
            // 만약 bookmark 테이블이 없으면 생성
            if (!connector.checkTableExists("bookmark")) {
                String createTableQuery = "CREATE TABLE bookmark " +
                        "(BM_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "BMG_ID INTEGER, " +
                        "X_SWIFI_MGR_NO TEXT, " +
                        "BM_REGI_DTTM TEXT, " +
                        "FOREIGN KEY (BMG_ID) REFERENCES bookmarkGroup(BMG_ID), " +
                        "FOREIGN KEY (X_SWIFI_MGR_NO) REFERENCES wifiInfo(X_SWIFI_MGR_NO))";
                Statement createStmt = conn.createStatement();
                createStmt.executeUpdate(createTableQuery);
            }

            // PreparedStatement 를 이용해 새로운 북마크 정보를 데이터베이스에 저장
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, bmgId);
            pstmt.setString(2, mgrNo);
            pstmt.setString(3, regiDttm);
            pstmt.executeUpdate();

            // 생성된 BM_ID 값 구하기
            ResultSet rs = pstmt.getGeneratedKeys();
            int bmId = 0;
            if (rs.next()) {
                bmId = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }
    }

    public List<BookmarkDTO> getBookmark() throws SQLException {
        connector.connect();
        Connection conn = connector.getConnection();
        PreparedStatement pstmt = null;
        String query = "SELECT * FROM bookmark b " +
                "INNER JOIN wifiInfo w ON b.X_SWIFI_MGR_NO = w.X_SWIFI_MGR_NO " +
                "INNER JOIN bookmarkGroup bg ON b.BMG_ID = bg.BMG_ID";
        List<BookmarkDTO> bookmarks = new ArrayList<>();

        try {
            pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                BookmarkDTO bmDTO = new BookmarkDTO();

                int bmId = rs.getInt("BM_ID");
                int bmgId = rs.getInt("BMG_ID");
                String bmgNm = rs.getString("BMG_NM");
                String wifiName = rs.getString("X_SWIFI_MAIN_NM");
                String bmRegiDttm = rs.getString("BM_REGI_DTTM");

                bmDTO.setBM_ID(bmId);
                bmDTO.setBMG_ID(bmgId);
                bmDTO.setBMG_NM(bmgNm);
                bmDTO.setX_SWIFI_MAIN_NM(wifiName);
                bmDTO.setBM_REGI_DTTM(bmRegiDttm);
                bookmarks.add(bmDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }

        return bookmarks;
    }

    public BookmarkDTO getBookmarkById(int bmId) throws SQLException {
        connector.connect();
        Connection conn = connector.getConnection();
        PreparedStatement pstmt = null;
        String query = "SELECT * FROM bookmark b " +
                "INNER JOIN wifiInfo w ON b.X_SWIFI_MGR_NO = w.X_SWIFI_MGR_NO " +
                "INNER JOIN bookmarkGroup bg ON b.BMG_ID = bg.BMG_ID " +
                "WHERE BM_ID=?";

        BookmarkDTO bmDTO = new BookmarkDTO();
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, bmId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String bmgNm = rs.getString("BMG_NM");
                String wifiName = rs.getString("X_SWIFI_MAIN_NM");
                String bmRegiDttm = rs.getString("BM_REGI_DTTM");

                bmDTO.setBM_ID(bmId);
                bmDTO.setBMG_NM(bmgNm);
                bmDTO.setX_SWIFI_MAIN_NM(wifiName);
                bmDTO.setBM_REGI_DTTM(bmRegiDttm);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }

        return bmDTO;
    }

    public void deleteBookmark(int bmId) throws SQLException {
        connector.connect();
        Connection conn = connector.getConnection();
        PreparedStatement pstmt = null;
        String query = "DELETE FROM bookmark WHERE BM_ID=?";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, bmId);
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
