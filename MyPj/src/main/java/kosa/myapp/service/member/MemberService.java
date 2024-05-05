package main.java.kosa.myapp.service.member;

import lombok.RequiredArgsConstructor;
import main.java.kosa.myapp.repository.member.MemberDAO;

import java.lang.reflect.Member;

/**
 * packageName    : main.java.kosa.myapp.service.member
 * fileName       : MemberService
 * author         : Yeong-Huns
 * date           : 2024-05-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-05        Yeong-Huns       최초 생성
 */

public class MemberService {
    private final MemberDAO memberDAO;

    public MemberService(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    public void insertMember(Member member){
        memberDAO.insertMember(member);
    };


}
