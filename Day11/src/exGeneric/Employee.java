package exGeneric;

/**
 * packageName    : MyGeneric
 * fileName       : Employee
 * author         : Yeong-Huns
 * date           : 2024-04-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-11        Yeong-Huns       최초 생성
 */
public class Employee extends Person{
    private int salary;

    public Employee(String name, int salary) {
        super(name);
        this.salary = salary;
    }
    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "급여=" + salary +
                ", 이름='" + name + '\'' +
                '}';
    }
}
