package ru.job4j.ood.lsp.food;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ControllQuality {
    private List<Store> stores = new ArrayList<>();
    private List<Food> products;
    private Calendar now;

    public ControllQuality(List<Food> products) {
        this.products = products;
        this.now = Calendar.getInstance();
        stores.add(new Warehouse(now));
        stores.add(new Shop(now));
        stores.add(new Trash(now));
    }

    public List<Store> getStores() {
        return stores;
    }

    public Calendar getNow() {
        return now;
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
