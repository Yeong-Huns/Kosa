package MyGenerics.food;

/**
 * packageName    : MyGenerics
 * fileName       : KoreanFood
 * author         : Yeong-Huns
 * date           : 2024-04-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-11        Yeong-Huns       최초 생성
 */
public class KoreanFood<S,I> extends Food<S,I>{
    private final S sideDish;
    public KoreanFood(S name,S sideDish, I price) {
        super(name, price);
        this.sideDish = sideDish;
    }
    public S getSideDish() {
        return sideDish;
    }
}
