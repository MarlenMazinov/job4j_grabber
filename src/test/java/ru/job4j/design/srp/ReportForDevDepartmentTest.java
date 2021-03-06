package ru.job4j.design.srp;

import org.junit.Test;

import javax.xml.bind.JAXBException;

import static org.junit.Assert.*;

import java.util.Calendar;

public class ReportForDevDepartmentTest {

    @Test
    public void whenHtmlFormatCorrect() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report report = new ReportForDevDepartmentToString(store);
        StringBuilder expect = new StringBuilder()
                .append("""
                        <html lang="ru">
                        <head>
                          <meta charset="UTF-8">
                          <title>Title 1</title>
                        </head>
                        <body>
                         Name; Hired; Fired; Salary""")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator())
                .append("</body>\n</html>");
        assertEquals(report.generate(em -> true), expect.toString());
    }

    @Test
    public void whenHtmlFormatNotCorrect() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report report = new ReportForDevDepartmentToString(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";");
        assertNotEquals(expect.toString(), report.generate(em -> true));
    }
}