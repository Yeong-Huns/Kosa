package MyGenerics.food;

import MyGenerics.Restaurant;

/**
 * packageName    : MyGenerics
 * fileName       : Food
 * author         : Yeong-Huns
 * date           : 2024-04-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-11        Yeong-Huns       최초 생성
 */
public abstract class Food<S, I> extends Restaurant<S,I> {
    public Food(S name, I price) {
        super(name, price);
    }
    public I getFoodPrice(){
        return super.getPrice();
    }
}
