package ru.job4j.ood.lsp;

import java.util.List;

/*
Класс SimpleOrganization нарушает правило "Инварианты (Invariants) —
все условия базового класса также должны быть сохранены и в подклассе".
Базовый класс Organization требует чтобы все сотрудники организации, в том числе и наследующихся
от него, имели сотрудников только с гражданством РФ. Это условие проверяется в методе add()
перед тем как сотрудник будет добавлен в список сотрудников организации.
 */
public class SimpleOrganization extends Organization {
    private String name;
    private int staffSize;
    private List<Employee> employes;

    public SimpleOrganization(String name, int staffSize, String name1, int staffSize1) {
        super(name, staffSize);
        this.name = name1;
        this.staffSize = staffSize1;
    }

    @Override
    public void add(Employee employee) {
        employes.add(employee);
    }
}
