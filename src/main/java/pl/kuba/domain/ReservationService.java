package pl.kuba.domain;

import org.springframework.stereotype.Service;
import pl.kuba.entities.Branch;
import pl.kuba.entities.Car;
import pl.kuba.entities.Client;
import pl.kuba.entities.Reservation;
import pl.kuba.infrastructure.CarRepository;
import pl.kuba.infrastructure.ReservationRepository;

import java.text.ParseException;
import java.util.Date;
import java.util.Optional;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final CarRepository carRepository;

    public ReservationService(ReservationRepository reservationRepository, CarRepository carRepository) {
        this.reservationRepository = reservationRepository;
        this.carRepository = carRepository;
    }

    public Reservation makeReservation(Client client, Car car, String rentDate, String returnDate,
                                       Branch rentingBranch, Branch receivingBranch) throws ParseException {
        Optional<Car> optionalCar = getOptionalCar(car);
        Car actualCar;
        if (optionalCar.isPresent())
            actualCar = optionalCar.get();
        else throw new RuntimeException("Selected car doesn't exist");
        Date rentDateAsDate = StringToDateConverter.convertStringToDate(rentDate);
        Date returnDateAsDate = StringToDateConverter.convertStringToDate(returnDate);
        Reservation reservation = new Reservation(getTodayDate(), client, car, rentDateAsDate, returnDateAsDate, rentingBranch,
                receivingBranch, actualCar.getAmountPerDay());
        return reservationRepository.save(reservation);
    }

    private void cancelReservation(long reservationId) throws ParseException {
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

    private Date getTodayDate() throws ParseException {
        String todayAsString = DateFormatter.dateFormatter();
        return StringToDateConverter.convertStringToDate(todayAsString);
    }

    private Optional<Car> getOptionalCar(Car car) {
        return carRepository.findAll().stream()
                .filter(car1 -> car1.getId() == car.getId())
                .findFirst();
    }

}
