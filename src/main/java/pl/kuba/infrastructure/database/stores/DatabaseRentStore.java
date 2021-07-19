package pl.kuba.infrastructure.database.stores;

import org.springframework.stereotype.Component;
import pl.kuba.domain.stores.RentStore;
import pl.kuba.entities.Rent;
import pl.kuba.infrastructure.persistence.RentRepository;

import java.util.List;

@Component
public class DatabaseRentStore implements RentStore {
   private final RentRepository rentRepository;

    public DatabaseRentStore(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
    }

    @Override
    public Rent save(Rent rent) {
        return rentRepository.save(rent);
    }

    @Override
    public List<Rent> findAll() {
        return rentRepository.findAll();
    }
}
