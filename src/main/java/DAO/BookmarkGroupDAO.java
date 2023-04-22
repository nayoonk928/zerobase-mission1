package DAO;
/*
    @author Nayoon
 */
import DTO.BookmarkGroupDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookmarkGroupDAO {
    private SqliteConnector connector;

    public BookmarkGroupDAO() {
        connector = new SqliteConnector();
        connector.connect();
    }

    public boolean insertBookmarkGroup(String bmgName, int order, String createDttm) throws SQLException {
        connector.connect();
        Connection conn = connector.getConnection();
        PreparedStatement pstmt = null;
        String query = "INSERT INTO bookmarkGroup (BMG_NM, BMG_ORDER, BMG_CR_DTTM, BMG_UP_DTTM) " +
                "VALUES (?, ?, ?, ?)";

        try {
            // 만약 bookmarkGroup 테이블이 없으면 생성
            if (!connector.checkTableExists("bookmarkGroup")) {
                String createTableQuery = "CREATE TABLE bookmarkGroup " +
                        "(BMG_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "BMG_NM TEXT, " +
                        "BMG_ORDER INTEGER, " +
                        "BMG_CR_DTTM TEXT, " +
                        "BMG_UP_DTTM TEXT)";
                Statement createStmt = conn.createStatement();
                createStmt.execute(createTableQuery);
            }

            // 같은 BMG_ORDER 값이 존재하는지 확인
            String checkQuery = "SELECT BMG_ORDER FROM bookmarkGroup WHERE BMG_ORDER = ?";
            pstmt = conn.prepareStatement(checkQuery);
            pstmt.setInt(1, order);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                // 이미 같은 순서의 북마크 그룹이 존재할 경우
                return false;
            }

            // PreparedStatement 를 이용해 새로운 북마크 정보를 데이터베이스에 저장
            pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, bmgName);
            pstmt.setInt(2, order);
            pstmt.setString(3, createDttm);
            pstmt.setString(4, "");
            pstmt.executeUpdate();

            // 생성된 BM_ID 값 구하기
            rs = pstmt.getGeneratedKeys();
            int bmId = 0;
            if (rs.next()) {
                bmId = rs.getInt(1);
            }

            // 성공적으로 북마크 그룹을 추가한 경우
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }
        return false;
    }

    public List<BookmarkGroupDTO> getBookmarkGroup() throws SQLException {
        connector.connect();
        Connection conn = connector.getConnection();
        PreparedStatement pstmt = null;
        String query = "SELECT * FROM bookmarkGroup ORDER BY BMG_ORDER;";
        List<BookmarkGroupDTO> bookmarkGroups = new ArrayList<>();

        try {
            pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                BookmarkGroupDTO bmgDTO = new BookmarkGroupDTO();

                int bmgId = rs.getInt("BMG_ID");
                String bmgNm = rs.getString("BMG_NM");
                int bmgOrder = rs.getInt("BMG_ORDER");
                String bmgCrDttm = rs.getString("BMG_CR_DTTM");
                String bmgUpDttm = rs.getString("BMG_UP_DTTM");

                bmgDTO.setBMG_ID(bmgId);
                bmgDTO.setBMG_NM(bmgNm);
                bmgDTO.setBMG_ORDER(bmgOrder);
                bmgDTO.setBMG_CR_DTTM(bmgCrDttm);
                bmgDTO.setBMG_UP_DTTM(bmgUpDttm);
                bookmarkGroups.add(bmgDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }

        return bookmarkGroups;
    }

    public BookmarkGroupDTO getBookmarkGroupById(int bmgId) throws SQLException{
        connector.connect();
        Connection conn = connector.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = "SELECT BMG_ID, BMG_NM, BMG_ORDER, BMG_CR_DTTM, BMG_UP_DTTM " +
                "FROM bookmarkGroup WHERE BMG_ID=?";

        BookmarkGroupDTO bmgDTO = new BookmarkGroupDTO();
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, bmgId);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                bmgDTO.setBMG_ID(rs.getInt("BMG_ID"));
                bmgDTO.setBMG_NM(rs.getString("BMG_NM"));
                bmgDTO.setBMG_ORDER(rs.getInt("BMG_ORDER"));
                bmgDTO.setBMG_CR_DTTM(rs.getString("BMG_CR_DTTM"));
                bmgDTO.setBMG_UP_DTTM(rs.getString("BMG_UP_DTTM"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }

        return bmgDTO;
    }

    public boolean updateBookmarkGroup(int bmgId, String bmgName, int order, String updateDttm) throws SQLException {
        connector.connect();
        Connection conn = connector.getConnection();
        PreparedStatement pstmt = null;
        String query = "UPDATE bookmarkGroup SET BMG_NM = ?, BMG_ORDER = ?, BMG_UP_DTTM = ? " + // BMG_NM, BMG_ORDER 을 변경하는 쿼리
                "WHERE BMG_ID = ?";

        try {
            // 같은 BMG_ORDER 값이 존재하는지 확인
            String checkQuery = "SELECT BMG_ORDER FROM bookmarkGroup WHERE BMG_ORDER = ? AND BMG_ID != ?";
            pstmt = conn.prepareStatement(checkQuery);
            pstmt.setInt(1, order);
            pstmt.setInt(2, bmgId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                // 이미 같은 순서의 북마크 그룹이 존재할 경우
                return false;
            }

            // PreparedStatement 를 이용해 새로운 북마크 정보를 데이터베이스에 저장
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, bmgName);
            pstmt.setInt(2, order);
            pstmt.setString(3, updateDttm);
            pstmt.setInt(4, bmgId);
            pstmt.executeUpdate();

            // 성공적으로 북마크 그룹을 수정한 경우
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }
        return false;
    }

    public void deleteBookmarkGroup(int bmgId) throws SQLException {
        connector.connect();
        Connection conn = connector.getConnection();
        PreparedStatement pstmt = null;
        String query = "DELETE FROM bookmarkGroup WHERE BMG_ID=?";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, bmgId);
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
