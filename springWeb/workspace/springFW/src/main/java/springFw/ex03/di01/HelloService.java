package springFw.ex03.di01;

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
public class HelloService implements IHelloService {
    @Override
    public String sayHello(String name) {
        System.out.println("HelloService.sayHello() 메서드 실행");
        return "Hello~~~~ " + name;
    }
}
