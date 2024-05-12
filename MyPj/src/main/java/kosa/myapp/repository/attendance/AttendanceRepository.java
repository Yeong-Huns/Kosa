package main.java.kosa.myapp.repository.attendance;

import main.java.kosa.myapp.dto.attendance.Attendance;
import main.java.kosa.myapp.dto.attendance.GetCommuteInfoResponse;
import main.java.kosa.myapp.dto.attendance.GetDeptMonthlyAttendanceResponse;
import main.java.kosa.myapp.dto.response.ResponseEntity;
import main.java.kosa.myapp.util.dataBaseConnection.CallableStatementTemplate;
import oracle.jdbc.OracleTypes;

import java.sql.Types;
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
 * 2024-05-07        Yeong-Huns       최초 생성`
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
        this.callableStatementTemplate = CallableStatementTemplate.getCallableStatement();
    }

    public ResponseEntity<List<Attendance>> getMemberMonthlyAttendance(Attendance attendance){
        return
        callableStatementTemplate.queryForMany(
          "{ call attendance_package.get_member_monthly_attendance(?, ?, ?, ?, ?, ?) }",
                cs -> {
                    cs.setInt(1, attendance.getMemberId());
                    cs.setInt(2, attendance.getAttendanceDate().getYear());
                    cs.setInt(3, attendance.getAttendanceDate().getMonthValue());
                    cs.registerOutParameter(4, OracleTypes.CURSOR);
                    cs.registerOutParameter(5, Types.INTEGER);
                    cs.registerOutParameter(6, Types.VARCHAR);
                },
                (rs, rowNum) -> Attendance.builder()
                        .attendanceId(rs.getInt(1))
                        .attendanceDate(rs.getDate(2).toLocalDate())
                        .startOfWork(rs.getTimestamp(3) == null ? null : rs.getTimestamp(3).toLocalDateTime().toLocalTime())
                        .endOfWork(rs.getTimestamp(4) == null ? null : rs.getTimestamp(4).toLocalDateTime().toLocalTime())
                        .build()
        );
    }

    public ResponseEntity<List<GetDeptMonthlyAttendanceResponse>> getDeptMonthlyAttendance(Attendance attendance){
        return
        callableStatementTemplate.queryForMany(
          " { call attendance_package.get_dept_monthly_attendance(?, ?, ?, ?, ?, ?) } ",
          cs ->{
              cs.setInt(1, attendance.getMemberId());
              cs.setInt(2, attendance.getAttendanceDate().getYear());
              cs.setInt(3, attendance.getAttendanceDate().getMonthValue());
              cs.registerOutParameter(4, OracleTypes.CURSOR);
              cs.registerOutParameter(5, Types.INTEGER);
              cs.registerOutParameter(6, Types.VARCHAR);
          }, (rs, rowNum) -> GetDeptMonthlyAttendanceResponse.builder()
                        .memberName(rs.getString(1))
                        .deptNo(rs.getInt(2))
                        .date(rs.getDate(3).toLocalDate())
                        .startOfWork(rs.getTimestamp(4).toLocalDateTime().toLocalTime())
                        .endOfWork(rs.getTimestamp(5) == null ? null : rs.getTimestamp(5).toLocalDateTime().toLocalTime())
                        .build()
        );
    }

    public ResponseEntity<Void> resisterAttendance(int currentUserId){
        return
        callableStatementTemplate.executeUpdate(
                "{ call attendance_package.resister_attendance(?, ?, ?, ?)}",
                cs -> {
                    cs.setInt(1, currentUserId);
                    cs.registerOutParameter(2, OracleTypes.CURSOR);
                    cs.registerOutParameter(3, Types.INTEGER);
                    cs.registerOutParameter(4, Types.VARCHAR);
                }
        );
    }

    public ResponseEntity<GetCommuteInfoResponse> getCommuteInfo(int currentUserId){
        return callableStatementTemplate.queryForOne(
                "{ call attendance_package.get_commute_info(?, ?, ?, ?) }",
                cs -> {
                    cs.setInt(1, currentUserId);
                    cs.registerOutParameter(2, OracleTypes.CURSOR);
                    cs.registerOutParameter(3, Types.INTEGER);
                    cs.registerOutParameter(4, Types.VARCHAR);
                },
                (rs, rowNum) ->
                        GetCommuteInfoResponse.builder()
                        .attendanceDate(rs.getDate(1).toLocalDate())
                        .startOfWork(rs.getTimestamp(2) == null ? null : rs.getTimestamp(2).toLocalDateTime().toLocalTime())
                        .endOfWork(rs.getTimestamp(3) == null ? null : rs.getTimestamp(3).toLocalDateTime().toLocalTime())
                                .deptName(rs.getString(4))
                        .build());
    }
}
