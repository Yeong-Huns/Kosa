package classDiagram.myClassDiagram.myAggregation;

/**
 * packageName    : classDiagram.myClassDiagram.myAggregation
 * fileName       : Computer
 * author         : Yeong-Huns
 * date           : 2024-04-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-08        Yeong-Huns       최초 생성
 */
public class Computer {
    private String MouseName;
    private String KeyboardName;

    public void setMyComputer(KeyBoard keyBoard, Mouse mouse){
        KeyboardName = keyBoard.getModelName();
        MouseName = mouse.getModelName();
    }
}
