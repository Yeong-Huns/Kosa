package MyGenerics.beverages;

/**
 * packageName    : MyGenerics
 * fileName       : Soda
 * author         : Yeong-Huns
 * date           : 2024-04-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-11        Yeong-Huns       최초 생성
 */
public class Soda<S,I> extends Beverages<S,I>{
    public Soda(S name, I price) {
        super(name, price, false);
    }
}
