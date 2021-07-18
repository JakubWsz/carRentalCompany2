package pl.kuba.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kuba.entities.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation ,Long> {

}
