package main.java.kosa.myapp.controller.member;

import main.java.kosa.myapp.dto.member.request.LoginRequest;
import main.java.kosa.myapp.entity.member.Member;
import main.java.kosa.myapp.entity.response.ResponseEntity;
import main.java.kosa.myapp.repository.member.MemberRepository;

import java.util.OptionalInt;

/**
 * packageName    : main.java.kosa.myapp.controller.member
 * fileName       : MemberController
 * author         : Yeong-Huns
 * date           : 2024-05-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-05        Yeong-Huns       최초 생성
 */
public class MemberController {
    private final MemberRepository memberRepository;
    private static MemberController instance;

    private MemberController() {
        this.memberRepository = MemberRepository.getInstance();
    }

    public static MemberController getInstance() {
        if (instance == null) {
            instance = new MemberController();
        }
        return instance;
    }

    public void insertMember(Member member){
        memberRepository.insertMember(member);
    }

    public int login(LoginRequest request){
        return memberRepository.login(request);
    }
    /*
    public ResponseEntity<Member> getMember(Member member){
        return memberRepository.getMember(member);
    }*/
}
