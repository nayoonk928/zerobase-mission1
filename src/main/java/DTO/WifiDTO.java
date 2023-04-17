package DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WifiDTO {
    private String X_SWIFI_MGR_NO; //관리번호
    private String X_SWIFI_WRDOFC; //자치구
    private String X_SWIFI_MAIN_NM; //와이파이명
    private String X_SWIFI_ADRES1; //도로명주소
    private String X_SWIFI_ADRES2; //상세주소
    private String X_SWIFI_INSTL_FLOOR; //설치위치(층)
    private String X_SWIFI_INSTL_TY; //설치유형
    private String X_SWIFI_INSTL_MBY; //설치기관
    private String X_SWIFI_SVC_SE; //서비스 구분
    private String X_SWIFI_CMCWR; //망 종류
    private String X_SWIFI_CNSTC_YEAR; //설치년도
    private String X_SWIFI_INOUT_DOOR; //실내외구분
    private String X_SWIFI_REMARS3; //접속환경
    private String LAT; //y좌표
    private String LNT; //x좌표
    private String WORK_DTTM; //작업일자

    public WifiDTO(String x_SWIFI_MGR_NO, String x_SWIFI_WRDOFC, String x_SWIFI_MAIN_NM, String x_SWIFI_ADRES1,
                   String x_SWIFI_ADRES2, String x_SWIFI_INSTL_FLOOR, String x_SWIFI_INSTL_TY, String x_SWIFI_INSTL_MBY,
                   String x_SWIFI_SVC_SE, String x_SWIFI_CMCWR, String x_SWIFI_CNSTC_YEAR, String x_SWIFI_INOUT_DOOR,
                   String x_SWIFI_REMARS3, String lAT, String lNT, String wORK_DTTM) {
        this.X_SWIFI_MGR_NO = x_SWIFI_MGR_NO;
        this.X_SWIFI_WRDOFC = x_SWIFI_WRDOFC;
        this.X_SWIFI_MAIN_NM = x_SWIFI_MAIN_NM;
        this.X_SWIFI_ADRES1 = x_SWIFI_ADRES1;
        this.X_SWIFI_ADRES2 = x_SWIFI_ADRES2;
        this.X_SWIFI_INSTL_FLOOR = x_SWIFI_INSTL_FLOOR;
        this.X_SWIFI_INSTL_TY = x_SWIFI_INSTL_TY;
        this.X_SWIFI_INSTL_MBY = x_SWIFI_INSTL_MBY;
        this.X_SWIFI_SVC_SE = x_SWIFI_SVC_SE;
        this.X_SWIFI_CMCWR = x_SWIFI_CMCWR;
        this.X_SWIFI_CNSTC_YEAR = x_SWIFI_CNSTC_YEAR;
        this.X_SWIFI_INOUT_DOOR = x_SWIFI_INOUT_DOOR;
        this.X_SWIFI_REMARS3 = x_SWIFI_REMARS3;
        this.LAT = lAT;
        this.LNT = lNT;
        this.WORK_DTTM = wORK_DTTM;
    }

}