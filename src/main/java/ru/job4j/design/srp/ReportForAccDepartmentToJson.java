package ru.job4j.design.srp;

import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.function.Predicate;

public class ReportForAccDepartmentToJson implements Report {

    public static final double COEFFICIENT = 100;
    private Store store;

    public ReportForAccDepartmentToJson(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> list = store.findBy(filter);
        list.forEach(el -> {
            el.setSalary(el.getSalary() * COEFFICIENT);
        });
        var lib = new GsonBuilder().create();
        return lib.toJson(lib.toJson(list));
    }
}