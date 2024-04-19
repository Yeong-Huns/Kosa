package ObserverDiagram;

import java.util.ArrayList;
import java.util.List;

//이벤트 객체 구현
class PlayController implements Publisher {
    private List<Observer> observers = new ArrayList<>();
    private boolean play;
    private Observer myOb;

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void deleteObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObserver() {

    }

    //옵저버들에게 변경사항을 전달
    @Override
    public void notifyObservers() {
        //옵저버 목록들에게 이벤트 전달
        for (int i=0; i<observers.size(); i++) {
            observers.get(i).notify(play);
        }
    }

    //이벤트 발생 함수
    public void setFlag(boolean play) {
        this.play = play;
        notifyObservers();
    }

    public boolean getFlag() {
        return play;
    }
}
