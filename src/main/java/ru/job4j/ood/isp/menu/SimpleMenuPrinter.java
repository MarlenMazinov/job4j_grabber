package ru.job4j.ood.isp.menu;

import java.util.Iterator;

public class SimpleMenuPrinter implements MenuPrinter {
    private Iterator<Menu.MenuItemInfo> iterator;
    private String result;

    public String getResult() {
        return result;
    }

    @Override
    public void print(Menu menu) {
        iterator = menu.iterator();
        StringBuilder sb = new StringBuilder();
        while (iterator.hasNext()) {
            Menu.MenuItemInfo menuItemInfo = iterator.next();
            int numberLength = menuItemInfo.getNumber().length();
            if (numberLength > 2) {
                for (int i = 0; i < numberLength; i++) {
                    sb.append("-");
                }
            }
            sb.append(menuItemInfo.getNumber() + menuItemInfo.getName() + "\n");
        }
        result = sb.toString();
        System.out.println(result);
    }
}
