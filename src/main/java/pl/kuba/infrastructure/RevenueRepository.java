package pl.kuba.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kuba.entities.Revenue;

import java.math.BigDecimal;

public interface RevenueRepository extends JpaRepository<Revenue,Integer> {
//    BigDecimal subtractAmount();
}
