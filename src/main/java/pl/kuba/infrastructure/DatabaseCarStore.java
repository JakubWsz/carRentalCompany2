package pl.kuba.infrastructure;

import org.springframework.stereotype.Component;
import pl.kuba.domain.CarStore;
import pl.kuba.entities.Car;

import java.util.Optional;

@Component
class DatabaseCarStore implements CarStore {

    private final CarStore carRepository;

    DatabaseCarStore(CarStore carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Optional<Car> findById(Long id) {
        return carRepository.findById(id);
    }
}
