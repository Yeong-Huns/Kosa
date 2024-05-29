package springFW.ex03.di04.member;

import org.springframework.stereotype.Controller;

/**
 * packageName    : springFW.ex03.di04.member
 * fileName       : MemberController
 * author         : Yeong-Huns
 * date           : 2024-05-29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-29        Yeong-Huns       최초 생성
 */
@Controller
public class MemberController {
    IMemberService memberService;
    public void printInfo(){
        MemberVO memberVO = memberService.getMemberInfo();
        System.out.println(memberVO);
    }

    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }
}
