package pl.kuba.domain.stores;

import pl.kuba.entities.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationStore {
    Reservation save (Reservation reservation);

    List<Reservation> findAll();

    Optional<Reservation> findById(long id);
}
