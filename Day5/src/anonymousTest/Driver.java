package anonymousTest;

/**
 * packageName    : anonymousTest
 * fileName       : Driver
 * author         : Yeong-Huns
 * date           : 2024-04-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-02        Yeong-Huns       최초 생성
 */
public class Driver {
    public static void main(String[] args) {
      Solution solution = new Solution();
      Solution.AddNum c = (a, b)-> a / b;
      Solution.calc(7, 8, c);
      int x = solution.solution(4,7);
      System.out.println(x);
    }

}
class Solution{

    interface AddNum{
        int addNum(int a, int b);
    }
    public static int calc(int a , int b, AddNum add){
        return add.addNum(a, b);
    }

    public int solution(int a, int b){

        return calc(a, b, (num1, num2)-> num1 * num2);

    }
}