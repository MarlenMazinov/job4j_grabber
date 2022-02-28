package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return sortList(value, comparator).get(value.size() - 1);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return sortList(value, comparator).get(0);
    }

    public <T> List<T> sortList(List<T> value, Comparator<T> comparator) {
        for (int i = 0; i < value.size() - 1; i++) {
            T tmp;
            T first = value.get(i);
            T second = value.get(i + 1);
            if (comparator.compare(first, second) > 0) {
                tmp = first;
                value.set(i, second);
                value.set((i + 1), tmp);
            }
        }
        return value;
    }
}