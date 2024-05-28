package springFW.ex03.di03;

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

    //Constructor 생성자 DI
    public HelloController(IHelloService helloService) {this.helloService = helloService;}

    //Setter를 이용한 DI
    public void setHelloService(IHelloService helloService) {    this.helloService = helloService;    }

    //팩토리 메서드 패턴


    public void hello(String name){System.out.println("HelloController : "+helloService.sayHello(name));}
}
