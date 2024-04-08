package classDiagram.aggregation;

import java.util.List;

/**
 * packageName    : classDiagram.student
 * fileName       : Department
 * author         : Yeong-Huns
 * date           : 2024-04-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-08        Yeong-Huns       최초 생성
 */
class Department {

    // Attributes of Department class
    private String deptName;
    private List<Student> students;

    // Constructor of Department class
    public Department(String deptName, List<Student> students)
    {
        this.deptName = deptName;
        this.students = students;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student)
    {
        students.add(student);
    }
}