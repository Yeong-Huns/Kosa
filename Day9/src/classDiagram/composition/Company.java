package classDiagram.composition;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : classDiagram.composition
 * fileName       : Company
 * author         : Yeong-Huns
 * date           : 2024-04-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-08        Yeong-Huns       최초 생성
 */
class Company {

    // Reference to refer to list of books
    private String companyName;
    private List<Department> departments;

    // Constructor of Company class
    public Company(String companyName)
    {
        this.companyName = companyName;
        this.departments = new ArrayList<Department>();
    }

    // Method
    // to add new Department to the Company
    public void addDepartment(Department department)
    {
        departments.add(department);
    }

    public List<Department> getDepartments()
    {
        return new ArrayList<>(departments);
    }

    // Method
    // to get total number of Departments in the Company
    public int getTotalDepartments()
    {
        return departments.size();
    }
}
