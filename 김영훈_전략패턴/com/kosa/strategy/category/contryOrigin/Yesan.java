package com.kosa.strategy.category.contryOrigin;

/**
 * packageName    : apple.category
 * fileName       : Yesan
 * author         : Yeong-Huns
 * date           : 2024-04-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-01        Yeong-Huns       최초 생성
 */
public class Yesan implements CountryOrigin{
    @Override
    public void GetCountryOrigin() {
        System.out.println("예산 사과입니다.");
    }
}
