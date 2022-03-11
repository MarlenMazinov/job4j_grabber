package ru.job4j.design.srp;

import static org.junit.Assert.*;

import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.util.Calendar;

public class ReportForHRDepartmentToJsonTest {
    @Test
    public void whenReportIsCorrect() throws JAXBException {
        MemStore store = new MemStore();
        Calendar dateForIvan = Calendar.getInstance();
        dateForIvan.set(2020, 10, 25, 23, 00, 00);
        Calendar dateForAnna = Calendar.getInstance();
        dateForAnna.set(2020, 05, 10, 23, 00, 00);
        Employee workerIvan = new Employee("Ivan", dateForIvan, dateForIvan, 100);
        Employee workerAnna = new Employee("Anna", dateForAnna, dateForAnna, 200);
        store.add(workerIvan);
        store.add(workerAnna);
        Report report = new ReportForHRDepartmentToJson(store);
        String expect = "\"[{\\\"name\\\":\\\"Anna\\\",\\\"salary\\\":200.0},"
                + "{\\\"name\\\":\\\"Ivan\\\",\\\"salary\\\":100.0}]\"";
        assertEquals(expect, report.generate(em -> true));
    }
}