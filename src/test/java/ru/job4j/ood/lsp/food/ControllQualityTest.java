package ru.job4j.ood.lsp.food;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.*;

public class ControllQualityTest {

    @Test
    public void whenMoveToWarehouse() {
        List<Food> products = new ArrayList<>();
        Calendar milkCreate = Calendar.getInstance();
        milkCreate.add(Calendar.DATE, -3);
        Calendar milkExp = Calendar.getInstance();
        milkExp.add(Calendar.DATE, 27);
        Food milk = new Milk("Milk", milkCreate, milkExp, 100f, 0f);
        products.add(milk);
        ControllQuality controllQuality = new ControllQuality(products);
        controllQuality.qualify(controllQuality.getProducts());
        int index = controllQuality.getStores().get(0).getProducts().indexOf(milk);
        assertEquals(milk, controllQuality.getStores().get(0).getProducts().get(0));
    }

    @Test
    public void whenMoveToShopWithoutDiscount() {
        List<Food> products = new ArrayList<>();
        Calendar milkCreate = Calendar.getInstance();
        milkCreate.add(Calendar.DATE, -15);
        Calendar milkExp = Calendar.getInstance();
        milkExp.add(Calendar.DATE, 15);
        Food milk = new Milk("Milk", milkCreate, milkExp, 100f, 0f);
        products.add(milk);
        ControllQuality controllQuality = new ControllQuality(products);
        controllQuality.qualify(controllQuality.getProducts());
        int index = controllQuality.getStores().get(1).getProducts().indexOf(milk);
        assertEquals(milk, controllQuality.getStores().get(1).getProducts().get(index));
    }

    @Test
    public void whenMoveToShopWithDiscount() {
        List<Food> products = new ArrayList<>();
        Calendar milkCreate = Calendar.getInstance();
        milkCreate.add(Calendar.DATE, -27);
        Calendar milkExp = Calendar.getInstance();
        milkExp.add(Calendar.DATE, 3);
        Food milk = new Milk("Milk", milkCreate, milkExp, 100f, 0.25f);
        products.add(milk);
        ControllQuality controllQuality = new ControllQuality(products);
        controllQuality.qualify(controllQuality.getProducts());
        int index = controllQuality.getStores().get(1).getProducts().indexOf(milk);
        assertEquals(25f,
                controllQuality.getStores().get(1).getProducts().get(index).getPrice(), 0.001);
    }

    @Test
    public void whenMoveToTrash() {
        List<Food> products = new ArrayList<>();
        ControllQuality controllQuality = new ControllQuality(products);
        Calendar milkCreate = Calendar.getInstance();
        milkCreate.add(Calendar.DATE, -30);
        Calendar milkExp = Calendar.getInstance();
        Food milk = new Milk("Milk", milkCreate, milkExp, 100f, 0f);
        products.add(milk);
        controllQuality.qualify(controllQuality.getProducts());
        int index = controllQuality.getStores().get(2).getProducts().indexOf(milk);
        assertEquals(milk, controllQuality.getStores().get(2).getProducts().get(index));
    }
}