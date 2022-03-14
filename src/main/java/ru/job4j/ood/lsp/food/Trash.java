package ru.job4j.ood.lsp.food;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Trash implements Store {
    private List<Food> list = new ArrayList<>();
    private Calendar now;

    public Trash(Calendar now) {
        this.now = now;
    }

    @Override
    public boolean accept(Food food) {
        return getExpirationPercent(food, now) <= 0.001;
    }

    @Override
    public List<Food> getProducts() {
        return new ArrayList<>(list);
    }

    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        if (accept(food)) {
            list.add(food);
            rsl = true;
        }
        return rsl;
    }
}
