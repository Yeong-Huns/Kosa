package ObserverDiagram;

//main 테스트
public class ObserverDriveTest {
    public static void main(String[] args) {
        PlayController playController = new PlayController();
        Observer ob1 = new ObserverA(playController);
        Observer ob2 = new ObserverB(playController);
        //이벤트 발생
        playController.setFlag(true);

        //옵저버 삭제
        playController.deleteObserver(ob1);
        playController.setFlag(false);
    }
}
