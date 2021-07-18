package pl.kuba.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kuba.entities.Revenue;

public interface RevenueStore extends JpaRepository<Revenue,Integer> {
}
