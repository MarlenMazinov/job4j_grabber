package ru.job4j.ood.lsp.food;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.*;

public class ControllQualityTest {

    @Test
    public void whenMoveToWarehouse() {
        List<Food> list = new ArrayList<>();
        Calendar milkCreate = Calendar.getInstance();
        milkCreate.add(Calendar.DATE, -3);
        Calendar milkExp = Calendar.getInstance();
        milkExp.add(Calendar.DATE, 27);
        Food milk = new Milk("Milk", milkCreate, milkExp, 100f, 0f);
        list.add(milk);
        ControllQuality controllQuality = new ControllQuality(list);
        controllQuality.qualify();
        assertEquals(milk, Warehouse.getList().get(0));
    }

    @Test
    public void whenMoveToShopWithoutDiscount() {
        List<Food> list = new ArrayList<>();
        Calendar milkCreate = Calendar.getInstance();
        milkCreate.add(Calendar.DATE, -15);
        Calendar milkExp = Calendar.getInstance();
        milkExp.add(Calendar.DATE, 15);
        Food milk = new Milk("Milk", milkCreate, milkExp, 100f, 0f);
        list.add(milk);
        ControllQuality controllQuality = new ControllQuality(list);
        controllQuality.qualify();
        assertEquals(milk, Shop.getList().get(0));
        Shop.setList(new ArrayList<>());
    }

    @Test
    public void whenMoveToShopWithDiscount() {
        List<Food> list = new ArrayList<>();
        Calendar milkCreate = Calendar.getInstance();
        milkCreate.add(Calendar.DATE, -27);
        Calendar milkExp = Calendar.getInstance();
        milkExp.add(Calendar.DATE, 3);
        Food milk = new Milk("Milk", milkCreate, milkExp, 100f, 0f);
        list.add(milk);
        ControllQuality controllQuality = new ControllQuality(list);
        controllQuality.qualify();
        assertEquals(0.5f, Shop.getList().get(0).getDiscount(), 0.001);
        Shop.setList(new ArrayList<>());
    }

    @Test
    public void whenMoveToTrash() {
        List<Food> list = new ArrayList<>();
        ControllQuality controllQuality = new ControllQuality(list);
        Calendar milkCreate = Calendar.getInstance();
        milkCreate.add(Calendar.DATE, -30);
        Calendar milkExp = controllQuality.getNow();
        Food milk = new Milk("Milk", milkCreate, milkExp, 100f, 0f);
        list.add(milk);
        controllQuality.qualify();
        assertEquals(milk, Trash.getList().get(0));
    }
}