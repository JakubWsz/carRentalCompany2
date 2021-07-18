package pl.kuba.domain;

import pl.kuba.entities.Revenue;

import java.util.List;

public interface RevenueStore {
    Revenue save(Revenue revenue);

    List<Revenue> findAll();
}
