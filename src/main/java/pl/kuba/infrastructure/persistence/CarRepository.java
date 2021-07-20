package pl.kuba.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kuba.entities.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
