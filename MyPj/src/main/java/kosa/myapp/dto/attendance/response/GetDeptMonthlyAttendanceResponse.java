package main.java.kosa.myapp.dto.attendance.response;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * packageName    : main.java.kosa.myapp.dto.attendance.response
 * fileName       : GetDeptMonthlyAttendanceResponse
 * author         : Yeong-Huns
 * date           : 2024-05-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-10        Yeong-Huns       최초 생성
 */
@Builder
public record GetDeptMonthlyAttendanceResponse(String memberName, int deptNo, LocalDate date, LocalDateTime startOfWork, LocalDateTime endOfWork) {
}
