package pl.kuba.domain;

import java.util.Optional;
import pl.kuba.entities.Car;

public interface CarStore {

    Optional<Car> findById(Long id);

}
