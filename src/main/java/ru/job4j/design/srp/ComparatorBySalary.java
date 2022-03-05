package ru.job4j.design.srp;

import java.util.Comparator;

public class ComparatorBySalary implements Comparator<Employee> {

    @Override
    public int compare(Employee e1, Employee e2) {
        return Double.compare(e2.getSalary(), e1.getSalary());
    }
}
