package springFW.ex03.di03;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * packageName    : springFW.ex03.di03
 * fileName       : NIceService
 * author         : Yeong-Huns
 * date           : 2024-05-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-28        Yeong-Huns       최초 생성
 */
@Service
@Primary
public class NiceService implements IHelloService{
    @Override
    public String sayHello(String name) {
        System.out.println("NiceService.sayHello() 메서드 실행");
        return "Nice~~~~ " + name;
    }
}
