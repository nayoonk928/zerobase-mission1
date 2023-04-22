package DAO;
/*
    @author Nayoon
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class BookmarkDAO {
    private SqliteConnector connector;

    public BookmarkDAO() {
        connector = new SqliteConnector();
        connector.connect();
    }

//    public void insertBookmark(String bmName, String wifiName, String regiDttm) throws SQLException {
//        connector.connect();
//        Connection conn = connector.getConnection();
//        PreparedStatement pstmt = null;
//        String query = "INSERT INTO bookmark (BM_ID, BMG_NM, X_SWIFI_MAIN_NM, BM_REGI_DTTM) VALUES (?, ?, ?, ?)";
//
//        try {
//            // 만약 bookmark 테이블이 없으면 생성
//            if (!connector.checkTableExists("bookmark")) {
//                String createTableQuery = "CREATE TABLE bookmark (BM_ID INTEGER PRIMARY KEY AUTOINCREMENT, BMG_NM TEXT, X_SWIFI_MAIN_NM TEXT, BM_REGI_DTTM TEXT)";
//                Statement createStmt = conn.createStatement();
//                createStmt.execute(createTableQuery);
//            }
//
//            // PreparedStatement 를 이용해 새로운 북마크 정보를 데이터베이스에 저장
//            pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
//            pstmt.setString(1, bmName);
//            pstmt.setString(2, wifiName);
//            pstmt.setString(3, regiDttm);
//            pstmt.executeUpdate();
//
//            // 생성된 BM_ID 값 구하기
//
//        }
//    }
}
