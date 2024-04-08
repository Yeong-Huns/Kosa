package classDiagram.composition;

/**
 * packageName    : classDiagram.composition
 * fileName       : CompositonExample
 * author         : Yeong-Huns
 * date           : 2024-04-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-08        Yeong-Huns       최초 생성
 */
class CompositonExample {

    // Main driver method
    public static void main(String[] args)
    {
        // Creating a Company object
        Company techCompany = new Company("Tech Corp");

        techCompany.addDepartment(new Department("Engineering"));
        techCompany.addDepartment(new Department("Operations"));
        techCompany.addDepartment(new Department("Human Resources"));
        techCompany.addDepartment(new Department("Finance"));

        int d = techCompany.getTotalDepartments();
        System.out.println("Total Departments: " + d);

        System.out.println("Department names: ");
        for (Department dept : techCompany.getDepartments()) {
            System.out.println("- " + dept.getDepartmentName());
        }
    }
}
