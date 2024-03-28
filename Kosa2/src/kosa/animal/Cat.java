package kosa.animal;

public class Cat extends Animal{

    public Cat(int size, int age, String name) {
        super(size, age, name, "고양이");
    }

    public Cat(String name) {
        super(name, "고양이");
    }

    public void bark() {
        super.bark("야옹야옹 웁니다.");
    }
    public void move(){
        super.move("사뿐사뿐");
    }
}
