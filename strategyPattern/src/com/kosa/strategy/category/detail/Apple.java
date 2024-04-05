package com.kosa.strategy.category.detail;

import com.kosa.strategy.category.contryOrigin.CountryOrigin;
import com.kosa.strategy.filter.AppleFilter;

/**
 * packageName    : apple.category.detail
 * fileName       : Apple
 * author         : Yeong-Huns
 * date           : 2024-04-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-01        Yeong-Huns       최초 생성
 */
public abstract class Apple {
    CountryOrigin countryOrigin;
    AppleFilter appleFilter;
    private int weight;
    private String name;
    private String color;
    public Apple(String name, String color, int weight) {
        this.name = name;
        this.color = color;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public String getColor() {
        return color;
    }

    public void getStatus(){
        System.out.println("name : " + this.name + " weigth : " + this.weight + " color : " + this.color);
        countryOrigin.getCountryOrigin();
    }

}
