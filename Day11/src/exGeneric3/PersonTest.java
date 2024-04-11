package exGeneric3;

/**
 * packageName    : exGeneric3
 * fileName       : PersonTest
 * author         : Yeong-Huns
 * date           : 2024-04-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-11        Yeong-Huns       최초 생성
 */
public class PersonTest {
    public static void main(String[] args) {
        Student<String, Integer, Double> hong = new Student<>();
        hong.setName("홍길동");
        hong.setAge(20);
        hong.setGarde(2);
        hong.setHeight(178.3);
        System.out.println(hong);
    }
}
