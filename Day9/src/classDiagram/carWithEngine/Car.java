package classDiagram.carWithEngine;

/**
 * packageName    : classDiagram.carWithEngine
 * fileName       : Car
 * author         : Yeong-Huns
 * date           : 2024-04-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-08        Yeong-Huns       최초 생성
 */
class Car {

    // For a car to move,
    // it needs to have an engine.
    private final Engine engine;

    // Constructor of this class
    public Car()
    {
        this.engine = new Engine();   // Composition
    }

    // Method
    // Car start moving by starting engine
    public void move()
    {
        engine.work();
        System.out.println("Car is moving");
    }
}