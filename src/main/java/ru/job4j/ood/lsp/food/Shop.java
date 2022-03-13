package ru.job4j.ood.lsp.food;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    private static List<Food> list = new ArrayList<>();

    public static List<Food> getList() {
        return list;
    }

    public static void setList(List<Food> list) {
        Shop.list = list;
    }

    public static void add(Food food) {
        list.add(food);
    }
}
