package ru.job4j.ood.lsp.food;

import java.util.Calendar;
import java.util.List;

public interface Store {

    boolean accept(Food food);

    List<Food> getProducts();

    boolean add(Food food);

    default double getExpirationPercent(Food food) {
        return ((double) (food.getExpiryDate().getTimeInMillis()
                - Calendar.getInstance().getTimeInMillis()))
                / ((double) (food.getExpiryDate().getTimeInMillis()
                - food.getCreateDate().getTimeInMillis()));
    }
}
