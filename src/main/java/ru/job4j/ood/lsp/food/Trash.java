package ru.job4j.ood.lsp.food;

import java.util.ArrayList;
import java.util.List;

public class Trash {
    private static List<Food> list = new ArrayList<>();

    public static List<Food> getList() {
        return list;
    }

    public static void setList(List<Food> list) {
        Trash.list = list;
    }

    public static void add(Food food) {
        list.add(food);
    }
}
