package pl.kuba.domain.repository;

import pl.kuba.domain.stores.RentalCompanyStore;
import pl.kuba.entities.RentalCompany;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestRentalCompanyStore implements RentalCompanyStore {
    private final List<RentalCompany> rentalCompanies = new ArrayList<>();

    @Override
    public Optional<RentalCompany> findByName(String name) {
        return rentalCompanies.stream()
                .filter(rentalCompany -> rentalCompany.getName().equals(name))
                .findFirst();
    }

    @Override
    public RentalCompany save(RentalCompany rentalCompany) {
        rentalCompanies.add(rentalCompany);
        return rentalCompany;
    }
}
