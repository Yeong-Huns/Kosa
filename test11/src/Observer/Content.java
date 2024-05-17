package Observer;

/**
 * packageName    : Observer
 * fileName       : Content
 * author         : Yeong-Huns
 * date           : 2024-04-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-10        Yeong-Huns       최초 생성
 */
public interface Content {
    void thumbsOn();
    void thumbsOff();
    void addComment(String comment);
    void removeComment(String comment);
}
