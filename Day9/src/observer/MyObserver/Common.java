package observer.MyObserver;

/**
 * packageName    : observer.MyObserver
 * fileName       : Common
 * author         : Yeong-Huns
 * date           : 2024-04-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-08        Yeong-Huns       최초 생성
 */
public class Common implements Follower{
    private String name;

    public Common(String name) {
        this.name = name;
    }

    @Override
    public void update(String CelebrityName ,String post) {
        System.out.println("new Post : " + CelebrityName + " -> " + name + " : " + post);
    }
}
