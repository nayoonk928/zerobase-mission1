package DTO;

/*
    @author Nayoon
 */
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HistoryDTO {
    private int HIS_NO; //기록번호
    private String LAT; //위도
    private String LNT; //경도
    private String LKUP_DTTM; //조회일자
}