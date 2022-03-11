package ru.job4j.design.srp;

import java.util.function.Predicate;

public class ReportForAccDepartmentToString implements Report {

    public static final double COEFFICIENT = 100;
    private Store store;

    public ReportForAccDepartmentToString(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;");
        for (Employee employee : store.findBy(filter)) {
            text.append(System.lineSeparator())
                    .append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary() * COEFFICIENT).append(";");
        }
        return text.toString();
    }
}