package string;

/**
 * packageName    : string
 * fileName       : StringDemo6
 * author         : Yeong-Huns
 * date           : 2024-03-29
 * description    : intern()
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-29        Yeong-Huns       최초 생성
 */
public class StringDemo6 {
    public static void main(String[] args) {
        String s = new String("Sachin");
        String s2 = s.intern();
        System.out.println(s2); //Sachin
    }
}
