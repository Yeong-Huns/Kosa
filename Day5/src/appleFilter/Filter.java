package appleFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : appleFilter
 * fileName       : Filter
 * author         : Yeong-Huns
 * date           : 2024-04-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-02        Yeong-Huns       최초 생성
 */
public class Filter {
    public List<Apple> filteredList(List<Apple> list, AppleFilter filter){
        List<Apple> result = new ArrayList<>();
        for(Apple apple: list){
            if(filter.test(apple)){
                result.add(apple);
            };
        }
        return result;
    }


}
