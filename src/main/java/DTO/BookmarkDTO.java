package DTO;
/*
    @author Nayoon
 */
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookmarkDTO {
    private int BM_ID; // 북마크 그룹 ID
    private String BMG_NM; // 북마크 그룹 이름
    private String X_SWIFI_MAIN_NM; // 와이파이 이름
    private String BM_REGI_DTTM; // 북마크 등록한 날짜
}
