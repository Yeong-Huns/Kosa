package Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : Observer
 * fileName       : Creator
 * author         : Yeong-Huns
 * date           : 2024-04-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-10        Yeong-Huns       최초 생성
 */
public class Creator {
    private String newContent;
    private List<Subscriber> subscribers = new ArrayList<>();
    public void upload(String content){
        this.newContent = content;
        this.alarm();
    }
    public void subscribe(Subscriber subscriber){
        this.subscribers.add(subscriber);
        this.alarm(this.subscribers.size());
    }

    public void unsubscribe(Subscriber subscriber){
        this.subscribers.remove(subscriber);
        this.alarm();
    }

    public void alarm(){
        subscribers.forEach(i->i.update(this.newContent));
    }
    public void alarm(int index){
        subscribers.forEach(i->i.update(index));
    }
}
