package pl.kuba.infrastructure;

import org.springframework.stereotype.Component;
import pl.kuba.domain.ReservationStore;
import pl.kuba.entities.Reservation;

import java.util.List;

@Component
public class DatabaseReservationStore implements ReservationStore {
   private final ReservationStore reservationRepository;

    public DatabaseReservationStore(ReservationStore reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }
}
