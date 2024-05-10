package main.java.kosa.myapp.dto.attendance.request;

import lombok.Builder;

import java.time.LocalDate;

/**
 * packageName    : main.java.kosa.myapp.dto.attendance.request
 * fileName       : GetMemberMonthlyAttendanceRequest
 * author         : Yeong-Huns
 * date           : 2024-05-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-10        Yeong-Huns       최초 생성
 */
@Builder
public record GetMemberMonthlyAttendanceRequest(int currentUserId, int year, int month) {
}
