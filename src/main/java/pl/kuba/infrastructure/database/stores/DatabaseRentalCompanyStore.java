package pl.kuba.infrastructure.database.stores;

import org.springframework.stereotype.Component;
import pl.kuba.domain.stores.RentalCompanyStore;
import pl.kuba.entities.RentalCompany;
import pl.kuba.infrastructure.persistence.RentalCompanyRepository;

@Component
public class DatabaseRentalCompanyStore implements RentalCompanyStore {
    private final RentalCompanyRepository rentalCompanyRepository;

    public DatabaseRentalCompanyStore(RentalCompanyRepository rentalCompanyRepository) {
        this.rentalCompanyRepository = rentalCompanyRepository;
    }

    @Override
    public RentalCompany save(RentalCompany rentalCompany) {
        return rentalCompanyRepository.save(rentalCompany);
    }
}
