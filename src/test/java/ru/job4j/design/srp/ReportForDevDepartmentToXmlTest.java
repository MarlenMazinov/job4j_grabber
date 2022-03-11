package ru.job4j.design.srp;

import static org.junit.Assert.*;

import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.util.Calendar;

public class ReportForDevDepartmentToXmlTest {

    @Test
    public void whenReportIsCorrect() throws JAXBException {
        MemStore store = new MemStore();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 25, 23, 00, 00);
        date.setTimeInMillis(0);
        Employee worker = new Employee("Ivan", date, date, 100);
        store.add(worker);
        Report report = new ReportForDevDepartmentToXml(store);
        String expect = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                + "<employers>\n"
                + "    <employee>\n"
                + "        <fired>1970-01-01T03:00:00+03:00</fired>\n"
                + "        <hired>1970-01-01T03:00:00+03:00</hired>\n"
                + "        <name>Ivan</name>\n"
                + "        <salary>100.0</salary>\n"
                + "    </employee>\n"
                + "</employers>\n";
        assertEquals(expect, report.generate(em -> true));
    }
}