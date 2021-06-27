package pl.kuba.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kuba.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation ,Integer> {

}
