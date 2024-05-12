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

        //test.memberTest();
        //test.attendanceTest();
        test.ApprovalTest();
    }

    public void memberTest(){
        MemberTest memberTest = new MemberTest();

        memberTest.insertMember();
        memberTest.login();
        memberTest.get_all_member();
        memberTest.update_deptno_and_role();
        memberTest.delete_member();
        System.out.println("===여기부터 실패 케이스===");
        //memberTest.insertMember_fail();
        //memberTest.failLogin();
        //memberTest.get_all_member_fail();
        memberTest.update_deptno_and_role_fail();
        memberTest.delete_member_fail();
    }

    public void attendanceTest(){
        AttendanceTest attendanceTest = new AttendanceTest();

        System.out.println("특정 멤버 범위 내 기록이 존재하는 경우 : ");
        attendanceTest.getMemberMonthlyAttendanceIsExist();
        System.out.println("특정 멤버 범위 내 기록이 존재하지 않는 경우 : ");
        attendanceTest.getMemberMonthlyAttendanceNotExist();
        System.out.println("부서별 근태 조회 범위 내 기록이 존재하는 경우 : ");
        attendanceTest.getDeptMonthlyAttendanceIsExist();
        System.out.println("부서별 근태 조회 범위 내 기록이 존재하지 않는 경우 : ");
        attendanceTest.getDeptMonthlyAttendanceNotExist();
        System.out.println("출근시간등록");
        attendanceTest.resisterAttendance();
        System.out.println("근태 정보 받아오기");
        attendanceTest.getCommuteInfo();
    }

    public void ApprovalTest(){
        ApprovalTest approvalTest = new ApprovalTest();
        approvalTest.get_member_annual_leaves_Success();
        approvalTest.get_member_annual_leaves_Not_Found();
        approvalTest.getMemberStatementOfReason_Success();
        approvalTest.getMemberStatementOfReason_Not_Found();
        approvalTest.getDeptAnnualLeaves_Success();
        approvalTest.getDeptAnnualLeaves_Not_Found();
        approvalTest.resisterApproval();
        approvalTest.insertStatementReason();
        approvalTest.approveOrRejectAnnualLeave();
    }


}
