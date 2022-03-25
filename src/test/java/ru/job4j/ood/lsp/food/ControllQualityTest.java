package ru.job4j.ood.lsp.food;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.*;

public class ControllQualityTest {

    @Test
    public void whenMoveToWarehouse() {
        List<Food> products = new ArrayList<>();
        List<Store> stores = new ArrayList<>(3);
        stores.add(new Warehouse());
        stores.add(new Shop());
        stores.add(new Trash());
        Calendar milkCreate = Calendar.getInstance();
        milkCreate.add(Calendar.DATE, -3);
        Calendar milkExp = Calendar.getInstance();
        milkExp.add(Calendar.DATE, 27);
        Food milk = new Milk("Milk", milkCreate, milkExp, 100f, 0f);
        products.add(milk);
        ControllQuality controllQuality = new ControllQuality(products, stores);
        controllQuality.qualify(products);
        assertTrue(stores.get(0).getProducts().contains(milk));
    }

    @Test
    public void whenMoveToShopWithoutDiscount() {
        List<Food> products = new ArrayList<>();
        List<Store> stores = new ArrayList<>(3);
        stores.add(new Warehouse());
        stores.add(new Shop());
        stores.add(new Trash());
        Calendar milkCreate = Calendar.getInstance();
        milkCreate.add(Calendar.DATE, -15);
        Calendar milkExp = Calendar.getInstance();
        milkExp.add(Calendar.DATE, 15);
        Food milk = new Milk("Milk", milkCreate, milkExp, 100f, 0f);
        products.add(milk);
        ControllQuality controllQuality = new ControllQuality(products, stores);
        controllQuality.qualify(products);
        assertTrue(stores.get(1).getProducts().contains(milk));
    }

    @Test
    public void whenMoveToShopWithDiscount() {
        List<Food> products = new ArrayList<>();
        List<Store> stores = new ArrayList<>(3);
        stores.add(new Warehouse());
        stores.add(new Shop());
        stores.add(new Trash());
        Calendar milkCreate = Calendar.getInstance();
        milkCreate.add(Calendar.DATE, -27);
        Calendar milkExp = Calendar.getInstance();
        milkExp.add(Calendar.DATE, 3);
        Food milk = new Milk("Milk", milkCreate, milkExp, 100f, 0.25f);
        products.add(milk);
        ControllQuality controllQuality = new ControllQuality(products, stores);
        controllQuality.qualify(products);
        assertTrue(stores.get(1).getProducts().contains(milk) && milk.getPrice() == 75f);
    }

    @Test
    public void whenMoveToTrash() {
        List<Food> products = new ArrayList<>();
        List<Store> stores = new ArrayList<>(3);
        stores.add(new Warehouse());
        stores.add(new Shop());
        stores.add(new Trash());
        ControllQuality controllQuality = new ControllQuality(products, stores);
        Calendar milkCreate = Calendar.getInstance();
        milkCreate.add(Calendar.DATE, -30);
        Calendar milkExp = Calendar.getInstance();
        Food milk = new Milk("Milk", milkCreate, milkExp, 100f, 0f);
        products.add(milk);
        controllQuality.qualify(products);
        assertTrue(stores.get(2).getProducts().contains(milk));
    }
}