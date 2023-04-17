package DAO;

import API.ApiClient;
import API.ApiService;
import DTO.WifiDTO;
import DTO.WifiResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class WifiDAO {
    private SqliteConnector connector;
    private ApiClient apiClient;

    public WifiDAO(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public WifiDAO(SqliteConnector connector) {
        this.connector = connector;
    }

    public WifiDAO(ApiClient apiClient, SqliteConnector connector) {
        this.apiClient = apiClient;
        this.connector = connector;
    }

    public void insertWifiToDB() throws IOException, SQLException {
        connector.connect();
        Connection conn = connector.getConnection();
        PreparedStatement pstmt = null;
        String sql = "INSERT OR IGNORE INTO wifiInfo (X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, " +
                "X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, " +
                "X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            pstmt = conn.prepareStatement(sql);

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
}

