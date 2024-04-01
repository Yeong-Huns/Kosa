package monster;

/**
 * packageName    : monster
 * fileName       : MonsterTestDriver
 * author         : Yeong-Huns
 * date           : 2024-04-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-01        Yeong-Huns       최초 생성
 */
public class MonsterTestDriver {
    public static void main(String[] args) {
        Monster[] monsters = new Monster[3];
        monsters[0] = new Vampire();
        monsters[1] = new Dragon();
        monsters[2] = new Monster();
        for(int i = 0; i < monsters.length; i++){
            monsters[i].frighten(i);
        }


    }
}
