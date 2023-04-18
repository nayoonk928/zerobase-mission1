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
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void insertWifiToDB() throws IOException, SQLException {
        connector.connect();
        Connection conn = connector.getConnection();
        PreparedStatement pstmt = null;
        String query = "INSERT OR IGNORE INTO wifiInfo (X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, " +
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

    public Map<WifiDTO, Double> findNearWifiDB(double myLat, double myLnt) throws IOException, SQLException {
        connector.connect();
        Connection conn = connector.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = "SELECT *,\n" +
                "(ABS(LAT - ?) * ABS(LAT - ?)), + \n" +
                "(ABS(LNT - ?) * ABS(LNT - ?)) AS distance\n" +
                "FROM wifiInfo ORDER BY distance ASC LIMIT 20;";

        Map<WifiDTO, Double> wifiList = new HashMap<>();

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setDouble(1, myLat);
            pstmt.setDouble(2, myLat);
            pstmt.setDouble(3, myLnt);
            pstmt.setDouble(4, myLnt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
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

                double distance = Math.sqrt(rs.getDouble("distance"));
                double realDistance = getDistance(myLat, myLnt, Double.parseDouble(LAT), Double.parseDouble(LNT));
                double roundedDistance = Double.parseDouble(new DecimalFormat("##.####").format(realDistance));

                WifiDTO wifiDTO = new WifiDTO();
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
                wifiDTO.setDistance(roundedDistance);
                wifiList.put(wifiDTO, roundedDistance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }

        return wifiList;
    }

    // 정확한 거리를 구하기 위한 메서드
    public double getDistance(double lat1, double lnt1, double lat2, double lnt2) {
        final double EARTH_RADIUS = 6371; // 지구 반지름
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lnt2 - lnt1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c;
    }
}


