package springFW.ex04.aop01;

/**
 * packageName    : springFW.ex04.aop01
 * fileName       : HelloServiceProxy
 * author         : Yeong-Huns
 * date           : 2024-05-29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-29        Yeong-Huns       최초 생성
 */
public class HelloServiceProxy extends HelloService{
    @Override
    public String sayHello(String name) {
        HelloLog.log();
        return super.sayHello(name);
    }
}
