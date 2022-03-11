package ru.job4j.design.srp;

import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ReportForHRDepartmentToJson implements Report {

    private Store store;

    public ReportForHRDepartmentToJson(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> list = new ArrayList<>();
        store.findBy(filter).forEach(el -> {
            list.add(new Employee(el.getName(), el.getSalary()));
        });
        list.sort(new ComparatorBySalary());
        var lib = new GsonBuilder().create();
        return lib.toJson(lib.toJson(list));
    }
}
