package string;

/**
 * packageName    : string
 * fileName       : StringDemo8
 * author         : Yeong-Huns
 * date           : 2024-03-29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-29        Yeong-Huns       최초 생성
 */
public class StringDemo8 {
    public static void main(String[] args) {
        String s1 = "Java is a programming language. Java is a platform. Java is an Island.";
        String replaceString = s1.replace("Java", "kava");
        System.out.println(replaceString);
    }
}
