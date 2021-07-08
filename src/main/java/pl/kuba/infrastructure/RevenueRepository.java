package pl.kuba.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kuba.entities.Revenue;

public interface RevenueRepository extends JpaRepository<Revenue,Integer> {
}
