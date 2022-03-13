package ru.job4j.ood.lsp.food;

public class TrashQualifier implements Qualifier {
    @Override
    public void move(Food food) {
        Trash.add(food);
    }
}
