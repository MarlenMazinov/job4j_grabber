package ru.job4j.ood.lsp.parking;

public class TrackWithSize3 implements Car {
    private int size = 3;

    @Override
    public int getSize() {
        return size;
    }
}
