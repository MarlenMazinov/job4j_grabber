package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class SimpleParking implements Parking {
    private List<Car> cars;
    private List<Car> trucks;
    private int freeCarPlaces;
    private int freeTruckPlaces;

    public SimpleParking(int carsSize, int trucksSize) {
        this.freeCarPlaces = carsSize;
        this.freeTruckPlaces = trucksSize;
        this.cars = new ArrayList<>(carsSize);
        this.trucks = new ArrayList<>(trucksSize);
    }

    @Override
    public boolean add(Car car) {
        boolean rsl = false;
        if (car.getSize() > Car.SMALL_CAR_SIZE) {
            if (freeTruckPlaces > 0) {
                trucks.add(car);
                freeTruckPlaces--;
                rsl = true;
            } else if (freeCarPlaces >= car.getSize()) {
                    for (int i = 0; i < car.getSize(); i++) {
                        cars.add(car);
                        freeCarPlaces--;
                        rsl = true;
                    }
                }
            } else if (freeCarPlaces-- > 0) {
                cars.add(car);
                rsl = true;
            }
        return rsl;
    }
}
