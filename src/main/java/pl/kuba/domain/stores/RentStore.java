package pl.kuba.domain.stores;

import pl.kuba.entities.Rent;

import java.util.List;

public interface RentStore {
    Rent save(Rent rent);

    List<Rent> findAll();
}
