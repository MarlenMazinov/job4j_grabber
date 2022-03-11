package ru.job4j.ood.lsp;

import java.util.List;

public class Organization {
    private String name;
    private int staffSize;
    private List<Employee> employes;

    public Organization(String name, int staffSize) {
        this.name = name;
        this.staffSize = staffSize;
    }

    public void add(Employee employee) {
        if ("Russian".equals(employee.getCitizenship())) {
            employes.add(employee);
        }
    }
}
