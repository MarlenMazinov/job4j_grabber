package ru.job4j.ood.lsp.food;

import java.util.ArrayList;
import java.util.Calendar;
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

    public void distribute(Food food, List<Store> stores) {
        stores.forEach(store -> {
            if (store.accept(food)) {
                store.add(food);
            }
        });
    }

    public void qualify() {
        products.forEach(product -> distribute(product, stores));
    }
}
