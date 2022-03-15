package ru.job4j.ood.lsp.food;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Shop implements Store {
    private List<Food> list = new ArrayList<>();

    @Override
    public boolean accept(Food food) {
        return getExpirationPercent(food) <= 0.75 && getExpirationPercent(food) > 0.001;
    }

    @Override
    public List<Food> getProducts() {
        return new ArrayList<>(list);
    }

    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        if (accept(food)) {
            if (getExpirationPercent(food) <= 0.25) {
                food.setDiscount(0.5f);
            }
            list.add(food);
            rsl = true;
        }
        return rsl;
    }
}
