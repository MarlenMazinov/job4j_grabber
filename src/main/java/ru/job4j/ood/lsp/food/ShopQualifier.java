package ru.job4j.ood.lsp.food;

public class ShopQualifier implements Qualifier {
    @Override
    public void move(Food food) {
        Shop.add(food);
    }
}
