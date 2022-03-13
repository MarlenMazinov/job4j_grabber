package ru.job4j.ood.lsp.food;

import java.util.Calendar;
import java.util.List;

public class ControllQuality {
    private List<Food> list;
    private WarehouseQualifier warehouseQualifier = new WarehouseQualifier();
    private ShopQualifier shopQualifier = new ShopQualifier();
    private TrashQualifier trashQualifier = new TrashQualifier();
    private Calendar now = Calendar.getInstance();

    public ControllQuality(List<Food> list) {
        this.list = list;
    }

    public List<Food> getList() {
        return list;
    }

    public void setList(List<Food> list) {
        this.list = list;
    }

    public Calendar getNow() {
        return now;
    }

    public void qualify() {
        Food food;
        int counter = 0;
        while (counter < list.size()) {
            food = list.get(counter);
            double timeToExpiration = ((double) (food.getExpiryDate().getTimeInMillis()
                    - now.getTimeInMillis())) / ((double) (food.getExpiryDate().getTimeInMillis()
                    - food.getCreateDate().getTimeInMillis()));
            if (timeToExpiration > 0.75) {
                warehouseQualifier.move(food);
                counter++;
                continue;
            }
            if (timeToExpiration > 0.25) {
                shopQualifier.move(food);
                counter++;
                continue;
            }
            if (timeToExpiration > 0.001) {
                food.setDiscount(0.5f);
                shopQualifier.move(food);
                counter++;
                continue;
            }
            trashQualifier.move(food);
            counter++;
        }
        list = null;
    }
}
