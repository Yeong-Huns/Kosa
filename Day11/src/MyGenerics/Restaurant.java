package MyGenerics;

/**
 * packageName    : MyGenerics
 * fileName       : Restaurant
 * author         : Yeong-Huns
 * date           : 2024-04-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-11        Yeong-Huns       최초 생성
 */
public abstract class Restaurant <S,I>{
    private final S name;
    private final I price;

    public Restaurant(S name, I price) {
        this.name = name;
        this.price = price;
    }
    public S getName() {
        return name;
    }
    public I getPrice() {
        return price;
    }
}
