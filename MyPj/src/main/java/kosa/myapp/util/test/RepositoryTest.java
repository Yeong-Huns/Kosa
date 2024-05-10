package main.java.kosa.myapp.util.test;

/**
 * packageName    : main.java.kosa.myapp.util.test
 * fileName       : RepositoryTest
 * author         : Yeong-Huns
 * date           : 2024-05-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-10        Yeong-Huns       최초 생성
 */
public class RepositoryTest {
    public static void main(String[] args) {
    RepositoryTest test = new RepositoryTest();

        test.memberTestUnit();
        test.attendanceTestUnit();
    }

    public void memberTestUnit(){
        MemberTest memberTest = new MemberTest();
        System.out.println("===멤버 추가 테스트===");
        memberTest.insertMember();
        System.out.println("===로그인 테스트===");
        memberTest.login();
        System.out.println("===멤버 전체 조회 테스트===");
        memberTest.get_all_member();
        System.out.println("===부서, 역할 업데이트 테스트===");
        memberTest.update_deptno_and_role();
        System.out.println("===멤버 삭제 테스트===");
        memberTest.delete_member();
    }

    public void attendanceTestUnit(){
        AttendanceTest attendanceTest = new AttendanceTest();

        System.out.println("특정 멤버 범위 내 기록이 존재하는 경우 : ");
        attendanceTest.getMemberMonthlyAttendanceIsExist();
        System.out.println("특정 멤버 범위 내 기록이 존재하지 않는 경우 : ");
        attendanceTest.getMemberMonthlyAttendanceNotExist();
        System.out.println("부서별 근태 조회 범위 내 기록이 존재하는 경우 : ");
        attendanceTest.getDeptMonthlyAttendanceIsExist();
        System.out.println("부서별 근태 조회 범위 내 기록이 존재하지 않는 경우 : ");
        attendanceTest.getDeptMonthlyAttendanceNotExist();
        //System.out.println("출근시간등록");
        //attendanceTest.resisterAttendance();
        System.out.println("근태 정보 받아오기");
        attendanceTest.getCommuteInfo();
    }
}
