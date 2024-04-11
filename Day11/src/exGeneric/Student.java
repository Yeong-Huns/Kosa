package exGeneric;

/**
 * packageName    : MyGeneric
 * fileName       : Student
 * author         : Yeong-Huns
 * date           : 2024-04-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-11        Yeong-Huns       최초 생성
 */
public class Student extends Person {
    protected int grade;

    public Student() {
    }
    public Student(String name, int grade) {
        super.name = name;
        this.grade = grade;
    }
    public int getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "점수=" + grade +
                ", 이름='" + name + '\'' +
                '}';
    }
}
