package classDiagram.association;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * packageName    : classDiagram
 * fileName       : Bank
 * author         : Yeong-Huns
 * date           : 2024-04-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-08        Yeong-Huns       최초 생성
 */
class Bank {

    // Attributes of bank
    private String bankName;
    private Set<Employee> employees;

    // Constructor of Bank class
    public Bank(String bankName)
    {
        this.bankName = bankName;
    }

    // Method of Bank class
    public String getBankName()
    {
        // Returning name of bank
        return this.bankName;
    }

    public void setEmployees(Set<Employee> employees)
    {
        this.employees = employees;
    }

    public Set<Employee> getEmployees()
    {
        return this.employees;
    }
}