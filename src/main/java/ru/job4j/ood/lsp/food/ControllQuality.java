package ru.job4j.ood.lsp.food;

import java.util.ArrayList;
import java.util.List;

public class ControllQuality {
    private List<Store> stores = new ArrayList<>();
    private List<Food> products;

    public ControllQuality(List<Food> products) {
        this.products = products;
        stores.add(new Warehouse());
        stores.add(new Shop());
        stores.add(new Trash());
    }

    public List<Store> getStores() {
        return stores;
    }

    public List<Food> getProducts() {
        return products;
    }

    public void distribute(Food food, List<Store> stores) {
        stores.forEach(store -> {
            if (store.accept(food)) {
                store.add(food);
            }
        });
    }

    public void qualify(List<Food> foodList) {
        for (int i = 0; i < foodList.size(); i++) {
            Food food = foodList.get(i);
            distribute(food, stores);
            foodList.remove(food);
        }
    }

    public void resort() {
        while (!stores.get(0).getProducts().isEmpty() || !stores.get(1).getProducts().isEmpty()) {
            for (Store store : stores) {
                List<Food> foods = store.getProducts();
                if (!foods.isEmpty()) {
                    qualify(foods);
                }
            }
        }
    }
}
