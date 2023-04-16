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

    public String toString() {
        return "WifiDTO{" +
                "X_SWIFI_MGR_NO='" + X_SWIFI_MGR_NO + '\'' +
                ", X_SWIFI_WRDOFC='" + X_SWIFI_WRDOFC + '\'' +
                ", X_SWIFI_MAIN_NM='" + X_SWIFI_MAIN_NM + '\'' +
                ", X_SWIFI_ADRES1='" + X_SWIFI_ADRES1 + '\'' +
                ", X_SWIFI_ADRES2='" + X_SWIFI_ADRES2 + '\'' +
                ", X_SWIFI_INSTL_FLOOR='" + X_SWIFI_INSTL_FLOOR + '\'' +
                ", X_SWIFI_INSTL_TY='" + X_SWIFI_INSTL_TY + '\'' +
                ", X_SWIFI_INSTL_MBY='" + X_SWIFI_INSTL_MBY + '\'' +
                ", X_SWIFI_SVC_SE='" + X_SWIFI_SVC_SE + '\'' +
                ", X_SWIFI_CMCWR='" + X_SWIFI_CMCWR + '\'' +
                ", X_SWIFI_CNSTC_YEAR='" + X_SWIFI_CNSTC_YEAR + '\'' +
                ", X_SWIFI_INOUT_DOOR='" + X_SWIFI_INOUT_DOOR + '\'' +
                ", X_SWIFI_REMARS3='" + X_SWIFI_REMARS3 + '\'' +
                ", LAT='" + LAT + '\'' +
                ", LNT='" + LNT + '\'' +
                ", WORK_DTTM='" + WORK_DTTM + '\'' +
                '}';
    }
}