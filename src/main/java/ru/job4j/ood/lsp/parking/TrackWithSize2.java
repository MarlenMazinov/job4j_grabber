package ru.job4j.ood.lsp.parking;

public class TrackWithSize2 implements Car {
    private int size = 2;

    @Override
    public int getSize() {
        return size;
    }
}
