package pl.kuba.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kuba.entities.Revenue;

public interface RevenueRepository extends JpaRepository<Revenue,Integer> {
}
