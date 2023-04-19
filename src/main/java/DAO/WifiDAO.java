package DAO;

/*
    @author Nayoon
 */

import API.ApiClient;
import API.ApiService;
import DTO.WifiDTO;
import DTO.WifiResponse;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WifiDAO {
    private SqliteConnector connector;
    private ApiClient apiClient;

    public WifiDAO() {
        connector = new SqliteConnector();
        connector.connect();
    }

    public WifiDAO(ApiClient apiClient, SqliteConnector connector) {
        this.apiClient = apiClient;
        this.connector = connector;
    }

    // 모든 api 데이터 db에 저장
    public void insertWifiToDB() throws IOException, SQLException {
        connector.connect();
        Connection conn = connector.getConnection();
        PreparedStatement pstmt = null;
        String query = "INSERT INTO wifiInfo (X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, " +
                "X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, " +
                "X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            pstmt = conn.prepareStatement(query);

            int pageSize = 1000;
            int startIdx = 1;
            int endIdx = pageSize;

            ApiService apiService = new ApiService(apiClient);
            WifiResponse wifiResponse = apiService.getWifiData(startIdx, endIdx);
            int totalCount = 0;

            if (wifiResponse != null) {
                totalCount = wifiResponse.getTotalCount();
                System.out.println(totalCount);
            } else {
                System.out.println("WifiResponse is null.");
            }

            while (endIdx <= totalCount) {
                startIdx = endIdx + 1;
                endIdx = Math.min(startIdx + pageSize - 1, totalCount);

                if (startIdx > endIdx) {
                    break;
                }

                wifiResponse = apiService.getWifiData(startIdx, endIdx);

                if (wifiResponse != null) {
                    List<WifiDTO> wifiDTOList = wifiResponse.getWifiDTOList();
                    if (wifiDTOList == null || wifiDTOList.isEmpty()) {
                        break;
                    }

                    for (WifiDTO wifiDTO : wifiDTOList) {
                        pstmt.setString(1, wifiDTO.getX_SWIFI_MGR_NO());
                        pstmt.setString(2, wifiDTO.getX_SWIFI_WRDOFC());
                        pstmt.setString(3, wifiDTO.getX_SWIFI_MAIN_NM());
                        pstmt.setString(4, wifiDTO.getX_SWIFI_ADRES1());
                        pstmt.setString(5, wifiDTO.getX_SWIFI_ADRES2());
                        pstmt.setString(6, wifiDTO.getX_SWIFI_INSTL_FLOOR());
                        pstmt.setString(7, wifiDTO.getX_SWIFI_INSTL_TY());
                        pstmt.setString(8, wifiDTO.getX_SWIFI_INSTL_MBY());
                        pstmt.setString(9, wifiDTO.getX_SWIFI_SVC_SE());
                        pstmt.setString(10, wifiDTO.getX_SWIFI_CMCWR());
                        pstmt.setString(11, wifiDTO.getX_SWIFI_CNSTC_YEAR());
                        pstmt.setString(12, wifiDTO.getX_SWIFI_INOUT_DOOR());
                        pstmt.setString(13, wifiDTO.getX_SWIFI_REMARS3());
                        pstmt.setString(14, wifiDTO.getLAT());
                        pstmt.setString(15, wifiDTO.getLNT());
                        pstmt.setString(16, wifiDTO.getWORK_DTTM());

                        pstmt.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }
    }

    // 입력한 위치에서 가장 가까운 wifi 20개
    public List<WifiDTO> findNearWifiDB(double myLat, double myLnt) throws IOException, SQLException {
        connector.connect();
        Connection conn = connector.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String query = "SELECT *,\n" +
                "(6371 * 2 * ASIN(SQRT(POWER(SIN((? - abs(LAT)) * pi()/180 / 2), 2) + " +
                "COS(? * pi()/180) * COS(abs(LAT) * pi()/180) * POWER(SIN((? - LNT) * pi()/180 / 2), 2)))) AS distance\n" +
                "FROM wifiInfo\n" +
                "ORDER BY distance ASC LIMIT 20;";

        List<WifiDTO> wifiList = new ArrayList<>();

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setDouble(1, myLnt);
            pstmt.setDouble(2, myLat);
            pstmt.setDouble(3, myLat);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                WifiDTO wifiDTO = new WifiDTO();
                String X_SWIFI_MGR_NO = rs.getString("X_SWIFI_MGR_NO");
                String X_SWIFI_WRDOFC = rs.getString("X_SWIFI_WRDOFC");
                String X_SWIFI_MAIN_NM = rs.getString("X_SWIFI_MAIN_NM");
                String X_SWIFI_ADRES1 = rs.getString("X_SWIFI_ADRES1");
                String X_SWIFI_ADRES2 = rs.getString("X_SWIFI_ADRES2");
                String X_SWIFI_INSTL_FLOOR = rs.getString("X_SWIFI_INSTL_FLOOR");
                String X_SWIFI_INSTL_TY = rs.getString("X_SWIFI_INSTL_TY");
                String X_SWIFI_INSTL_MBY = rs.getString("X_SWIFI_INSTL_MBY");
                String X_SWIFI_SVC_SE = rs.getString("X_SWIFI_SVC_SE");
                String X_SWIFI_CMCWR = rs.getString("X_SWIFI_CMCWR");
                String X_SWIFI_CNSTC_YEAR = rs.getString("X_SWIFI_CNSTC_YEAR");
                String X_SWIFI_INOUT_DOOR = rs.getString("X_SWIFI_INOUT_DOOR");
                String X_SWIFI_REMARS3 = rs.getString("X_SWIFI_REMARS3");
                String LAT = rs.getString("LAT");
                String LNT = rs.getString("LNT");
                String WORK_DTTM = rs.getString("WORK_DTTM");
                double distance = Math.round(Math.sqrt(rs.getDouble("distance")) * 10000.0) / 10000.0;

                wifiDTO.setX_SWIFI_MGR_NO(X_SWIFI_MGR_NO);
                wifiDTO.setX_SWIFI_WRDOFC(X_SWIFI_WRDOFC);
                wifiDTO.setX_SWIFI_MAIN_NM(X_SWIFI_MAIN_NM);
                wifiDTO.setX_SWIFI_ADRES1(X_SWIFI_ADRES1);
                wifiDTO.setX_SWIFI_ADRES2(X_SWIFI_ADRES2);
                wifiDTO.setX_SWIFI_INSTL_FLOOR(X_SWIFI_INSTL_FLOOR);
                wifiDTO.setX_SWIFI_INSTL_TY(X_SWIFI_INSTL_TY);
                wifiDTO.setX_SWIFI_INSTL_MBY(X_SWIFI_INSTL_MBY);
                wifiDTO.setX_SWIFI_SVC_SE(X_SWIFI_SVC_SE);
                wifiDTO.setX_SWIFI_CMCWR(X_SWIFI_CMCWR);
                wifiDTO.setX_SWIFI_CNSTC_YEAR(X_SWIFI_CNSTC_YEAR);
                wifiDTO.setX_SWIFI_INOUT_DOOR(X_SWIFI_INOUT_DOOR);
                wifiDTO.setX_SWIFI_REMARS3(X_SWIFI_REMARS3);
                wifiDTO.setLAT(LAT);
                wifiDTO.setLNT(LNT);
                wifiDTO.setWORK_DTTM(WORK_DTTM);
                wifiDTO.setDistance(distance);
                wifiList.add(wifiDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }

        return wifiList;
    }
}


