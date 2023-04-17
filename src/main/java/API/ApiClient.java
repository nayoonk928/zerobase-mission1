package API;

import DTO.WifiDTO;
import DTO.WifiResponse;
import com.google.gson.*;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;


public class ApiClient {
    private static ApiClient instance;
    private final String DOMAIN;
    private final String KEY;
    private final String TYPE;
    private final String SERVICE;

    public ApiClient(String domain, String key, String type, String service) {
        DOMAIN = domain;
        KEY = key;
        TYPE = type;
        SERVICE = service;
    }

    public static ApiClient getInstance(String domain, String key, String type, String service) {
        if (instance == null) {
            instance = new ApiClient(domain, key, type, service);
        }
        return instance;
    }

    public WifiResponse callApi(int startIdx, int endIdx) throws IOException {
        OkHttpClient client = new OkHttpClient();
        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host("openapi.seoul.go.kr")
                .port(8088)
                .addPathSegments(KEY)
                .addPathSegment(TYPE)
                .addPathSegment(SERVICE)
                .addPathSegment(Integer.toString(startIdx))
                .addPathSegment(Integer.toString(endIdx))
                .build();

        Request request = new Request.Builder()
                .url(url).get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            String responseBody = response.body().string();
            System.out.println(responseBody); // API 응답 데이터 콘솔 출력
            Gson gson = new Gson();
            return gson.fromJson(responseBody, WifiResponse.class);
        }
    }
}