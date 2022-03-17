package ru.job4j.ood.dip;

import java.util.ArrayList;
import java.util.List;

/*
Поле List<Car> orderedCars содержит список машин, который необходимо отремонтировать автомеханику.
Класс CarMechanic нарушает принцип DIP, т.к. автомеханик может ремонтировать не только машины,
но и другие автотранспортные средства. Поэтому нужно создать абстракцию
и от нее сделать наследником класс CarMechanic. А поле  нужно private List<Car> orderedCars
заменить на private List<Vehicle> orderedVehicles, где Vehicle - это абстракция,
реализацией которой могут быть и машины, и мотоциклы и т.п.
Также у метода repairCar принимаемый на вход и возвращаемый тип объекта заменить на Vehicle.
 */
public class CarMechanic {
    private String name;
    private int experinceYears;
    private List<Car> orderedCars = new ArrayList<>();

    public Car repairCar(Car car) {
        /*
        some logic
         */
        return car;
    }

}
