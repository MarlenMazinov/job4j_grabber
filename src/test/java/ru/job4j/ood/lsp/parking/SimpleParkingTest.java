package ru.job4j.ood.lsp.parking;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleParkingTest {

    @Test
    public void whenAddCarAndAvailableCarPlaces() {
        Car car = new SmallCar();
        SimpleParking parking = new SimpleParking(1, 0);
        assertTrue(parking.add(car));
    }

    @Test
    public void whenAddCarAndAvailableTruckPlaces() {
        Car car = new SmallCar();
        SimpleParking parking = new SimpleParking(0, 1);
        assertFalse(parking.add(car));
    }

    @Test
    public void whenAddCarAndNoAvailablePlaces() {
        Car car = new SmallCar();
        SimpleParking parking = new SimpleParking(0, 0);
        parking.add(car);
        assertFalse(parking.add(car));
    }

    @Test
    public void whenAddTruckSize2OnTruckPlace() {
        Car car = new Truck(2);
        SimpleParking parking = new SimpleParking(0, 1);
        assertTrue(parking.add(car));
    }

    @Test
    public void whenAddTruckSize3OnTruckPlace() {
        Car car = new Truck(3);
        SimpleParking parking = new SimpleParking(0, 1);
        assertTrue(parking.add(car));
    }

    @Test
    public void whenAddTruckSize2OnCarPlace() {
        Car car = new Truck(2);
        SimpleParking parking = new SimpleParking(2, 0);
        assertTrue(parking.add(car));
    }

    @Test
    public void whenAddTruckSize3OnCarPlace() {
        Car car = new Truck(3);
        SimpleParking parking = new SimpleParking(3, 0);
        assertTrue(parking.add(car));
    }

    @Test
    public void whenAddTruckSize2OnAndNoAvailablePlaces() {
        Car car = new Truck(2);
        SimpleParking parking = new SimpleParking(0, 0);
        assertFalse(parking.add(car));
    }

    @Test
    public void whenAddTruckSize3OnAndNoAvailablePlaces() {
        Car car = new Truck(3);
        SimpleParking parking = new SimpleParking(0, 0);
        assertFalse(parking.add(car));
    }

    @Test
    public void whenAddTruckSize2OnAndAvailable1CarPlace() {
        Car car = new Truck(2);
        SimpleParking parking = new SimpleParking(1, 0);
        assertFalse(parking.add(car));
    }

    @Test
    public void whenAddTruckSize3OnAndAvailable2CarPlace() {
        Car car = new Truck(3);
        SimpleParking parking = new SimpleParking(2, 0);
        assertFalse(parking.add(car));
    }

    @Test
    public void whenAddCarAndTrucks() {
        Car smallCar = new SmallCar();
        Car truckSize2 = new Truck(2);
        Car truckSize3 = new Truck(3);
        SimpleParking parking = new SimpleParking(6, 2);
        parking.add(smallCar);
        parking.add(truckSize2);
        parking.add(truckSize3);
        parking.add(truckSize2);
        assertTrue(parking.add(truckSize3));
        assertFalse(parking.add(truckSize3));
    }

}