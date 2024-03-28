package kosa.chairWars;

public class ShapeDriver {
    public static void main(String[] args) {

        Square square = new Square();
        square.rotate();
        square.playSound();

        Amoeba amoeba = new Amoeba();
        amoeba.rotate();
        amoeba.playsound();

        Circle circle = new Circle();
        circle.rotate();
        circle.playSound();
    }
}
