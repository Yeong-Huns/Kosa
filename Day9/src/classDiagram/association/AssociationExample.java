package classDiagram.association;

import java.util.HashSet;
import java.util.Set;

/**
 * packageName    : classDiagram
 * fileName       : AssociationExample
 * author         : Yeong-Huns
 * date           : 2024-04-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-08        Yeong-Huns       최초 생성
 */
class AssociationExample {

    // Main driver method
    public static void main(String[] args)
    {
        // Creating Employee objects
        Employee emp1 = new Employee("Ridhi");
        Employee emp2 = new Employee("Vijay");

        // adding the employees to a set
        Set<Employee> employees = new HashSet<>();
        employees.add(emp1);
        employees.add(emp2);

        // Creating a Bank object
        Bank bank = new Bank("ICICI");

        // setting the employees for the Bank object
        bank.setEmployees(employees);

        // traversing and displaying the bank employees
        for (Employee emp : bank.getEmployees()) {
            System.out.println(emp.getEmployeeName()
                    + " belongs to bank "
                    + bank.getBankName());
        }
    }
}