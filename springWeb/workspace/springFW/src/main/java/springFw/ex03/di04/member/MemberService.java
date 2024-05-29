package springFw.ex03.di04.member;

/**
 * packageName    : springFW.ex03.di04.member
 * fileName       : MemberService
 * author         : Yeong-Huns
 * date           : 2024-05-29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-29        Yeong-Huns       최초 생성
 */
public class MemberService implements IMemberService {
    @Override
    public MemberVO getMemberInfo() {
        MemberVO vo = new MemberVO();
        vo.setMemberID("1234");
        vo.setAge(22);
        vo.setName("홍길동");
        vo.setEmail("aaa@naver.com");
        return vo;
    }
}
