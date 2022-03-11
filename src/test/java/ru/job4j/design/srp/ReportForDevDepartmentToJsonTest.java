package ru.job4j.design.srp;

import static org.junit.Assert.*;

import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.util.Calendar;

public class ReportForDevDepartmentToJsonTest {

    @Test
    public void whenReportIsCorrect() throws JAXBException {
        MemStore store = new MemStore();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 25, 23, 00, 00);
        Employee worker = new Employee("Ivan", date, date, 100);
        store.add(worker);
        Report report = new ReportForDevDepartmentToJson(store);
        String expect = "\"[{\\\"name\\\":\\\"Ivan\\\",\\\"hired\\\":{\\\"year\\\":2020,"
                + "\\\"month\\\":10,\\\"dayOfMonth\\\":25,\\\"hourOfDay\\\":23,\\\"minute\\\":0,"
                + "\\\"second\\\":0},\\\"fired\\\":{\\\"year\\\":2020,\\\"month\\\":10,"
                + "\\\"dayOfMonth\\\":25,\\\"hourOfDay\\\":23,\\\"minute\\\":0,\\\"second\\\":0},"
                + "\\\"salary\\\":100.0}]\"";
        assertEquals(expect, report.generate(em -> true));
    }
}