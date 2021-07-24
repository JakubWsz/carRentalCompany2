package pl.kuba.infrastructure.database.stores;

import org.springframework.stereotype.Component;
import pl.kuba.domain.stores.ReservationStore;
import pl.kuba.entities.Reservation;
import pl.kuba.infrastructure.persistence.ReservationRepository;

import java.util.List;
import java.util.Optional;

@Component
public class DatabaseReservationStore implements ReservationStore {
   private final ReservationRepository reservationRepository;

    public DatabaseReservationStore(ReservationRepository reservationRepository) {
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

    @Override
    public Optional<Reservation> findById(long id) {
        return reservationRepository.findById(id);
    }
}
