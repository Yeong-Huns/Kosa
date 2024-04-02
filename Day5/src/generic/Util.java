package generic;

/**
 * packageName    : generic
 * fileName       : Util
 * author         : Yeong-Huns
 * date           : 2024-04-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-02        Yeong-Huns       최초 생성
 */
public class Util {
    public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2){
        return p1.getKey() == p2.getKey() && p1.getValue().equals(p2.getValue());
    }
}
