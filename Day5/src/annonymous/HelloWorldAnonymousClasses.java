package annonymous;

/**
 * packageName    : annonymous
 * fileName       : HelloWorldAnonymousClasses
 * author         : Yeong-Huns
 * date           : 2024-04-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-02        Yeong-Huns       최초 생성
 */
public class HelloWorldAnonymousClasses {

    interface HelloWorld<T>{
        public String greetSomeone(T t);
    }

    public static String sayHello(String str){
        HelloWorld<String> anonymousTest = new HelloWorld<String>() {
            @Override
            public String greetSomeone(String s) {
                return s + "테스트용";
            }
        };
        return anonymousTest.greetSomeone(str);
    }

    //public static String sayHello2(String str, new HelloWorld)

    public static void main(String[] args) {
        System.out.println(sayHello("bbangBang"));

    }
}


