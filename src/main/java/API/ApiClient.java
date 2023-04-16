package API;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class ApiClient {
    private static ApiClient instance;
    private final String DOMAIN;
    private final String KEY;
    private final String TYPE;
    private final String SERVICE;
    private final int START_IDX;
    private final int END_IDX;

    private ApiClient(String domain, String key, String type, String service, int startIdx, int endIdx) {
        DOMAIN = domain;
        KEY = key;
        TYPE = type;
        SERVICE = service;
        START_IDX = startIdx;
        END_IDX = endIdx;
    }

    public static ApiClient getInstance(String domain, String key, String type, String service, int startIdx, int endIdx) {
        if (instance == null) {
            instance = new ApiClient(domain, key, type, service, startIdx, endIdx);
        }
        return instance;
    }

    public String callApi() throws IOException {
        OkHttpClient client = new OkHttpClient();
        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host("openapi.seoul.go.kr")
                .port(8088)
                .addPathSegments(KEY)
                .addPathSegments(TYPE)
                .addPathSegments(SERVICE)
                .addPathSegments(Integer.toString(START_IDX))
                .addPathSegments(Integer.toString(END_IDX))
                .build();

        Request request = new Request.Builder()
                .url(url).get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            return response.body().string();
        }
    }
}