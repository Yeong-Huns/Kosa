package strategyPattern.strategy.category.detail;

import strategyPattern.strategy.category.contryOrigin.Yesan;

/**
 * packageName    : apple.category.detail
 * fileName       : Apple2
 * author         : Yeong-Huns
 * date           : 2024-04-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-01        Yeong-Huns       최초 생성
 */
public class Apple2 extends Apple{
    public Apple2(String name, String color, int weight) {
        super(name, color, weight);
        countryOrigin = new Yesan();
    }
}
