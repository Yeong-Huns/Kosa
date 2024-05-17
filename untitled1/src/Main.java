import java.util.ArrayList;
import java.util.List;

/**
 * packageName    :
 * fileName       : ${NAME}
 * author         : Yeong-Huns
 * date           : 2024-04-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-05        Yeong-Huns       최초 생성
 */
public class Main {
    public static void main(String[] args) {

        List list = new ArrayList<>();
        list.add(1);
        list.add(1.23);
        list.add('야');
        list.add("야호");

        List<String> list2 = list;
        list2.add("add");
        for(Object i : list){
            System.out.println(i.getClass());
        }
        for(Object i : list2){
            System.out.println(i.getClass());
        }
        list2.add("test");
        list = list2;
        for(Object i : list2){
            System.out.println(i.getClass());
        }

    }
}