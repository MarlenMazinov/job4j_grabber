package ru.job4j.ood.lsp.food;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {
    private static List<Food> list = new ArrayList<>();

    public static List<Food> getList() {
        return list;
    }

    public static void setList(List<Food> list) {
        Warehouse.list = list;
    }

    public static void add(Food food) {
        list.add(food);
    }
}
