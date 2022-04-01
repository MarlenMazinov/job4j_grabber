package ru.job4j.ood.lsp.food;

import java.util.List;
import java.util.stream.Collectors;

public class ControllQuality {
    private List<Store> stores;

    public ControllQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void distribute(Food food) {
        stores.forEach(store -> {
            if (store.accept(food)) {
                store.add(food);
            }
        });
    }

    public void qualify(List<Food> foodList) {
        for (Food food : foodList) {
            distribute(food);
        }
    }

    public void resort() {
        List<Food> allProducts = stores.stream().
                flatMap(store -> store.clear().stream()).collect(Collectors.toList());
        qualify(allProducts);
    }
}
