package main.java.kosa.myapp.controller.member;

import main.java.kosa.myapp.entity.response.ResponseEntity;
import main.java.kosa.myapp.repository.member.MemberRepository;

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

    public ResponseEntity<Void> insertMember(main.java.kosa.myapp.entity.member.Member member){
        return this.memberRepository.insertMember(member);
    }
}
