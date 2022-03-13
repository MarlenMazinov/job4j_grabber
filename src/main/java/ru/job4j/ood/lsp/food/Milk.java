package ru.job4j.ood.lsp.food;

import java.util.Calendar;
import java.util.Objects;

public class Milk extends Food {
    private String name;
    private Calendar createDate;
    private Calendar expiryDate;
    private float price;
    private float discount;

    public Milk(String name, Calendar createDate, Calendar expiryDate,
                float price, float discount) {
        this.name = name;
        this.createDate = createDate;
        this.expiryDate = expiryDate;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Calendar createDate) {
        this.createDate = createDate;
    }

    public Calendar getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Calendar expiryDate) {
        this.expiryDate = expiryDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Milk milk = (Milk) o;
        return Float.compare(milk.price, price) == 0 && Float.compare(milk.discount, discount)
                == 0 && Objects.equals(name, milk.name)
                && Objects.equals(createDate, milk.createDate)
                && Objects.equals(expiryDate, milk.expiryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, createDate, expiryDate, price, discount);
    }
}
