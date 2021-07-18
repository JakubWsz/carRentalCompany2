package pl.kuba.domain;

import org.springframework.stereotype.Service;
import pl.kuba.entities.*;
import pl.kuba.infrastructure.StringToDateConverter;
import pl.kuba.infrastructure.persistence.BranchRepository;
import pl.kuba.infrastructure.persistence.CarRepository;
import pl.kuba.infrastructure.persistence.ReservationRepository;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CarService {
    private final CarRepository carRepository;
    private final BranchRepository branchRepository;
    private final ReservationRepository reservationRepository;
    private final RevenueService revenueService;
    private final CarAvailabilityAsDatesService carAvailabilityAsDatesService;

    public CarService(CarRepository carRepository, BranchRepository branchRepository,
                      ReservationRepository reservationRepository, RevenueService revenueService,
                      CarAvailabilityAsDatesService carAvailabilityAsDatesService) {
        this.carRepository = carRepository;
        this.branchRepository = branchRepository;
        this.reservationRepository = reservationRepository;
        this.revenueService = revenueService;
        this.carAvailabilityAsDatesService = carAvailabilityAsDatesService;
    }


    public void updateCarMileage(long id, int carMileage) {
        Optional<Car> optionalCar = getOptionalCar(id);
        if (optionalCar.isPresent()) {
            optionalCar.get().setCarMileage(carMileage);
        } else throwExceptionThereIsNoCarWithPassedId();
    }

    public void updateCarAmountPerDay(long id, int carAmountPerDayGoldCoin, int carAmountPerDayPennyCoin) {
        BigDecimal carAmountPerDay =
                new BigDecimal(String.format("%d.%d", carAmountPerDayGoldCoin, carAmountPerDayPennyCoin));
        Optional<Car> optionalCar = getOptionalCar(id);
        if (optionalCar.isPresent()) {
            optionalCar.get().setAmountPerDay(carAmountPerDay);
        } else throwExceptionThereIsNoCarWithPassedId();
    }

    public String updateAvailabilityStatus(long id, AvailabilityStatus availabilityStatus, String note) {
        StringBuilder carNote = new StringBuilder("");
        Optional<Car> optionalCar = getOptionalCar(id);
        if (optionalCar.isPresent()) {
            optionalCar.get().setAvailabilityStatus(availabilityStatus);
            carNote.append(note);
        } else throwExceptionThereIsNoCarWithPassedId();
        return carNote.toString();
    }

    public Map<LocalDate,AvailabilityStatus> getCarAvailabilityStatusByParticularDate(long id, String date) {
        Map<LocalDate,AvailabilityStatus> whenCarIsAvailable = new HashMap<>();
        LocalDate particularDate = StringToDateConverter.convertStringToDate(date);

        CarAvailabilityAsDates dates = carAvailabilityAsDatesService.getDatesRangeCarsPotentialAvailability(id);

        for (LocalDate dateRange = dates.getRentDate(); dateRange.isAfter(dates.getReturnDate()); dateRange = dateRange.plusDays(1)) {
            LocalDate finalDateRange = dateRange;
            getAllReservations().stream()
                    .filter(reservation -> reservation.getReservationDate().equals(particularDate))
                    .filter(reservation -> reservation.getCar().getId() == id)
                    .findFirst()
                    .ifPresent(reservation -> whenCarIsAvailable.put(finalDateRange, reservation.getCar().getAvailabilityStatus()));
        }
        return whenCarIsAvailable;
    }

    public List<Car> getAvailableCars(String branchLocation, String date) throws ParseException {
        Reservation selectedReservation = getReservationByDate(date);
        return getAllReservations().stream()
                .filter(reservation -> reservation.getRentDate().equals(selectedReservation.getRentDate()))
                .filter(reservation -> reservation.getRentingBranch().equals(getSelectedBranch(branchLocation)))
                .map(Reservation::getCar)
                .collect(Collectors.toList());
    }

    private void throwExceptionThereIsNoCarWithPassedId() {
        throw new RuntimeException(("There is no car with passed id"));
    }

    private Optional<Car> getOptionalCar(long id) {
        return carRepository.findById(id);
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
        return branchRepository.findAll();
    }

    private List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    private Optional<Reservation> getOptionalReservationByDate(String date) throws ParseException {
        LocalDate dateToCheck = StringToDateConverter.convertStringToDate(date);
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