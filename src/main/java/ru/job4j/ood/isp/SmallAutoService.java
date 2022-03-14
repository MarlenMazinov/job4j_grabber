package ru.job4j.ood.isp;

public class SmallAutoService implements AutoService {

    @Override
    public void repairEngine() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void fixElectricianProblems() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void renovateCarBody() {
        System.out.println("Renovate car's body");
    }
}
