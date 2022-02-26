package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return returnValue(value, comparator, "max");
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return returnValue(value, comparator, "min");
    }

    public <T> T returnValue(List<T> value, Comparator<T> comparator, String returningValue) {
        value.sort(comparator);
        return "max".equals(returningValue) ? value.get(value.size() - 1)
                : value.get(0);
    }
}