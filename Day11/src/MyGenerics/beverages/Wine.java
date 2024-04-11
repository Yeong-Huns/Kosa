package MyGenerics.beverages;

/**
 * packageName    : MyGenerics
 * fileName       : Wine
 * author         : Yeong-Huns
 * date           : 2024-04-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-11        Yeong-Huns       최초 생성
 */
public class Wine<S,I> extends Beverages<S,I>{
    public Wine(S name, I price) {
        super(name, price, true);
    }

}
