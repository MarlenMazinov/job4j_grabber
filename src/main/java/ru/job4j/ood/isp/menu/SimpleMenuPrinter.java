package ru.job4j.ood.isp.menu;

import java.util.Iterator;

public class SimpleMenuPrinter implements MenuPrinter {
    private Iterator<Menu.MenuItemInfo> iterator;

    @Override
    public void print(Menu menu) {
        menu.forEach(el -> {
            int numberLength = el.getNumber().length();
            if (numberLength > 2) {
                for (int i = 0; i < numberLength; i++) {
                    System.out.print("-");
                }
            }
            System.out.print(el.getNumber() + el.getName() + "\n");
        });
    }
}
