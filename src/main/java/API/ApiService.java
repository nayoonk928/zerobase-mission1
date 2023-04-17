package API;

import DTO.WifiResponse;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class ApiService {
    private final ApiClient apiClient;
    public WifiResponse getWifiData(int startIdx, int endIdx) throws IOException {
        return apiClient.callApi(startIdx, endIdx);
    }
}