package MyGenerics.beverages;

import MyGenerics.Restaurant;

/**
 * packageName    : MyGenerics
 * fileName       : Beverages
 * author         : Yeong-Huns
 * date           : 2024-04-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-11        Yeong-Huns       최초 생성
 */
public abstract class Beverages<S,I> extends Restaurant<S,I> {
    private final boolean isAlcohol;
    public Beverages(S name, I price, boolean isAlcohol) {
        super(name, price);
        this.isAlcohol = isAlcohol;
    }
    public boolean isAlcohol() {
        return isAlcohol;
    }
}
