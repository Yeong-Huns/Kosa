package filter;

/**
 * packageName    : filter
 * fileName       : Apple
 * author         : Yeong-Huns
 * date           : 2024-04-03
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-03        Yeong-Huns       최초 생성
 */
public class Apple {


    private int weight;
    private Color color;

    private String name;
    public Apple(int weight, Color color, String name) {
        this.weight = weight;
        this.color = color;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public Color getColor() {
        return color;
    }

    public void printApple(){
        System.out.println("무게 : " + weight + " 색상 : " + color + " 산지 : " + name);
    }
}
