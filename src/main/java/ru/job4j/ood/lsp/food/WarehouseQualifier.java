package ru.job4j.ood.lsp.food;

public class WarehouseQualifier implements Qualifier {
    @Override
    public void move(Food food) {
        Warehouse.add(food);
    }
}
