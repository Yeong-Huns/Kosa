package classDiagram.myClassDiagram.myAssociation;

import java.util.List;

/**
 * packageName    : classDiagram.myClassDiagram.myAssociation
 * fileName       : Person
 * author         : Yeong-Huns
 * date           : 2024-04-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-08        Yeong-Huns       최초 생성
 */
public class Person {
    private String name;
    private int age;
    private List<Habbit> habbitList;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public void setHabbit(String name){
        Habbit habbit = new Habbit();
        habbit.setHabbit(name);
        habbitList.add(habbit);
    }
}
