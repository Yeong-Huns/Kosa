package springFw.ex05.jdbc01;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * packageName    : springFW.ex05.jdbc01
 * fileName       : EmpMain
 * author         : Yeong-Huns
 * date           : 2024-05-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-30        Yeong-Huns       최초 생성
 */
public class EmpMain {
    public static void main(String[] args) {
        AbstractApplicationContext context = new GenericXmlApplicationContext("classpath:config/jdbc01/application-config.xml");

        IEmpService empService = context.getBean(IEmpService.class);
        System.out.println("-- 사원수 --");
        System.out.println(empService.getEmpCount());
        System.out.println(empService.getEmpCount(50));

        System.out.println("-- 사원리스트 --");
        System.out.println(empService.getEmpList());

        System.out.println("-- 200 사원 검색 --");
        System.out.println(empService.getEmpInfo(200));
    }
}
