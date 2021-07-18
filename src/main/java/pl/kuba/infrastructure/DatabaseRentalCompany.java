package pl.kuba.infrastructure;

import org.springframework.stereotype.Component;
import pl.kuba.domain.RentalCompanyStore;
import pl.kuba.entities.RentalCompany;

@Component
public class DatabaseRentalCompany implements RentalCompanyStore {
    private final RentalCompanyStore rentalCompanyRepository;

    public DatabaseRentalCompany(RentalCompanyStore rentalCompanyRepository) {
        this.rentalCompanyRepository = rentalCompanyRepository;
    }

    @Override
    public RentalCompany save(RentalCompany rentalCompany) {
        return rentalCompanyRepository.save(rentalCompany);
    }
}
