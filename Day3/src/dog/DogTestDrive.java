package dog;

/**
 * packageName    : dog
 * fileName       : DogTestDrive
 * author         : Yeong-Huns
 * date           : 2024-03-29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-29        Yeong-Huns       최초 생성
 */
public class DogTestDrive {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.name = "Bart";
        dog.bark();

        Dog[] myDogs = new Dog[3];
        myDogs[0] = new Dog();
        myDogs[1] = new Dog();
        myDogs[2] = dog;

        myDogs[0].name = "Fred";
        myDogs[1].name = "Marge";

        // what is myDogs[2] nmae? -> Bart
        System.out.print("last dog's name is ");
        System.out.println(myDogs[2].name);

        for (Dog myDog : myDogs) {
            myDog.bark();
        }
    }
}
