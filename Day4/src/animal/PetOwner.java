package animal;

/**
 * packageName    : animal
 * fileName       : PetOwner
 * author         : Yeong-Huns
 * date           : 2024-04-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-01        Yeong-Huns       최초 생성
 */
public class PetOwner {
    public void start(){
        Vet vet = new Vet();
        Dog dog = new Dog();
        Hippo hippo = new Hippo();
        vet.giveShot(dog);
        vet.giveShot(hippo);
        vet.giveShot(new Snake());
    }
}
