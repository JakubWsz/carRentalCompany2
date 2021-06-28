package pl.kuba.domain;

import pl.kuba.entities.BodyType;
import pl.kuba.entities.Car;

import java.util.List;
import java.util.stream.Collectors;


public class CarFilter {

    public List<Car> getSelectedCars(String brand, String model, BodyType bodyType, String color, List<Car> availableCars) {
        return availableCars.stream()
                .filter(car -> car.getBrand().equals(brand))
                .filter(car -> car.getModel().equals(model))
                .filter(car -> car.getBodyType() == (bodyType))
                .filter(car -> car.getColor().equals(color))
                .collect(Collectors.toList());
    }
}
