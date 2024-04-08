package classDiagram.composition;

/**
 * packageName    : classDiagram.composition
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

    // Attributes of Department
    public String departmentName;

    // Constructor of Department class
    public Department(String departmentName)
    {
        this.departmentName = departmentName;
    }

    public String getDepartmentName()
    {
        return departmentName;
    }
}