package pl.kuba.domain.services;

import org.springframework.stereotype.Service;
import pl.kuba.domain.stores.CarStore;
import pl.kuba.domain.stores.ReservationStore;
import pl.kuba.domain.stores.ReturnStore;
import pl.kuba.entities.*;
import pl.kuba.infrastructure.datehelpers.DateFormatter;
import pl.kuba.infrastructure.datehelpers.StringToDateConverter;
import pl.kuba.infrastructure.persistence.RentRepository;
import pl.kuba.infrastructure.persistence.ReturnRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ReservationService {
    private final ReservationStore reservationStore;
    private final CarStore carStore;
    private final RentRepository rentStore;
    private final ReturnStore returnStore;

    public ReservationService(ReservationStore reservationRepository, CarStore carRepository,
                              RentRepository rentRepository, ReturnStore returnStore) {
        this.reservationStore = reservationRepository;
        this.carStore = carRepository;
        this.rentStore = rentRepository;
        this.returnStore = returnStore;
    }

    public Reservation makeReservation(Client client, Car car, String rentDate, String returnDate,
                                       Branch rentingBranch, Branch receivingBranch, Worker rentingWorker,
                                       int surcharge, Worker receiptingWorker, String commentRent,
                                       String commentReceipt) {
        Optional<Car> optionalCar = getOptionalCar(car);
        Car actualCar;
        if (optionalCar.isPresent())
            actualCar = optionalCar.get();
        else throw new RuntimeException("Selected car doesn't exist");

        LocalDate rentDateAsDate = StringToDateConverter.convertStringToDate(rentDate);
        LocalDate returnDateAsDate = StringToDateConverter.convertStringToDate(returnDate);
        Reservation reservation = new Reservation(getTodayDate(), client, car, rentDateAsDate, returnDateAsDate,
                rentingBranch, receivingBranch, actualCar.getAmountPerDay());

        rentCar(rentingWorker, rentDateAsDate, reservation, commentRent);
        confirmCarReceipt(receiptingWorker, returnDateAsDate, reservation, surcharge, commentReceipt);
        return reservationStore.save(reservation);
    }


    public void cancelReservation(long reservationId) {
        Optional<Reservation> optionalReservation = findOptionalReservationById(reservationId);
        if (optionalReservation.isPresent()) {
            optionalReservation.get().setDeleted(true);
            reservationStore.save(optionalReservation.get());
        } else {
            throwExceptionThereIsNoReservationWithPassedId();
        }
    }

    private Optional<Reservation> findOptionalReservationById(long reservationId) {
        return reservationStore.findAll().stream()
                .filter(reservation -> reservation.getId() == reservationId)
                .findFirst();
    }

    private void throwExceptionThereIsNoReservationWithPassedId() {
        throw new RuntimeException(("There is no reservation with passed id"));
    }

    private LocalDate getTodayDate() {
        String todayAsString = DateFormatter.dateFormatter();
        return StringToDateConverter.convertStringToDate(todayAsString);
    }

    private Optional<Car> getOptionalCar(Car car) {
        return carStore.findAll().stream()
                .filter(car1 -> car1.getId() == car.getId())
                .findFirst();
    }

    private Return confirmCarReceipt(Worker worker, LocalDate returnDate, Reservation reservation,
                                     int surcharge, String comment) {
        Return returnVariable = new Return();
        returnVariable.setWorker(worker);
        returnVariable.setReturnDate(returnDate);
        returnVariable.setReservation(reservation);
        returnVariable.setSurcharge(surcharge);
        returnVariable.setComment(comment);
        return returnStore.save(returnVariable);
    }

    private Rent rentCar(Worker rentingWorker, LocalDate rentDateAsDate, Reservation reservation, String commentRent) {
        Rent rentVariable = new Rent();
        rentVariable.setWorker(rentingWorker);
        rentVariable.setRentDate(rentDateAsDate);
        rentVariable.setReservation(reservation);
        rentVariable.setComment(commentRent);
        return rentStore.save(rentVariable);
    }
}
