package pl.kuba.domain.repository;

import pl.kuba.domain.stores.RentStore;
import pl.kuba.entities.Rent;

import java.util.ArrayList;
import java.util.List;

public class TestRentStore implements RentStore {
    List<Rent> rents = new ArrayList<>();

    @Override
    public Rent save(Rent rent) {
        rents.add(rent);
        return rent;
    }

    @Override
    public List<Rent> findAll() {
        return new ArrayList<>(rents);
    }
}
