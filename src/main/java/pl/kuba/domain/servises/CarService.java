package pl.kuba.domain.servises;

import org.springframework.stereotype.Service;
import pl.kuba.domain.stores.BranchStore;
import pl.kuba.domain.stores.CarStore;
import pl.kuba.domain.stores.ReservationStore;
import pl.kuba.entities.AvailabilityStatus;
import pl.kuba.entities.Branch;
import pl.kuba.entities.Car;
import pl.kuba.entities.Reservation;
import pl.kuba.infrastructure.datehelpers.StringToDateConverter;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private final CarStore carStore;
    private final BranchStore branchStore;
    private final ReservationStore reservationStore;

    public CarService(CarStore carStore,
                      BranchStore branchRepository,
                      ReservationStore reservationStore) {
        this.carStore = carStore;
        this.branchStore = branchRepository;
        this.reservationStore = reservationStore;
    }

    public void updateCarMileage(long id, int carMileage) {
        Optional<Car> optionalCar = getOptionalCar(id);
        if (optionalCar.isPresent()) {
            optionalCar.get().setCarMileage(carMileage);
            carStore.save(optionalCar.get());
        } else throwExceptionThereIsNoCarWithPassedId();
    }

    public void updateCarAmountPerDay(long id, int carAmountPerDayGoldCoin, int carAmountPerDayPennyCoin) {
        BigDecimal carAmountPerDay =
                new BigDecimal(String.format("%d.%d",carAmountPerDayGoldCoin,carAmountPerDayPennyCoin));
        Optional<Car> optionalCar = getOptionalCar(id);
        if (optionalCar.isPresent()) {
            optionalCar.get().setAmountPerDay(carAmountPerDay);
            carStore.save(optionalCar.get());
        } else throwExceptionThereIsNoCarWithPassedId();
    }

    public String updateAvailabilityStatus(long id, AvailabilityStatus availabilityStatus, String note) {
        StringBuilder carNote = new StringBuilder("");
        Optional<Car> optionalCar = getOptionalCar(id);
        if (optionalCar.isPresent()) {
            optionalCar.get().setAvailabilityStatus(availabilityStatus);
            carNote.append(note);
            carStore.save(optionalCar.get());
        } else throwExceptionThereIsNoCarWithPassedId();
        return carNote.toString();
    }

    public AvailabilityStatus getCarAvailabilityStatusByParticularDate(long carId, String date) throws ParseException {
        AvailabilityStatus availabilityStatus;
        Date particularDate = StringToDateConverter.convertStringToDate(date);
        Optional<Reservation> optionalReservation = getAllReservations().stream()
                .filter(reservation -> reservation.getReservationDate().equals(particularDate))
                .filter(reservation -> reservation.getCar().getId() == carId)
                .findFirst();
        if (optionalReservation.isPresent()) {
            availabilityStatus = optionalReservation.get().getCar().getAvailabilityStatus();
            return availabilityStatus;
        }else throw new RuntimeException("There is no such car");

    }

    public List<Car> getAvailableCars(String branchLocation, String date) throws ParseException {
        Reservation selectedReservation = getReservationByDate(date);
        List<Car> cars = new ArrayList<>();
        getAllReservations().stream()
                .filter(reservation -> reservation.getRentDate().equals(selectedReservation.getRentDate()))
                .filter(reservation -> reservation.getRentingBranch().equals(getSelectedBranch(branchLocation)))
                .forEach(reservation -> cars.add(reservation.getCar()));
        return cars;
    }

    private void throwExceptionThereIsNoCarWithPassedId() {
        throw new RuntimeException(("There is no car with passed id"));
    }

    private Optional<Car> getOptionalCar(long id) {
        return carStore.findById(id);
    }

    private Reservation getReservationByDate(String date) throws ParseException {
        Optional<Reservation> optionalReservation = getOptionalReservationByDate(date);
        Reservation reservation;
        if (optionalReservation.isPresent()) {
            reservation = optionalReservation.get();
        } else throw new RuntimeException("There is no reservation on this date");
        return reservation;
    }

    private Branch getSelectedBranch(String branchLocation) {
        Optional<Branch> optionalBranch = getOptionalBranch(branchLocation);
        Branch branch;
        if (optionalBranch.isPresent()) {
            branch = optionalBranch.get();
        } else throw new RuntimeException("Branch doesn't exist");
        return branch;
    }

    private List<Branch> getAllBranches() {
        return branchStore.findAll();
    }

    private List<Reservation> getAllReservations() {
        return reservationStore.findAll();
    }

    private Optional<Reservation> getOptionalReservationByDate(String date) throws ParseException {
        Date dateToCheck = StringToDateConverter.convertStringToDate(date);
        List<Reservation> reservations = getAllReservations();
        return reservations.stream()
                .filter(reservation -> reservation.getReservationDate().equals(dateToCheck))
                .findFirst();
    }

    private Optional<Branch> getOptionalBranch(String branchLocation) {
        return getAllBranches().stream()
                .filter(branch -> branch.getAddress().equals(branchLocation))
                .findFirst();
    }
}