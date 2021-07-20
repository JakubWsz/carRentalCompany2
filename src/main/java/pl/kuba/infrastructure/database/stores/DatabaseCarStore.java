package pl.kuba.infrastructure.database.stores;

import org.springframework.stereotype.Component;
import pl.kuba.domain.stores.CarStore;
import pl.kuba.entities.Car;
import pl.kuba.infrastructure.persistence.CarRepository;

import java.util.List;
import java.util.Optional;

@Component
class DatabaseCarStore implements CarStore {

    private final CarRepository carRepository;

    DatabaseCarStore(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Optional<Car> findById(Long id) {
        return carRepository.findById(id);
    }

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }
}
