package Observer;

/**
 * packageName    : Observer
 * fileName       : Subscriber
 * author         : Yeong-Huns
 * date           : 2024-04-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-10        Yeong-Huns       최초 생성
 */
public interface Subscriber {
    void update(String content);
    void update(int size);
}
