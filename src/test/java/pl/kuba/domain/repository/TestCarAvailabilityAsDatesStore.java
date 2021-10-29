package pl.kuba.domain.repository;

import pl.kuba.domain.stores.CarAvailabilityAsDatesStore;
import pl.kuba.entities.CarAvailabilityAsDates;

import java.util.ArrayList;
import java.util.List;

public class TestCarAvailabilityAsDatesStore implements CarAvailabilityAsDatesStore {
    List<CarAvailabilityAsDates> carAvailabilityAsDatesList = new ArrayList<>();

    @Override
    public CarAvailabilityAsDates save(CarAvailabilityAsDates carAvailabilityAsDates) {
        carAvailabilityAsDatesList.add(carAvailabilityAsDates);
        return carAvailabilityAsDates;
    }
}
