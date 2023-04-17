package API;

/*
    @author Nayoon
 */

public class ApiConfig {
    public static final String DOMAIN = "http://openapi.seoul.go.kr:8088/";
    public static final String KEY = "77715057646c696536344b4a6d4671";
    public static final String TYPE = "json";
    public static final String SERVICE = "TbPublicWifiInfo";

    // API 요청 URL 생성
    public static String getWifiAPIUrl(int startIdx, int endIdx) {
        return DOMAIN + KEY + "/" + TYPE + "/" + SERVICE + "/" + startIdx + "/" + endIdx+ "/";
    }
}
