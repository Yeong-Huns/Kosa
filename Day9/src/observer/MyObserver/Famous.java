package observer.MyObserver;

/**
 * packageName    : observer.MyObserver
 * fileName       : celebrity
 * author         : Yeong-Huns
 * date           : 2024-04-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-08        Yeong-Huns       최초 생성
 */
public interface Famous {
    void follow(Follower follower);
    void unfollow(Follower follower);
    void notifyFollower();
}
