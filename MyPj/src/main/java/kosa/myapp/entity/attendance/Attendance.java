package main.java.kosa.myapp.entity.attendance;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * packageName    : main.java.kosa.myapp.entity.attendance
 * fileName       : Attendance
 * author         : Yeong-Huns
 * date           : 2024-05-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-07        Yeong-Huns       최초 생성
 */
@Data
public class Attendance {
    private long id;
    private LocalDate date;
    private int deptNO;
    private long memberId;
    private int approvalType;
    private int confirm;
    private String content;
    private LocalTime approvalDate;
}
