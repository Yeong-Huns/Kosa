package observer.MyObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : observer.MyObserver
 * fileName       : ElonMusk
 * author         : Yeong-Huns
 * date           : 2024-04-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-08        Yeong-Huns       최초 생성
 */
public class Celebrity implements Famous {
    private String name;
    private String post;
    private List<Follower> followers = new ArrayList<>();

    public Celebrity(String name) {
        this.name = name;
    }

    @Override
    public void follow(Follower follower) {
        followers.add(follower);
    }

    @Override
    public void unfollow(Follower follower) {
        followers.remove(follower);
    }
    @Override
    public void notifyFollower(){
        followers.forEach(i->i.update(this.name, this.post));
    }

    public void newPost(String post){
        this.post = post;
        notifyFollower();
    }
}
