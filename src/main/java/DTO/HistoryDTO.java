package DTO;

/*
    @author Nayoon
 */
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HistoryDTO {
    int HIS_NO; //기록번호
    String LAT; //위도
    String LNT; //경도
    String LKUP_DTTM; //조회일자
}