package ru.job4j.ood.isp;

public class Worker implements Employee {
    @Override
    public void makeJob() {
        System.out.println("Make job");
    }

    @Override
    public void makeReport() {
        throw new UnsupportedOperationException("Worker don't have to make reports");
    }
}
