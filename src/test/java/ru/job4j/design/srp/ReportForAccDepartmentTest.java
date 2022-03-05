package ru.job4j.design.srp;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Calendar;

public class ReportForAccDepartmentTest {

    @Test
    public void whenSalaryRecalculationCorrect() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report report = new ReportForAccDepartment(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary() * 100).append(";");
        assertEquals(report.generate(em -> true), expect.toString());
    }

    @Test
    public void whenSalaryRecalculationNotCorrect() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report report = new ReportForAccDepartment(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";");
        assertNotEquals(report.generate(em -> true), expect.toString());
    }
}