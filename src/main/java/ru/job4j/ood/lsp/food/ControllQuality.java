package ru.job4j.ood.lsp.food;

import java.util.ArrayList;
import java.util.List;

/**
 * ukyuklyu
 */
public class ControllQuality {
    private List<Food> products;
    private List<Store> stores = new ArrayList<>();

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
        for (Food food : foodList) {
            distribute(food, stores);
        }
    }

    public void resort() {
        while (!stores.get(0).getProducts().isEmpty() || !stores.get(1).getProducts().isEmpty()) {
            List<Food> products = new ArrayList<>();
            for (Store store : stores) {
                products.addAll(store.clear());
            }
            products.forEach(product -> distribute(product, stores));
            products.clear();
        }
    }
}
