package springFw.ex04.aop01;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * packageName    : springFW.ex03.di01
 * fileName       : HelloMain
 * author         : Yeong-Huns
 * date           : 2024-05-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-28        Yeong-Huns       최초 생성
 */
public class HelloMain {
    public static void main(String[] args) {
        
        AbstractApplicationContext context = new GenericXmlApplicationContext("classpath:config/aop01/application-config.xml");
        
        //Setter 사용
        HelloController helloController = context.getBean("helloController", HelloController.class);
        helloController.hello("이연복");
        context.close();
    }
}
