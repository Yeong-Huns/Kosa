package appleFilter;

/**
 * packageName    : appleFilter
 * fileName       : Apple
 * author         : Yeong-Huns
 * date           : 2024-04-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-02        Yeong-Huns       최초 생성
 */
public class Apple {
    private int weight;
    private Color color;

    public Apple(int weight, Color color) {
        this.weight = weight;
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public Color getColor() {
        return color;
    }

    public void getStatus(){
        System.out.println("무게 : "+this.weight + " 색깔 : " + this.getColor());
    }
}
