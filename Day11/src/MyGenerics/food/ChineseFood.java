package MyGenerics.food;

/**
 * packageName    : MyGenerics
 * fileName       : ChineseFood
 * author         : Yeong-Huns
 * date           : 2024-04-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-11        Yeong-Huns       최초 생성
 */
public class ChineseFood<S,I> extends Food<S,I>{
    private final S serviceMenu;
    public ChineseFood(S name, S serviceMenu, I price) {
        super(name, price);
        this.serviceMenu = serviceMenu;
    }
    public S getServiceMenu() {
        return serviceMenu;
    }
}
