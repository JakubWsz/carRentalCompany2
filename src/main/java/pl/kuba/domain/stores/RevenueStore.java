package pl.kuba.domain.stores;

import pl.kuba.entities.Revenue;

import java.util.List;

public interface RevenueStore {
    Revenue save(Revenue revenue);

    List<Revenue> findAll();
}
