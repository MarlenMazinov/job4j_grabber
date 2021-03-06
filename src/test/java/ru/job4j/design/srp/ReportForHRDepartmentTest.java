package ru.job4j.design.srp;

import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.util.Calendar;

import static org.junit.Assert.*;

public class ReportForHRDepartmentTest {

    @Test
    public void whenEmployersSorted() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee workerIvan = new Employee("Ivan", now, now, 100);
        Employee workerAnna = new Employee("Anna", now, now, 200);
        store.add(workerIvan);
        store.add(workerAnna);
        Report report = new ReportForHRDepartmentToString(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(workerAnna.getName()).append(";")
                .append(workerAnna.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(workerIvan.getName()).append(";")
                .append(workerIvan.getSalary()).append(";");
        assertEquals(report.generate(em -> true), expect.toString());
    }

    @Test
    public void whenEmployersNotSorted() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee workerIvan = new Employee("Ivan", now, now, 200);
        Employee workerAnna = new Employee("Anna", now, now, 100);
        store.add(workerIvan);
        store.add(workerAnna);
        Report report = new ReportForHRDepartmentToString(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(workerAnna.getName()).append(";")
                .append(workerAnna.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(workerIvan.getName()).append(";")
                .append(workerIvan.getSalary()).append(";");
        assertNotEquals(expect.toString(), report.generate(em -> true));
    }

    @Test
    public void whenEmployersHaveTwoFields() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee workerIvan = new Employee("Ivan", now, now, 100);
        store.add(workerIvan);
        Report report = new ReportForHRDepartmentToString(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(workerIvan.getName()).append(";")
                .append(workerIvan.getSalary()).append(";");
        assertEquals(report.generate(em -> true), expect.toString());
    }

    @Test
    public void whenEmployersHaveMoreFields() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee workerIvan = new Employee("Ivan", now, now, 100);
        store.add(workerIvan);
        Report report = new ReportForHRDepartmentToString(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(workerIvan.getName()).append(";")
                .append(workerIvan.getHired()).append(";")
                .append(workerIvan.getFired()).append(";")
                .append(workerIvan.getSalary()).append(";");
        assertNotEquals(report.generate(em -> true), expect.toString());
    }
}