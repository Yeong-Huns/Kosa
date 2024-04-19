package ObserverDiagram;

//관찰자 객체 구현
class ObserverA implements Observer {
    private boolean bPlay;
    private Publisher publisher;

    public ObserverA(Publisher publisher) {
        this.publisher = publisher;
        publisher.addObserver(this);
    }

    @Override
    public void notify(boolean play) {
        this.bPlay = play;
        myActControl();
    }

    public void myActControl() {
        if (bPlay) {
            System.out.println("MyClassA : 동작을 시작합니다.");
        } else {
            System.out.println("MyClassA : 동작을 정지합니다.");
        }
    }
}
