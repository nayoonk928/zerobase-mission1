package DTO;
/*
    @author Nayoon
 */
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookmarkGroupDTO {
    private int BMG_ID; // 북마크 그룹 ID
    private String BMG_NM; // 북마크 그룹 이름
    private int BMG_ORDER; // 순서
    private String BMG_CR_DTTM; // 만든 날짜
}
