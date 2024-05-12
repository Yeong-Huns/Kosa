package main.java.kosa.myapp.util.test;

import main.java.kosa.myapp.dto.attendance.*;
import main.java.kosa.myapp.dto.response.ResponseEntity;
import main.java.kosa.myapp.repository.attendance.AttendanceRepository;
import main.java.kosa.myapp.ui.dialogs.DialogUtils;

import java.time.LocalDate;
import java.util.List;

/**
 * packageName    : main.java.kosa.myapp.util.test
 * fileName       : AttenDanceTest
 * author         : Yeong-Huns
 * date           : 2024-05-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-10        Yeong-Huns       최초 생성
 */
public class AttendanceTest {
    private final AttendanceRepository repository;
    public AttendanceTest() {
        repository = AttendanceRepository.getInstance();
    }

    public void getMemberMonthlyAttendanceIsExist(){
        System.out.println("특정 멤버 범위 내 기록이 존재하는 경우 : ");
        ResponseEntity<List<Attendance>>  response = repository.getMemberMonthlyAttendance(
                Attendance.builder()
                        .memberId(3)
                        .attendanceDate(LocalDate.of(2024,5,1))
                        .build());
        System.out.println(response.getErrorMessage());
        response.runIfSuccess(value-> value.forEach(System.out::println));
    }
    public void getMemberMonthlyAttendanceNotExist(){
        System.out.println("특정 멤버 범위 내 기록이 존재하지 않는 경우 : ");
        ResponseEntity<List<Attendance>>  response = repository.getMemberMonthlyAttendance(
                Attendance.builder()
                        .memberId(3)
                        .attendanceDate(LocalDate.of(2032,5,1))
                        .build());
        System.out.println(response.getErrorMessage());
        response.runIfSuccess(value-> value.forEach(System.out::println));
    }

    public void getDeptMonthlyAttendanceIsExist(){
        System.out.println("부서별 근태 조회 범위 내 기록이 존재하는 경우 : ");
        ResponseEntity<List<GetDeptMonthlyAttendanceResponse>> response = repository.getDeptMonthlyAttendance(
                Attendance.builder()
                        .memberId(3)
                        .attendanceDate(LocalDate.of(2024,5,1))
                        .build());
        System.out.println(response.getErrorMessage());
        response.runIfSuccess(value-> value.forEach(System.out::println));
    }

    public void getDeptMonthlyAttendanceNotExist(){
        System.out.println("부서별 근태 조회 범위 내 기록이 존재하지 않는 경우 : ");
        ResponseEntity<List<GetDeptMonthlyAttendanceResponse>> response = repository.getDeptMonthlyAttendance(
                Attendance.builder()
                        .memberId(3)
                        .attendanceDate(LocalDate.of(2032,5,1))
                        .build());
        System.out.println(response.getErrorMessage());
        response.runIfSuccess(value-> value.forEach(System.out::println));
    }

    public void resisterAttendance(){
        System.out.println("출근시간등록");
        ResponseEntity<Void> response = repository.resisterAttendance(8);
        System.out.println(response.getErrorMessage());
        DialogUtils.showDialogs(response);
    }

    public void getCommuteInfo(){
        System.out.println("commuteInfo 받아오기 : ");
        ResponseEntity<GetCommuteInfoResponse>  response = repository.getCommuteInfo(9);
        System.out.println(response.getErrorMessage());
        response.runIfSuccess(System.out::println);
    }
}
