package main.java.kosa.myapp.repository.attendance;

import main.java.kosa.myapp.dto.attendance.request.GetDeptMonthlyAttendanceRequest;
import main.java.kosa.myapp.dto.attendance.request.GetMemberMonthlyAttendanceRequest;
import main.java.kosa.myapp.dto.attendance.response.GetCommuteInfoResponse;
import main.java.kosa.myapp.dto.attendance.response.GetDeptMonthlyAttendanceResponse;
import main.java.kosa.myapp.dto.attendance.response.GetMemberMonthlyAttendanceResponse;
import main.java.kosa.myapp.entity.response.ResponseEntity;
import main.java.kosa.myapp.ui.dialogs.DialogUtils;
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
        this.callableStatementTemplate = CallableStatementTemplate.getCallableStatement();
    }

    public List<GetMemberMonthlyAttendanceResponse> getMemberMonthlyAttendance(GetMemberMonthlyAttendanceRequest request){
        ResponseEntity<List<GetMemberMonthlyAttendanceResponse>> getMemberMonthlyAttendance =
        callableStatementTemplate.queryForMany(
          "{ call attendance_package.get_member_monthly_attendance(?, ?, ?, ?, ?, ?) }",
                cs -> {
                    cs.setInt(1, request.currentUserId());
                    cs.setInt(2, request.year());
                    cs.setInt(3, request.month());
                    cs.registerOutParameter(4, OracleTypes.CURSOR);
                    cs.registerOutParameter(5, Types.INTEGER);
                    cs.registerOutParameter(6, Types.VARCHAR);
                },
                (rs, rowNum) -> GetMemberMonthlyAttendanceResponse.builder()
                        .attendanceId(rs.getInt(1))
                        .attendanceDate(rs.getDate(2).toLocalDate())
                        .startOfWork(rs.getTimestamp(3).toLocalDateTime())
                        .endOfWork(rs.getTimestamp(4).toLocalDateTime())
                        .build()
        );
        return getMemberMonthlyAttendance.getOrThrow();
    }

    public List<GetDeptMonthlyAttendanceResponse> getDeptMonthlyAttendance(GetDeptMonthlyAttendanceRequest request){
        ResponseEntity<List<GetDeptMonthlyAttendanceResponse>> getDeptMonthlyAttendance =
        callableStatementTemplate.queryForMany(
          " { call attendance_package.get_dept_monthly_attendance(?, ?, ?, ?, ?, ?) } ",
          cs ->{
              cs.setInt(1, request.currentUserId());
              cs.setInt(2, request.year());
              cs.setInt(3, request.month());
              cs.registerOutParameter(4, OracleTypes.CURSOR);
              cs.registerOutParameter(5, Types.INTEGER);
              cs.registerOutParameter(6, Types.VARCHAR);
          }, (rs, rowNum) -> GetDeptMonthlyAttendanceResponse.builder()
                        .memberName(rs.getString(1))
                        .deptNo(rs.getInt(2))
                        .date(rs.getDate(3).toLocalDate())
                        .startOfWork(rs.getTimestamp(4).toLocalDateTime())
                        .endOfWork(rs.getTimestamp(5).toLocalDateTime())
                        .build()
        );
        return getDeptMonthlyAttendance.getOrThrow();
    }

    public void resisterAttendance(int currentUserId){
        ResponseEntity<Void> resisterAttendance =
        callableStatementTemplate.executeUpdate(
                "{ call attendance_package.resister_attendance(?, ?, ?, ?)}",
                cs -> {
                    cs.setInt(1, currentUserId);
                    cs.registerOutParameter(2, OracleTypes.CURSOR);
                    cs.registerOutParameter(3, Types.INTEGER);
                    cs.registerOutParameter(4, Types.VARCHAR);
                }
        );
        resisterAttendance.executeIfSuccessOrElseThrow(
                () -> DialogUtils.showSuccessMessage(resisterAttendance.getErrorMessage()),
                () -> DialogUtils.showFailureMessage(resisterAttendance.getErrorMessage())
        );
    }

    public GetCommuteInfoResponse getCommuteInfo(int currentUserId){
        ResponseEntity<GetCommuteInfoResponse> commuteInfo = callableStatementTemplate.queryForOne(
                "{ call attendance_package.get_commute_info(?, ?, ?, ?) }",
                cs -> {
                    cs.setInt(1, currentUserId);
                    cs.registerOutParameter(2, OracleTypes.CURSOR);
                    cs.registerOutParameter(3, Types.INTEGER);
                    cs.registerOutParameter(4, Types.VARCHAR);
                },
                (rs, rowNum) -> GetCommuteInfoResponse.builder()
                        .attendanceDate(rs.getDate(1).toLocalDate())
                        .startOfDate(rs.getTimestamp(2).toLocalDateTime())
                        .endOfDate(rs.getTimestamp(3).toLocalDateTime())
                        .build()
        );
        return commuteInfo.executeIfSuccessOrElseThrow(
                () -> DialogUtils.showSuccessMessage(commuteInfo.getErrorMessage()),
                () -> DialogUtils.showFailureMessage(commuteInfo.getErrorMessage())
        );
    }
/*
    public ResponseEntity<Void> resisterAttendance(int currentUserId){

    }
*/
    //public get_commute_info

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
