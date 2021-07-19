package pl.kuba.infrastructure.database.stores;

import org.springframework.stereotype.Component;
import pl.kuba.domain.stores.RevenueStore;
import pl.kuba.entities.Revenue;
import pl.kuba.infrastructure.persistence.RevenueRepository;

import java.util.List;

@Component
public class DatabaseRevenueStore implements RevenueStore {
  private final RevenueRepository revenueRepository;

    public DatabaseRevenueStore(RevenueRepository revenueRepository) {
        this.revenueRepository = revenueRepository;
    }

    @Override
    public Revenue save(Revenue revenue) {
        return revenueRepository.save(revenue);
    }

    @Override
    public List<Revenue> findAll() {
        return revenueRepository.findAll();
    }
}
