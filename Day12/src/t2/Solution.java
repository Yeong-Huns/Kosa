package t2;

/**
 * packageName    : t2
 * fileName       : Solution
 * author         : Yeong-Huns
 * date           : 2024-04-12
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-12        Yeong-Huns       최초 생성
 */
class Solution {
    public String solution(int age) {

         Integer.toString(age).chars().map(i->(char)(i+33)).forEach(System.out::print);
        return "";
    }
}
