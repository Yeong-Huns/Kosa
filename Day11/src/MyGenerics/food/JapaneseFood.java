package MyGenerics.food;

/**
 * packageName    : MyGenerics
 * fileName       : JapaneseFood
 * author         : Yeong-Huns
 * date           : 2024-04-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-11        Yeong-Huns       최초 생성
 */
public class JapaneseFood<S,I,B> extends Food<S,I> {
    private final B serviceSoup;
    public JapaneseFood(S name, I price, B serviceSoup) {
        super(name, price);
        this.serviceSoup = serviceSoup;
    }

    public B getServiceSoup() {
        return serviceSoup;
    }
}
