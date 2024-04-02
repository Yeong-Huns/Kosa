package appleFilter;

/**
 * packageName    : appleFilter
 * fileName       : OverWeightFilter
 * author         : Yeong-Huns
 * date           : 2024-04-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-02        Yeong-Huns       최초 생성
 */
public class OverWeightFilter implements AppleFilter{
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() >= 80;
    }
}
