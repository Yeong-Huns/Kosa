package main.java.kosa.myapp.service.member;

import main.java.kosa.myapp.repository.member.MemberRepository;

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
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = MemberRepository.getInstance();
    }

    public void insertMember(main.java.kosa.myapp.entity.member.Member member){
        this.memberRepository.insertMember(member);
    };


}
