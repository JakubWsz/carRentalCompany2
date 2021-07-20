package pl.kuba.infrastructure.database.stores;

import org.springframework.stereotype.Component;
import pl.kuba.domain.stores.CarAvailabilityAsDatesStore;
import pl.kuba.entities.CarAvailabilityAsDates;
import pl.kuba.infrastructure.persistence.CarAvailabilityAsDatesRepository;

@Component
public class DatabaseCarAvailabilityAsDatesStore implements CarAvailabilityAsDatesStore {
    private final CarAvailabilityAsDatesRepository carAvailabilityAsDatesRepository;

    public DatabaseCarAvailabilityAsDatesStore(CarAvailabilityAsDatesRepository carAvailabilityAsDatesRepository) {
        this.carAvailabilityAsDatesRepository = carAvailabilityAsDatesRepository;
    }

    @Override
    public CarAvailabilityAsDates save(CarAvailabilityAsDates carAvailabilityAsDates) {
        return carAvailabilityAsDatesRepository.save(carAvailabilityAsDates);
    }
}
