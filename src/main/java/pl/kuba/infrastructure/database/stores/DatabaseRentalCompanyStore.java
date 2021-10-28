package pl.kuba.infrastructure.database.stores;

import org.springframework.stereotype.Component;
import pl.kuba.domain.stores.RentalCompanyStore;
import pl.kuba.entities.RentalCompany;
import pl.kuba.infrastructure.persistence.RentalCompanyRepository;

import java.util.Optional;

@Component
public class DatabaseRentalCompanyStore implements RentalCompanyStore {
    private final RentalCompanyRepository rentalCompanyRepository;

    public DatabaseRentalCompanyStore(RentalCompanyRepository rentalCompanyRepository) {
        this.rentalCompanyRepository = rentalCompanyRepository;
    }

    @Override
    public Optional<RentalCompany> findByName(String name) {
        return rentalCompanyRepository.findByName(name);
    }

    @Override
    public RentalCompany save(RentalCompany rentalCompany) {
        return rentalCompanyRepository.save(rentalCompany);
    }
}
