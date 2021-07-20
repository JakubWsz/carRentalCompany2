package pl.kuba.domain.repository;

import pl.kuba.domain.stores.ReservationStore;
import pl.kuba.entities.Reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestReservationStore implements ReservationStore {
    List<Reservation> reservations = new ArrayList<>();

    @Override
    public Reservation save(Reservation reservation) {
         reservations.add(reservation);
         return reservation;
    }

    @Override
    public List<Reservation> findAll() {
        return new ArrayList<>(reservations);
    }

    @Override
    public Optional<Reservation> findById(long id) {
        return reservations.stream()
                .filter(reservation -> reservation.getId() == id)
                .findFirst();
    }
}
