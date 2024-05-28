package springFW.ex03.di01;

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
        //생성자
        //HelloController controller = new HelloController(new HelloService());

        //세터
        HelloController controller = new HelloController();
        controller.setHelloService(new HelloService());
        controller.hello("이연복");

        //spring DL, DI, XML 사용
        AbstractApplicationContext context = new GenericXmlApplicationContext("classpath:config/di01/application-config.xml");

        IHelloService helloService = context.getBean("helloService", IHelloService.class);
        System.out.println(helloService.sayHello("라따뚜이"));


        //생성자
        HelloController helloController = context.getBean("helloController", HelloController.class);
        helloController.hello("도레미");

        //Setter 사용
        HelloController helloController2 = context.getBean("helloController2", HelloController.class);
        helloController2.hello("파솔라");

        HelloController helloController3 = context.getBean("helloController3", HelloController.class);
        helloController3.hello("시도");
    }
}
