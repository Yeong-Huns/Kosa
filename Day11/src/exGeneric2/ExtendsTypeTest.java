package exGeneric2;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : exGeneric2
 * fileName       : ExtendsTypeTest
 * author         : Yeong-Huns
 * date           : 2024-04-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-11        Yeong-Huns       최초 생성
 */
public class ExtendsTypeTest {
    public static void printPersonList(List<?> list){
        for(Object obj : list){
            System.out.println(((Person)obj).getName());
        }
    }
    public static void printEmployeeList(List<? extends Employee> list){
        for(Employee employee : list){
            System.out.println(employee.getSalary());
        }
    }

    public static void printStudentList(List<? extends Student> list){
        for(Student student : list){
            System.out.println(student.getGrade());
        }
    }

    public static void main(String[] args) {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("홍길동"));
        personList.add(new Person("이길동"));
        personList.add(new Person("김길동"));
        personList.add(new Person("동길동"));

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("홍길동", 1000000));
        employeeList.add(new Employee("이길동", 2000000));
        employeeList.add(new Employee("김길동", 3000000));
        employeeList.add(new Employee("동길동", 4000000));

        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("홍길동", 1));
        studentList.add(new Student("이길동", 2));
        studentList.add(new Student("김길동", 3));
        studentList.add(new Student("동길동", 4));

        List<College> collegeList = new ArrayList<>();
        collegeList.add(new College("홍길동",1,20));
        collegeList.add(new College("이길동",2,30));
        collegeList.add(new College("김길동",3,40));
        collegeList.add(new College("동길동",4,50));

        printPersonList(personList);
        printPersonList(employeeList);
        printPersonList(studentList);
        printPersonList(collegeList);

        System.out.println("========================");

        //printEmployeeList(personList);
        printEmployeeList(employeeList);
        //printEmployeeList(studentList);
        //printEmployeeList(collegeList);

        System.out.println("========================");

        printStudentList(studentList);
        printStudentList(collegeList);// -> student 의 하위타입이기에 가능

        System.out.println("=========================");

    }
}
