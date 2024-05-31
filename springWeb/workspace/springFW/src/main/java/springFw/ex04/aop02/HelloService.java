package springFw.ex04.aop02;

import org.springframework.stereotype.Service;

/**
 * packageName    : springFW.ex03.di01
 * fileName       : HelloService
 * author         : Yeong-Huns
 * date           : 2024-05-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-28        Yeong-Huns       최초 생성
 */
@Service
public class HelloService implements IHelloService {
    @Override
    public String sayHello(String name) {
        return "Hello~~~~ " + name;
    }

    @Override
    public String sayGoodbye(String name) {
        return "Bye~~~~ " + name;
    }
}
