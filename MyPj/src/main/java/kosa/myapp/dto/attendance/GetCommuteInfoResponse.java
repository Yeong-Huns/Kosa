package main.java.kosa.myapp.dto.attendance;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * packageName    : main.java.kosa.myapp.dto.attendance
 * fileName       : GetCommuteInfo
 * author         : Yeong-Huns
 * date           : 2024-05-13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-13        Yeong-Huns       최초 생성
 */
@Builder
public record GetCommuteInfoResponse(LocalDate attendanceDate, LocalTime startOfWork, LocalTime endOfWork, String deptName) {
}
