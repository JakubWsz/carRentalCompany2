package pl.kuba.domain.repository;

import pl.kuba.domain.stores.CarStore;
import pl.kuba.entities.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestCarStore implements CarStore {
    private final List<Car> carList = new ArrayList<>();

    @Override
    public Optional<Car> findById(Long id) {
        return carList.stream()
                .filter(car1 -> car1.getId() == id)
                .findFirst();
    }

    @Override
    public Car save(Car car) {
        carList.add(car);
        return car;
    }

    @Override
    public List<Car> findAll() {
        return new ArrayList<>(carList);
    }
}
