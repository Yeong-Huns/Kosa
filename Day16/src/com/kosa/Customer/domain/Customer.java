package com.kosa.Customer.domain;

import java.time.LocalDate;

/**
 * packageName    : com.kosa.Employee.domain
 * fileName       : Employee
 * author         : Yeong-Huns
 * date           : 2024-04-18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-18        Yeong-Huns       최초 생성
 */

public class Customer {
    private long id;
    private String name;
    private long expense;
    private LocalDate registrationDate;


    public Customer(String name, long expense) {
        this.name = name;
        this.expense = expense;
    }


    public Customer(long id, String name, long expense, LocalDate registrationDate) {
        this.id = id;
        this.name = name;
        this.expense = expense;
        this.registrationDate = registrationDate;
    }
    public void updateExpense(int money) {
        this.expense += money;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getExpense() {
        return expense;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", expense=" + expense +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
