package pl.kuba.domain;

import org.springframework.stereotype.Service;
import pl.kuba.entities.*;
import pl.kuba.infrastructure.DateFormatter;
import pl.kuba.infrastructure.StringToDateConverter;
import pl.kuba.infrastructure.persistence.CarRepository;
import pl.kuba.infrastructure.persistence.RentRepository;
import pl.kuba.infrastructure.persistence.ReservationRepository;
import pl.kuba.infrastructure.persistence.ReturnRepository;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final CarRepository carRepository;
    private final RentRepository rentRepository;
    private final ReturnRepository returnRepository;

    public ReservationService(ReservationRepository reservationRepository, CarRepository carRepository,
                              RentRepository rentRepository, ReturnRepository returnRepository) {
        this.reservationRepository = reservationRepository;
        this.carRepository = carRepository;
        this.rentRepository = rentRepository;
        this.returnRepository = returnRepository;
    }

    public Reservation makeReservation(Client client, Car car, String rentDate, String returnDate,
                                       Branch rentingBranch, Branch receivingBranch, Worker rentingWorker,
                                       String comment) throws ParseException {
        Optional<Car> optionalCar = getOptionalCar(car);
        Car actualCar;
        if (optionalCar.isPresent())
            actualCar = optionalCar.get();
        else throw new RuntimeException("Selected car doesn't exist");
        LocalDate rentDateAsDate = StringToDateConverter.convertStringToDate(rentDate);
        LocalDate returnDateAsDate = StringToDateConverter.convertStringToDate(returnDate);
        Reservation reservation = new Reservation(getTodayDate(), client, car, rentDateAsDate, returnDateAsDate, rentingBranch,
                receivingBranch, actualCar.getAmountPerDay());
        Rent rent = rentCreator(rentingWorker, rentDateAsDate, reservation, comment);
        rentRepository.save(rent);
        return reservationRepository.save(reservation);
    }

    public Return confirmCarReceipt(Worker worker, LocalDate returnDate, Reservation reservation,
                                  int surcharge, String comment) {
        Return returnVariable = new Return();
        returnVariable.setWorker(worker);
        returnVariable.setReturnDate(returnDate);
        returnVariable.setReservation(reservation);
        returnVariable.setSurcharge(surcharge);
        returnVariable.setComment(comment);
       return returnRepository.save(returnVariable);
    }

    public void cancelReservation(long reservationId) throws ParseException {
        Optional<Reservation> optionalReservation = findOptionalReservationById(reservationId);
        Reservation reservation;
        if (optionalReservation.isPresent()) {
            reservation = optionalReservation.get();
            reservationRepository.delete(reservation);
        } else throwExceptionThereIsNoReservationWithPassedId();
    }

    private Optional<Reservation> findOptionalReservationById(long reservationId) {
        return reservationRepository.findAll().stream()
                .filter(reservation -> reservation.getId() == reservationId)
                .findFirst();
    }

    private void throwExceptionThereIsNoReservationWithPassedId() {
        throw new RuntimeException(("There is no reservation with passed id"));
    }

    private LocalDate getTodayDate() throws ParseException {
        String todayAsString = DateFormatter.dateFormatter();
        return StringToDateConverter.convertStringToDate(todayAsString);
    }

    private Optional<Car> getOptionalCar(Car car) {
        return carRepository.findAll().stream()
                .filter(car1 -> car1.getId() == car.getId())
                .findFirst();
    }

    private Rent rentCreator(Worker worker, LocalDate rentDate, Reservation reservation, String comment) {
        Rent rent = new Rent();
        rent.setWorker(worker);
        rent.setReservation(reservation);
        rent.setRentDate(rentDate);
        rent.setComment(comment);
        return rent;
    }
}
