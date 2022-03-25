package ru.job4j.ood.lsp.food;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ukyuklyu
 */
public class ControllQuality {
    private List<Food> products;
    private List<Store> stores;

    public ControllQuality(List<Food> products, List<Store> stores) {
        this.products = products;
        this.stores = stores;
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
        Map<Class<? extends Store>, Integer> map = new HashMap<>();
        stores.forEach(store -> map.put(store.getClass(), stores.indexOf(store)));
        if (map.containsKey(Warehouse.class) && map.containsKey(Shop.class)) {
            while (!stores.get(map.get(Warehouse.class)).getProducts().isEmpty()
                    || !stores.get(map.get(Shop.class)).getProducts().isEmpty()) {
                List<Food> products = new ArrayList<>();
                for (Store store : stores) {
                    products.addAll(store.clear());
                }
                products.forEach(product -> distribute(product, stores));
                products.clear();
            }
        }
    }
}
