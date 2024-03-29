package compare;

/**
 * packageName    : compare
 * fileName       : Compare
 * author         : Yeong-Huns
 * date           : 2024-03-29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-29        Yeong-Huns       최초 생성
 */
public class Compare {

    public static void main(String[] args) {
        String str1 = "apple"; //리터럴을 이용한 방식
        String str2 = "apple"; //리터럴을 이용한 방식
        String str3 = new String("apple"); //new 연산자를 이용한 방식
        String str4 = new String("apple"); //new 연산자를 이용한 방식


        System.out.println(str1 == str2);   //true
        System.out.println(str1 == str3);   //false
        System.out.println(str3 == str4);   //false

        System.out.println("=================================");

        System.out.println(str1.equals(str2));   //true
        System.out.println(str1.equals(str3));   //true
        System.out.println(str3.equals(str4));   //true

    }
}
