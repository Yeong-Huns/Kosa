package kosa.animal;

public class Tiger extends Animal{
    public Tiger(int size, int age, String name) {
        super(size, age, name, "호랑이");
    }

    public Tiger(String name) {
        super(name, "호랑이");
    }

    public void bark() {
        super.bark("어흥어흥 포효합니다.");
    }
    public void move(){
        super.move("어슬렁어슬렁");
    }
}
