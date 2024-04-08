package classDiagram.myClassDiagram.MyComposition;

/**
 * packageName    : classDiagram.myClassDiagram.MyComposition
 * fileName       : Employee
 * author         : Yeong-Huns
 * date           : 2024-04-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-08        Yeong-Huns       최초 생성
 */
public class Employee {
    private AnnualLeaves annualLeaves;
    private int age;
    private String name;

    public Employee() {
        this.annualLeaves = new AnnualLeaves();
    }

    public Employee(int age, String name) {
        this.age = age;
        this.name = name;
        this.annualLeaves = new AnnualLeaves();
    }

    public void useAnnualLeaves(AnnualLeaves annualLeaves) {
        this.annualLeaves.UseAnnualLeaves();
    }
}
