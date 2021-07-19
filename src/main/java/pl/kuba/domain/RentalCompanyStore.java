package pl.kuba.domain;

import pl.kuba.entities.RentalCompany;

public interface RentalCompanyStore {

    RentalCompany save(RentalCompany rentalCompany);
}
