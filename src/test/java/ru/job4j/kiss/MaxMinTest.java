package ru.job4j.kiss;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class MaxMinTest {
    @Test
    public void whenFindMaxInteger() {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(0);
        list.add(5);
        MaxMin maxMin = new MaxMin();
        MyComparator myComparator = new MyComparator();
        assertEquals(maxMin.max(list, myComparator).intValue(), 5);
    }

    @Test
    public void whenFindMinInteger() {
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(0);
        list.add(1);
        MaxMin maxMin = new MaxMin();
        MyComparator myComparator = new MyComparator();
        assertEquals(maxMin.min(list, myComparator).intValue(), 0);
    }
}