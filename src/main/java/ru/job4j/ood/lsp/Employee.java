package ru.job4j.ood.lsp;

public class Employee {
    private String name;
    private int age;
    private String citizenship;

    public Employee(String name, int age, String citizenship) {
        this.name = name;
        this.age = age;
        this.citizenship = citizenship;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }
}
