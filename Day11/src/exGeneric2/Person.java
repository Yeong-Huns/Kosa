package exGeneric2;

/**
 * packageName    : MyGeneric
 * fileName       : Person
 * author         : Yeong-Huns
 * date           : 2024-04-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-11        Yeong-Huns       최초 생성
 */
public class Person {
    protected String name;

    public Person(String name) {
        this.name = name;
    }

    public Person() {
    }
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return
                "이름= '" + name + '\'';
    }
}
