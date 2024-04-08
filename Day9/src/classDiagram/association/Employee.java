package classDiagram.association;

import lombok.Getter;

/**
 * packageName    : classDiagram
 * fileName       : Employee
 * author         : Yeong-Huns
 * date           : 2024-04-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-08        Yeong-Huns       최초 생성
 */
class Employee {

    // Attributes of employee
    private String name;

    // Constructor of Employee class
    public Employee(String name)
    {
        // this keyword refers to current instance
        this.name = name;
    }

    // Method of Employee class
    public String getEmployeeName()
    {
        // returning the name of employee
        return this.name;
    }
}
