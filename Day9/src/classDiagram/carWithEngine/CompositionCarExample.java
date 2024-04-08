package classDiagram.carWithEngine;

/**
 * packageName    : classDiagram.carWithEngine
 * fileName       : CompositionCarExample
 * author         : Yeong-Huns
 * date           : 2024-04-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-08        Yeong-Huns       최초 생성
 */
class CompositionCarExample {

    // Main driver method
    public static void main(String[] args)
    {
        // Creating a car object,
        // which will also initializes the engine class object
        Car car = new Car();

        // Making car to move by calling
        // move() method inside main()
        car.move();
    }
}