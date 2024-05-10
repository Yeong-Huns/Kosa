package main.java.kosa.myapp.util.test;

import main.java.kosa.myapp.dto.member.request.DeleteMemberRequest;
import main.java.kosa.myapp.dto.member.request.LoginRequest;
import main.java.kosa.myapp.dto.member.request.UpdateDeptAndRoleRequest;
import main.java.kosa.myapp.dto.member.response.GetAllMemberResponse;
import main.java.kosa.myapp.entity.member.Member;
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
        Member member = new Member();
        member.setId("테스트1");
        member.setPassword("TestUserPassWord");
        member.setName("테스트");
        member.setPhoneNumber("01033333333");
        memberRepository.insertMember(member);
    }

    public void login(){
        int key = memberRepository.login(LoginRequest.builder()
                .id("테스트1")
                .password("TestUserPassWord")
                .build());
        setInt(key);
    }

    public void get_all_member(){
        List<GetAllMemberResponse> allMember = memberRepository.getAllMember(2);
            allMember.forEach(System.out::println);
    }

    public void update_deptno_and_role(){
        memberRepository.updateDeptAndRole(
                UpdateDeptAndRoleRequest.builder()
                        .currentUserId(2)
                        .targetMemberId(targetId)
                        .deptNo(2)
                        .roleId(3)
                        .build()
        );
    }

    public void delete_member(){
        memberRepository.deleteMember(DeleteMemberRequest.builder()
                .currentUserId(2)
                .targetUserId(targetId)
                .build());
    }

    private void setInt(int number){
        targetId = number;
    }
}
