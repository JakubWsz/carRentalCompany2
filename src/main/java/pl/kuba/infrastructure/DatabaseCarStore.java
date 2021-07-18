package pl.kuba.infrastructure;

import java.util.Optional;
import org.springframework.stereotype.Service;
import pl.kuba.domain.CarStore;
import pl.kuba.entities.Car;

@Service
class DatabaseCarStore implements CarStore {

    private final CarRepository carRepository;

    DatabaseCarStore(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Optional<Car> findById(Long id) {
        return carRepository.findById(id);
    }
}
