package ObserverDiagram;

//관찰자 객체 구현
class ObserverB implements Observer {
    private boolean bPlay;

    //객체를 생성할 때
    public ObserverB(Publisher publisher) {
        publisher.addObserver(this);
    }

    //이벤트 감지
    @Override
    public void notify(boolean play) {
        this.bPlay = play;
        myActControl();
    }

    //행위
    public void myActControl() {
        if (bPlay) {
            System.out.println("MyClassB : 동작을 시작합니다.");
        } else {
            System.out.println("MyClassB : 동작을 정지합니다.");
        }
    }
}

