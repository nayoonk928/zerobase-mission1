package Test;

/*
    @author Nayoon
 */

import java.io.IOException;
import java.util.List;

import API.ApiConfig;

import API.ApiClient;
import DAO.WifiDAO;
import DTO.WifiDTO;

public class ApiTest {

    public static void main(String[] args) {
        int startIdx = 1;
        int endIdx = 3;

//        ApiClient apiClient = ApiClient.getInstance(ApiConfig.DOMAIN, ApiConfig.KEY, ApiConfig.TYPE, ApiConfig.SERVICE);
//        WifiDAO wifiDAO = new WifiDAO(apiClient);
//
//        try {
//            List<WifiDTO> wifiDTOList = wifiDAO.insertWifiAPI(startIdx, endIdx);
//            for (WifiDTO wifiDTO : wifiDTOList) {
//                System.out.println(wifiDTO);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}