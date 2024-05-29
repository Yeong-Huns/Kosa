package springFw.ex03.di03;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
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

        //spring DL, DI, XML 사용
        AbstractApplicationContext context;
        //context = new GenericXmlApplicationContext("classpath:config/di03/application-config.xml");
        context = new AnnotationConfigApplicationContext(Appconfig.class);
        
        HelloController controller = context.getBean("helloController",HelloController.class);
        controller.hello("홍길동");
        context.close();
    }
}
