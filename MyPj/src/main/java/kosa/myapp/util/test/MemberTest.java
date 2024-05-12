package main.java.kosa.myapp.util.test;

import main.java.kosa.myapp.dto.member.DeleteMemberRequest;
import main.java.kosa.myapp.dto.member.Member;
import main.java.kosa.myapp.dto.member.UpdateDeptAndRoleRequest;
import main.java.kosa.myapp.dto.response.ResponseEntity;
import main.java.kosa.myapp.repository.member.MemberRepository;

import java.util.List;

/**
 * packageName    : main.java.kosa.myapp.util.test
 * fileName       : MemberTest
 * author         : Yeong-Huns
 * date           : 2024-05-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-10        Yeong-Huns       최초 생성
 */
public class MemberTest {
    private final MemberRepository memberRepository;
    private int targetId;
    public MemberTest() {
        memberRepository = MemberRepository.getInstance();
    }

    public void insertMember() {
        System.out.println("===멤버 추가 테스트===");
        ResponseEntity<Void> response = memberRepository.insertMember(
                Member.builder()
                        .id("테스트1")
                        .password("TestUserPassWord")
                        .name("테스트")
                        .phoneNumber("01033333333")
                        .build());
        response.printLog();
        response.showDialogs();
    }

    public void login(){
        System.out.println("===로그인 테스트===");
        ResponseEntity<Integer> response = memberRepository.login(
                Member.builder()
                .id("테스트1")
                .password("TestUserPassWord")
                .build());
        response.runIfSuccess(this::setInt);
        response.printLog();
        response.showDialogs();
    }


    public void get_all_member(){
        System.out.println("===멤버 전체 조회 테스트===");
        ResponseEntity<List<Member>> response = memberRepository.getAllMember(2);
        response.printLog();
        response.runIfSuccess(Value -> Value.forEach(System.out::println));
    }

    public void update_deptno_and_role(){
        System.out.println("===부서, 역할 업데이트 테스트===");
        ResponseEntity<Void> response = memberRepository.updateDeptAndRole(
                UpdateDeptAndRoleRequest.builder()
                        .currentUserId(2)
                        .targetMemberId(targetId)
                        .deptNo(2)
                        .roleId(3)
                        .build()
        );
        response.printLog();
        response.showDialogs();
    }

    public void delete_member(){
        System.out.println("===멤버 삭제 테스트===");
        ResponseEntity<Void> response = memberRepository.deleteMember(DeleteMemberRequest.builder()
                .currentUserId(2)
                .targetUserId(targetId)
                .build());
        response.printLog();
        response.showDialogs();
    }
//============================================================================================================
    //여기부터 실패 케이스
    public void insertMember_fail() {
        System.out.println("===멤버 추가 실패 테스트===");
        ResponseEntity<Void> response = memberRepository.insertMember(
                Member.builder().id("user1")
                .password("TestUserPassWord")
                .name("테스트")
                .phoneNumber("01033333333")
                .build());
        response.printLog();
        response.showDialogs();
    }

    public void failLogin(){
        System.out.println("===로그인 실패 테스트===");
        ResponseEntity<Integer> response =  memberRepository.login(Member.builder()
                .id("dd")
                .password("ff")
                .build());
        response.printLog();
        response.runIfSuccess(this::setInt);
    }

    public void get_all_member_fail(){
        System.out.println("===멤버 전체 조회 실패 테스트===");
        ResponseEntity<List<Member>> response = MemberRepository.getInstance().getAllMember(999);
        response.printLog();
        response.runIfSuccess(Value -> Value.forEach(System.out::println));
    }



    public void update_deptno_and_role_fail(){
        System.out.println("===부서, 역할 업데이트 실패 테스트===");
        ResponseEntity<Void> response = memberRepository.updateDeptAndRole(
                UpdateDeptAndRoleRequest.builder()
                        .currentUserId(2)
                        .targetMemberId(39478)
                        .deptNo(2)
                        .roleId(3)
                        .build()
        );
        response.printLog();
        response.showDialogs();
    }



    public void delete_member_fail(){
        System.out.println("===멤버 삭제 실패 테스트===");
        ResponseEntity<Void> response = memberRepository.deleteMember(DeleteMemberRequest.builder()
                .currentUserId(2)
                .targetUserId(39483)
                .build());
        response.printLog();
        response.showDialogs();
    }


    private void setInt(int number){
        targetId = number;
    }
}
