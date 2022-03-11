package ru.job4j.design.srp;

import static org.junit.Assert.*;

import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.util.Calendar;

public class ReportForAccDepartmentToXmlTest {

    @Test
    public void whenReportIsCorrect() throws JAXBException {
        MemStore store = new MemStore();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 25, 23, 00, 00);
        date.set(Calendar.MILLISECOND, 0);
        Employee worker = new Employee("Ivan", date, date, 100);
        store.add(worker);
        Report report = new ReportForAccDepartmentToXml(store);
        String expect = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                + "<employers>\n"
                + "    <employee>\n"
                + "        <fired>2020-11-25T23:00:00+03:00</fired>\n"
                + "        <hired>2020-11-25T23:00:00+03:00</hired>\n"
                + "        <name>Ivan</name>\n"
                + "        <salary>10000.0</salary>\n"
                + "    </employee>\n"
                + "</employers>\n";
        assertEquals(expect, report.generate(em -> true));
    }
}