package test;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : test
 * fileName       : CarImpl
 * author         : Yeong-Huns
 * date           : 2024-04-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-10        Yeong-Huns       최초 생성
 */
public class 경찰차 extends 자동차 {
    private List<자동차> 점검리스트;

    public 경찰차() {
        this.점검리스트 = new ArrayList();
    }
}
