package car;

/**
 * packageName    : car
 * fileName       : Mini
 * author         : Yeong-Huns
 * date           : 2024-04-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-02        Yeong-Huns       최초 생성
 */
public class Mini extends Car{
    private Color color;
    public Mini() {
        this(Color.RED);
    }
    public Mini(Color color) {
        super("Mini");
        this.color = color;
    }
    /*
    public Mini(int size){
        this(Color.RED);
        super(size);
    } -> 오류 because : this 나 super 둘 중에 하나만 사용가능
    */
}
