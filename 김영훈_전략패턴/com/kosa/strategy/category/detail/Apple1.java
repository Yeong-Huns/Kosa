package com.kosa.strategy.category.detail;

import com.kosa.strategy.category.contryOrigin.Deagu;

/**
 * packageName    : apple.category.detail
 * fileName       : Apple1
 * author         : Yeong-Huns
 * date           : 2024-04-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-01        Yeong-Huns       최초 생성
 */
public class Apple1 extends Apple{

    public Apple1(String name, String color, int weight) {
        super(name, color, weight);
        countryOrigin = new Deagu();
    }

}
