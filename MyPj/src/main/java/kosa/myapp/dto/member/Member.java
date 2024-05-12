package main.java.kosa.myapp.dto.member;

import lombok.Builder;
import lombok.Getter;

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
@Getter
@Builder
public class Member {
    private int memberId;
    private String id;
    private String password;
    private String name;
    private LocalDate workStartDate;
    private String phoneNumber;
    private int annualLeaves;
    private int roleId;
    private int deptNo;
}
