package pl.kuba.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kuba.entities.Rent;

@Repository
public interface RentRepository extends JpaRepository<Rent,Integer> {
}
