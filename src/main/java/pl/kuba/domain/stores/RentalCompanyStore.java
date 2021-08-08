package pl.kuba.domain.stores;

import pl.kuba.entities.RentalCompany;

import java.util.Optional;

public interface RentalCompanyStore {
    Optional<RentalCompany> findByName(String name);

    RentalCompany save(RentalCompany rentalCompany);

    RentalCompany patchBranchAddress(long id, String address);
}
