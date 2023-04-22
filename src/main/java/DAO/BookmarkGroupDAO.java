package DAO;
/*
    @author Nayoon
 */
import java.sql.*;

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
        String query = "INSERT INTO bookmarkGroup (BMG_NM, BMG_ORDER, BMG_CR_DTTM) " +
                "VALUES (?, ?, ?)";

        try {
            // 만약 bookmarkGroup 테이블이 없으면 생성
            if (!connector.checkTableExists("bookmarkGroup")) {
                String createTableQuery = "CREATE TABLE bookmarkGroup " +
                        "(BMG_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "BMG_NM TEXT, " +
                        "BMG_ORDER INTEGER, " +
                        "BMG_CR_DTTM TEXT)";
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
}
