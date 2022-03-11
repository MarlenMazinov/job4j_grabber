package ru.job4j.design.srp;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "employers")
public class Employers {

    private List<Employee> employee;

    public Employers() {
    }

    public Employers(List<Employee> employers) {
        this.employee = employers;
    }

    public List<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }
}