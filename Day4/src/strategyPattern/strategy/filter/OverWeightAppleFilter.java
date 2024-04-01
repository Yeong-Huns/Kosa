package strategyPattern.strategy.filter;

import strategyPattern.strategy.category.detail.Apple;

/**
 * packageName    : filter
 * fileName       : WeightFilter
 * author         : Yeong-Huns
 * date           : 2024-04-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-01        Yeong-Huns       최초 생성
 */
public class OverWeightAppleFilter implements AppleFilter {
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 80;
    }
}
