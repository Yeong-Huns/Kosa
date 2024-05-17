package com.kosa.strategy.filter;

import com.kosa.strategy.category.detail.Apple;

/**
 * packageName    : filter
 * fileName       : Filter
 * author         : Yeong-Huns
 * date           : 2024-04-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-01        Yeong-Huns       최초 생성
 */
public interface AppleFilter {
    boolean test(Apple apple);
}
