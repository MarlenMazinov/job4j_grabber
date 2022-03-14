package ru.job4j.ood.isp;

public class Accountant implements Employee {
    @Override
    public void makeJob() {
        System.out.println("Make job");
    }

    @Override
    public void makeReport() {
        System.out.println("Make report");
    }
}
