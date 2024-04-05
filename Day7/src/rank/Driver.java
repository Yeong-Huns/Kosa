package rank;

/**
 * packageName    : rank
 * fileName       : Driver
 * author         : Yeong-Huns
 * date           : 2024-04-04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-04        Yeong-Huns       최초 생성
 */
public class Driver {
    public static void main(String[] args) {

        // toString()
        System.out.println(Rank.GOLD.toString());   // -> 상수 이름 Return -> GOLD
        // values()
        Rank[] ranks = Rank.values(); //-> Enum 클래스가 가지고 있는 모든 상수 값(인스턴스)을 배열로 리턴
        // valueOf()
        Rank YourRank = Rank.valueOf("BRONZE");     // -> 전달받은 문자열과 일치하는 인스턴스 반환
        for(Rank r: ranks){
            System.out.println(r.toString() + "의 인덱스는 " +  r.ordinal() + " 입니다.");
        }
    }
}
