package ru.job4j.design.srp;

import com.google.gson.GsonBuilder;

import java.util.function.Predicate;

public class ReportForDevDepartmentToJson implements Report {
    private Store store;

    public ReportForDevDepartmentToJson(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        var lib = new GsonBuilder().create();
        return lib.toJson(lib.toJson(store.findBy(filter)));
    }
}
