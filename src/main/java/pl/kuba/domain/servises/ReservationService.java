package pl.kuba.domain.servises;

import org.springframework.stereotype.Service;
import pl.kuba.domain.stores.CarStore;
import pl.kuba.domain.stores.ReservationStore;
import pl.kuba.entities.*;
import pl.kuba.infrastructure.datehelpers.DateFormatter;
import pl.kuba.infrastructure.datehelpers.StringToDateConverter;
import pl.kuba.infrastructure.persistence.RentRepository;
import pl.kuba.infrastructure.persistence.ReturnRepository;

import java.text.ParseException;
import java.util.Date;
import java.util.Optional;

@Service
public class ReservationService {
    private final ReservationStore reservationStore;
    private final CarStore carStore;
    private final RentRepository rentStore;
    private final ReturnRepository returnStore;

    public ReservationService(ReservationStore reservationRepository, CarStore carRepository,
                              RentRepository rentRepository, ReturnRepository returnRepository) {
        this.reservationStore = reservationRepository;
        this.carStore = carRepository;
        this.rentStore = rentRepository;
        this.returnStore = returnRepository;
    }

    public Reservation makeReservation(Client client, Car car, String rentDate, String returnDate,
                                       Branch rentingBranch, Branch receivingBranch, Worker rentingWorker,
                                       String comment) throws ParseException {
        Optional<Car> optionalCar = getOptionalCar(car);
        Car actualCar;
        if (optionalCar.isPresent())
            actualCar = optionalCar.get();
        else throw new RuntimeException("Selected car doesn't exist");
        Date rentDateAsDate = StringToDateConverter.convertStringToDate(rentDate);
        Date returnDateAsDate = StringToDateConverter.convertStringToDate(returnDate);
        Reservation reservation = new Reservation(getTodayDate(), client, car, rentDateAsDate, returnDateAsDate, rentingBranch,
                receivingBranch, actualCar.getAmountPerDay());
        Rent rent = rentCreator(rentingWorker, rentDateAsDate, reservation, comment);
        rentStore.save(rent);
        return reservationStore.save(reservation);
    }

    public Return confirmCarReceipt(Worker worker, Date returnDate, Reservation reservation,
                                    int surcharge, String comment) {
        Return returnVariable = new Return();
        returnVariable.setWorker(worker);
        returnVariable.setReturnDate(returnDate);
        returnVariable.setReservation(reservation);
        returnVariable.setSurcharge(surcharge);
        returnVariable.setComment(comment);
        return returnStore.save(returnVariable);
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

    private Date getTodayDate() throws ParseException {
        String todayAsString = DateFormatter.dateFormatter();
        return StringToDateConverter.convertStringToDate(todayAsString);
    }

    private Optional<Car> getOptionalCar(Car car) {
        return carStore.findAll().stream()
                .filter(car1 -> car1.getId() == car.getId())
                .findFirst();
    }

    private Rent rentCreator(Worker worker, Date rentDate, Reservation reservation, String comment) {
        Rent rent = new Rent();
        rent.setWorker(worker);
        rent.setReservation(reservation);
        rent.setRentDate(rentDate);
        rent.setComment(comment);
        return rent;
    }
}
