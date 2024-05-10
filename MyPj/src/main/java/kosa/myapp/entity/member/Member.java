package main.java.kosa.myapp.entity.member;

import lombok.Data;

import java.time.LocalDate;

/**
 * packageName    : main.java.kosa.myapp.model
 * fileName       : Member
 * author         : Yeong-Huns
 * date           : 2024-05-04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-04        Yeong-Huns       최초 생성
 */
@Data
public class Member {
    private int memberId;
    private String id;
    private String Password;
    private String Name;
    private LocalDate workStartDate;
    private String PhoneNumber;
    private int AnnualLeaves;
    private int roleId;
    private int deptNo;
}
