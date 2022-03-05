package ru.job4j.design.srp;

import java.util.Calendar;
import java.util.function.Predicate;

public class ReportForDevDepartment implements Report {

    private Store store;

    public ReportForDevDepartment(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("""
                <html lang="ru">
                <head>
                  <meta charset="UTF-8">
                  <title>Title 1</title>
                </head>
                <body>
                 Name; Hired; Fired; Salary""");
        for (Employee employee : store.findBy(filter)) {
            text.append(System.lineSeparator())
                    .append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary()).append(";");
        }
        text.append(System.lineSeparator())
                .append("</body>\n</html>");
        return text.toString();
    }

    public static void main(String[] args) {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        store.add(new Employee("Ivan", now, now, 100));
        ReportForDevDepartment reportForDevDepartment = new ReportForDevDepartment(store);
        System.out.println(reportForDevDepartment.generate(em -> true));
    }
}