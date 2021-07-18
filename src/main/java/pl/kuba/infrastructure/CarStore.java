package pl.kuba.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kuba.entities.Car;

@Repository
public interface CarStore extends JpaRepository<Car, Long> {
}
