package goodDog;

/**
 * packageName    : goodDog
 * fileName       : GoodsDogTestDrive
 * author         : Yeong-Huns
 * date           : 2024-03-29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-29        Yeong-Huns       최초 생성
 */
public class GoodsDogTestDrive {
    public static void main(String[] args) {
        GoodDog dog1 = new GoodDog();
        GoodDog dog2 = new GoodDog();

        dog1.setSize(70);
        dog2.setSize(8);

        dog1.bark();
        dog2.bark();
    }
}
