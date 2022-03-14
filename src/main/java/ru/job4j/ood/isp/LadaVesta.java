package ru.job4j.ood.isp;

public class LadaVesta implements Car {
    @Override
    public void run() {
        System.out.println("Run");
    }

    @Override
    public void stop() {
        System.out.println("Stop");
    }

    @Override
    public void fill() {
        System.out.println("Fill tank by petrol");
    }
}
