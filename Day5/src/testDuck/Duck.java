package testDuck;

/**
 * packageName    : testDuck
 * fileName       : Duck
 * author         : Yeong-Huns
 * date           : 2024-04-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-02        Yeong-Huns       최초 생성
 */
public class Duck {
    private int kilos = 6;
    private float floatability = 2.1F;
    private String name = "Generic";
    private long[] feathers = {1,2,3,4,5,6,7};
    private boolean canFly = true;
    private int maxSpeed = 25;

    public Duck() {
        System.out.println("type 1 duck");
    }

    public Duck(boolean canFly) {
        this.canFly = canFly;
        System.out.println("type 2 duck");
    }

    public Duck(String name, long[] feathers) {
        this.name = name;
        this.feathers = feathers;
        System.out.println("type 3 duck");
    }

    public Duck(int kilos, float floatability) {
        this.kilos = kilos;
        this.floatability = floatability;
        System.out.println("type 4 duck");
    }

    public Duck(float floatability, int maxSpeed) {
        this.floatability = floatability;
        this.maxSpeed = maxSpeed;
        System.out.println("type 5 duck");
    }
}
