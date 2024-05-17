package Observer;

import java.util.ArrayList;
import java.util.List;

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
public class NewContent implements Content{
    private String contentName;
    private int thumbs = 0;
    private List<String> comment = new ArrayList<>();

    @Override
    public void thumbsOn() {
        thumbs += 1;
    }

    @Override
    public void thumbsOff() {
        thumbs -= 1;
    }

    @Override
    public void addComment(String comment) {
        this.comment.add(comment);
    }

    @Override
    public void removeComment(String comment) {
        this.comment.remove(comment);
    }
}
