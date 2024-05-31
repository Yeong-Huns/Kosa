package springFw.ex04.aop02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * packageName    : springFW.ex03.di01
 * fileName       : HelloController
 * author         : Yeong-Huns
 * date           : 2024-05-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-28        Yeong-Huns       최초 생성
 */
@Controller
public class HelloController {
    @Autowired
    IHelloService helloService;

    public void hello(String name){
        System.out.println("HelloController : "+helloService.sayHello(name));
    }

    public void bye(String name){System.out.println("HelloController : "+helloService.sayGoodbye(name));}
}
