package pl.kuba.domain;

import pl.kuba.entities.Rent;

public interface RentStore {
    Rent save(Rent rent);
}
