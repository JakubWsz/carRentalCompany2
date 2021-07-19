package pl.kuba.domain.stores;

import pl.kuba.entities.Rent;

public interface RentStore {
    Rent save(Rent rent);
}
