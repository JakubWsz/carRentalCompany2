package pl.kuba.domain.stores;

import java.util.Optional;
import pl.kuba.entities.Car;

public interface CarStore {

    Optional<Car> findById(Long id);

}
