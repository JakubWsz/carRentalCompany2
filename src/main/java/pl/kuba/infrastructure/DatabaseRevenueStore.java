package pl.kuba.infrastructure;

import org.springframework.stereotype.Component;
import pl.kuba.entities.Revenue;

import java.util.List;

@Component
public class DatabaseRevenueStore implements pl.kuba.domain.RevenueStore {
  private final RevenueStore revenueRepository;

    public DatabaseRevenueStore(RevenueStore revenueRepository) {
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
