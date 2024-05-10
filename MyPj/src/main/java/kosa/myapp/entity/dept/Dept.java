package main.java.kosa.myapp.entity.dept;

import lombok.Data;

/**
 * packageName    : main.java.kosa.myapp.entity.dept
 * fileName       : Dept
 * author         : Yeong-Huns
 * date           : 2024-05-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-08        Yeong-Huns       최초 생성
 */
@Data
public class Dept {
    private int deptNo;
    private String deptName;
    private int beforeUsingAnnualLeaves;
    private int memberId;
}
