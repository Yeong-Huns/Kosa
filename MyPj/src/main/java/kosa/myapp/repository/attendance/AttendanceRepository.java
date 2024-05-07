package main.java.kosa.myapp.repository.attendance;

import main.java.kosa.myapp.entity.attendance.Attendance;
import main.java.kosa.myapp.entity.response.ResponseEntity;
import main.java.kosa.myapp.util.dataBaseConnection.CallableStatementTemplate;

import java.util.List;

/**
 * packageName    : main.java.kosa.myapp.repository.attendance
 * fileName       : Attendance
 * author         : Yeong-Huns
 * date           : 2024-05-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-07        Yeong-Huns       최초 생성
 */
public class AttendanceRepository {
    private final CallableStatementTemplate callableStatementTemplate;
    private static AttendanceRepository instance;

    public static AttendanceRepository getInstance() {
        if (instance == null) {
            instance = new AttendanceRepository();
        }
        return instance;
    }
    private AttendanceRepository() {
        this.callableStatementTemplate = new CallableStatementTemplate();
    }
    /*
    create or replace package attendance_package as
-- 근무기록조회(member_id를 통해 해당하는 월 근무기록 조회)
    procedure get_member_monthly_attendance(
        p_member_id         in number,
        p_year              in number,
        p_month             in number,
        p_records           out sys_refcursor,
        p_result_code       out number,
        p_result_message    out varchar2);
    * */
    /*public ResponseEntity<List<Attendance>> getYearMonthAttendance() {
        return ResponseEntity<Collect>
    }*/
}
