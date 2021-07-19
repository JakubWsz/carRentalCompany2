package pl.kuba.domain.stores;

import pl.kuba.entities.Reservation;

import java.util.List;

public interface ReservationStore {
    Reservation save (Reservation reservation);

    List<Reservation> findAll();
}
