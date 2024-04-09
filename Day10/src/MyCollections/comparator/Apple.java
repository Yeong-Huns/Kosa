package MyCollections.comparator;

/**
 * packageName    : MyCollections.arrayList
 * fileName       : Apple
 * author         : Yeong-Huns
 * date           : 2024-04-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-09        Yeong-Huns       최초 생성
 */
public class Apple {
    private Color color;
    private int weight;
    private int price;
    public Apple(Color color, int weight, int price) {
        this.color = color;
        this.weight = weight;
        this.price = price;
    }
    public String getColor() {
        return color.toString();
    }
    public int getPrice(){
        return price;
    }
    public int getWeight() {
        return weight;
    }
    public void printApple(){
        System.out.println("Color: " + color.toString() + " Weight : " + weight + " Price : " + price);
    }
}
