package main.java.kosa.myapp.util.test;

import main.java.kosa.myapp.dto.attendance.request.GetDeptMonthlyAttendanceRequest;
import main.java.kosa.myapp.dto.attendance.request.GetMemberMonthlyAttendanceRequest;
import main.java.kosa.myapp.dto.attendance.response.GetCommuteInfoResponse;
import main.java.kosa.myapp.dto.attendance.response.GetDeptMonthlyAttendanceResponse;
import main.java.kosa.myapp.dto.attendance.response.GetMemberMonthlyAttendanceResponse;
import main.java.kosa.myapp.repository.attendance.AttendanceRepository;

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
        List<GetMemberMonthlyAttendanceResponse> list = repository.getMemberMonthlyAttendance(
                GetMemberMonthlyAttendanceRequest.builder()
                        .currentUserId(2)
                        .year(2024)
                        .month(5)
                        .build());
        list.forEach(System.out::println);
        boolean pass = !list.isEmpty();
        System.out.println("특정 멤버 범위 내 기록이 존재하는 경우 : " + pass);

    }
    public void getMemberMonthlyAttendanceNotExist(){
        List<GetMemberMonthlyAttendanceResponse> list = repository.getMemberMonthlyAttendance(
                GetMemberMonthlyAttendanceRequest.builder()
                        .currentUserId(2)
                        .year(2032)
                        .month(5)
                        .build());
        list.forEach(System.out::println);
        boolean pass = list.isEmpty();
        System.out.println("특정 멤버 범위 내 기록이 존재하지 않는 경우 : " + pass);
    }

    public void getDeptMonthlyAttendanceIsExist(){
        List<GetDeptMonthlyAttendanceResponse> list = repository.getDeptMonthlyAttendance(
                GetDeptMonthlyAttendanceRequest.builder()
                        .currentUserId(2)
                        .year(2024)
                        .month(5)
                        .build());
        list.forEach(System.out::println);
        boolean pass = !list.isEmpty();
        System.out.println("부서별 근태 조회 범위 내 기록이 존재하는 경우 : " + pass);
    }

    public void getDeptMonthlyAttendanceNotExist(){
        List<GetDeptMonthlyAttendanceResponse> list = repository.getDeptMonthlyAttendance(
                GetDeptMonthlyAttendanceRequest.builder()
                        .currentUserId(2)
                        .year(2032)
                        .month(5)
                        .build());
        list.forEach(System.out::println);
        boolean pass = list.isEmpty();
        System.out.println("부서별 근태 조회 범위 내 기록이 존재하지 않는 경우 : " + pass);
    }

    public void resisterAttendance(){
        repository.resisterAttendance(2);
        System.out.println("출근시간등록");
    }

    public void getCommuteInfo(){
        GetCommuteInfoResponse getCommuteInfoResponse = repository.getCommuteInfo(2);
        System.out.println("commuteInfo 받아오기 : " + getCommuteInfoResponse);
    }
}
