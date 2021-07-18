package pl.kuba.infrastructure;

import org.springframework.stereotype.Component;
import pl.kuba.domain.RentStore;
import pl.kuba.entities.Rent;

@Component
public class DatabaseRentStore implements RentStore {
   private final RentStore rentRepository;

    public DatabaseRentStore(RentStore rentRepository) {
        this.rentRepository = rentRepository;
    }

    @Override
    public Rent save(Rent rent) {
        return rentRepository.save(rent);
    }
}
