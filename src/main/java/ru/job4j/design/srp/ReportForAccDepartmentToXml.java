package ru.job4j.design.srp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.function.Predicate;

public class ReportForAccDepartmentToXml implements Report {

    public static final double COEFFICIENT = 100;
    private Store store;

    public ReportForAccDepartmentToXml(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Employers.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        List<Employee> list = store.findBy(filter);
        list.forEach(el -> {
            el.setSalary(el.getSalary() * COEFFICIENT);
        });
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(new Employers(list), writer);
            xml = writer.getBuffer().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xml;
    }
}