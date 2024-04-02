package codetest;

/**
 * packageName    : codetest
 * fileName       : main
 * author         : Yeong-Huns
 * date           : 2024-04-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-02        Yeong-Huns       최초 생성
 */
public class TestMain {
    public static void main(String[] args) {
        Valid valid = new Valid();
        String[] test = {"aya", "yee", "u", "maa", "wyeoo"};
        System.out.println(valid.getCount(test));

    }
}
