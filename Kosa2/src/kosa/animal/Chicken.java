package kosa.animal;

public class Chicken extends Animal{



    public Chicken(int size, int age, String name) {
        super(size, age, name, "닭");
    }

    public Chicken(String name) {
        super(name, "닭");
    }

    public void bark() {
        super.bark("꼬끼오 웁니다.");
    }
    public void move(){
        super.move("총총총");
    }
}
