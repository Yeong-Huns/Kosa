package ObserverDiagram;

// 이벤트 발생시키는 객체 인터페이스
interface Publisher{
    // 관찰자 객체 추가
    public void addObserver(Observer o);
    // 관찰자 객체 삭제
    public void deleteObserver(Observer o);
    // 관찰자들에게 이벤트 발생 전달
    public void notifyObserver();

    //옵저버들에게 변경사항을 전달
    void notifyObservers();
}

// 관찰자 객체 인터페이스
