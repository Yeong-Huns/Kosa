package springFw.ex03.di04.member;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * packageName    : springFW.ex03.di04.member
 * fileName       : MemberMain
 * author         : Yeong-Huns
 * date           : 2024-05-29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-29        Yeong-Huns       최초 생성
 */
public class MemberMain {
    public static void main(String[] args) {
        AbstractApplicationContext context;
        context = new GenericXmlApplicationContext("classpath:config/di04/member/application-config.xml");
        MemberController controller = context.getBean("memberController", MemberController.class);
        controller.printInfo();
        context.close();
    }
}
