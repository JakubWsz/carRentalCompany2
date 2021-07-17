package pl.kuba.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kuba.entities.CarAvailabilityAsDates;

@Repository
public interface CarAvailabilityAsDatesRepository extends JpaRepository<CarAvailabilityAsDates, Long> {
}
