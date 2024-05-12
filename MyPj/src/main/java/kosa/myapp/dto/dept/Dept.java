package main.java.kosa.myapp.dto.dept;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

/**
 * packageName    : main.java.kosa.myapp.dto.dept
 * fileName       : Dept
 * author         : Yeong-Huns
 * date           : 2024-05-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-08        Yeong-Huns       최초 생성
 */
@Getter
@Builder
public class Dept {
    private int deptNo;
    private String deptName;
    private int beforeUsingAnnualLeaves;
}
