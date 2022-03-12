package ru.job4j.design.srp;

import static org.junit.Assert.*;

import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class ReportForAccDepartmentToXmlTest {

    @Test
    public void whenReportIsCorrect() throws JAXBException {
        MemStore store = new MemStore();
        Calendar date = new GregorianCalendar(2022, Calendar.MARCH, 11);
        date.setTimeZone(TimeZone.getTimeZone(ZoneOffset.of("+3")));
        Employee worker = new Employee("Ivan", date, date, 100);
        store.add(worker);
        Report report = new ReportForAccDepartmentToXml(store);
        String expect = """
                <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                <employers>
                    <employee>
                        <fired>2022-03-11T00:00:00+03:00</fired>
                        <hired>2022-03-11T00:00:00+03:00</hired>
                        <name>Ivan</name>
                        <salary>10000.0</salary>
                    </employee>
                </employers>
                """;
        assertEquals(expect, report.generate(em -> true));
    }
}