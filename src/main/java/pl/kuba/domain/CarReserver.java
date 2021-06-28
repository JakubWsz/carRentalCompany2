package pl.kuba.domain;

import pl.kuba.entities.BodyType;
import pl.kuba.entities.Car;

import java.util.ArrayList;
import java.util.List;

public class CarReserver {

    public Car reserveCar(List<Car> selectedCars, String brand, String model, BodyType bodyType, String color) {
        return selectedCars.stream()
                .filter(car -> car.getBrand().equals(brand))
                .filter(car -> car.getModel().equals(model))
                .filter(car -> car.getBodyType() == (bodyType))
                .filter(car -> car.getColor().equals(color))
                .findFirst()
                .get();
    }
}
