package ru.job4j.ood.isp;

public class BigAutoservice implements AutoService {

    @Override
    public void repairEngine() {
        System.out.println("Repair engine");
    }

    @Override
    public void fixElectricianProblems() {
        System.out.println("Fix problems");
    }

    @Override
    public void renovateCarBody() {
        System.out.println("Renovate car's body");
    }
}
