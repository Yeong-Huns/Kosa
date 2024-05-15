package main.java.kosa.myapp.dto.attendance;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
@Getter
@Builder
public class Attendance {
    private int attendanceId;
    private LocalDate attendanceDate;
    private LocalTime startOfWork;
    private LocalTime endOfWork;
    private int memberId;

    @Override
    public String toString() {
        return "Attendance{" +
                "attendanceId=" + attendanceId +
                ", attendanceDate=" + attendanceDate +
                ", startOfWork=" + startOfWork +
                ", endOfWork=" + endOfWork +
                ", memberId=" + memberId +
                '}';
    }
}
