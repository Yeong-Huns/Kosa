package rank;

/**
 * packageName    : rank
 * fileName       : Rank
 * author         : Yeong-Huns
 * date           : 2024-04-04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-04        Yeong-Huns       최초 생성
 */
public enum Rank {
    BRONZE(10, 1000),
    SILVER(20, 2000),
    GOLD(30, 3000);

    private final int win;
    private final int score;
    private int count;

    Rank(int win, int score) {  // default = private
        this.win = win;
        this.score = score;
    }

    public void plusCount(){    // 멀티쓰레드 환경에서 위험할 수 있음  -> 각 인스턴스의 count 가 공유되고 있기 때문
        this.count++;
    }

}
