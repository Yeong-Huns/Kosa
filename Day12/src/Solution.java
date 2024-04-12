import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * packageName    : PACKAGE_NAME
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
    public int[] solution(int[] numbers, int num1, int num2) {
        return IntStream.rangeClosed(num1, num2).map(i->numbers[i]).toArray();
    }
}
