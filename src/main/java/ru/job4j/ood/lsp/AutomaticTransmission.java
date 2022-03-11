package ru.job4j.ood.lsp;

public class AutomaticTransmission {
    private int maxGear;
    private int minGear;

    public AutomaticTransmission() {
    }

    public AutomaticTransmission(int maxGear, int minGear) {
        this.maxGear = maxGear;
        this.minGear = minGear;
    }

    public int toggle(int gear, boolean flag) {
        if (flag && gear < maxGear) {
            gear++;
        }
        if (!flag && gear > minGear) {
            gear--;
        } else {
            throw new IllegalArgumentException("Gear value is wrong!");
        }
        return gear;
    }
}
