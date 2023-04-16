package Test;

import java.io.IOException;

import com.google.gson.Gson;

import API.ApiClient;
import DTO.WifiDTO;
import DTO.WifiResponse;

public class ApiTest {
    private static final String DOMAIN = "http://openapi.seoul.go.kr:8088/";
    private static final String KEY = "77715057646c696536344b4a6d4671";
    private static final String TYPE = "json";
    private static final String SERVICE = "TbPublicWifiInfo";

    public static void main(String[] args) {
        int startIdx = 1;
        int endIdx = 2;

        try {
            ApiClient apiClient = ApiClient.getInstance(DOMAIN, KEY, TYPE, SERVICE, startIdx, endIdx);
            String apiResponse = apiClient.callApi();

            Gson gson = new Gson();
            WifiResponse response = gson.fromJson(apiResponse, WifiResponse.class);
            if (response != null && response.getWifiPublicData() != null && response.getWifiPublicData().getWifiList() != null) {
                for (WifiDTO wifiDTO : response.getWifiPublicData().getWifiList()) {
                    System.out.println(wifiDTO.toString());
                }
            } else {
                System.out.println("데이터가 없습니다.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}