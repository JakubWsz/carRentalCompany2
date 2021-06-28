package pl.kuba.domain;

import org.springframework.stereotype.Service;
import pl.kuba.entities.Branch;
import pl.kuba.entities.Car;
import pl.kuba.entities.Reservation;
import pl.kuba.infrastructure.BranchRepository;
import pl.kuba.infrastructure.CarRepository;
import pl.kuba.infrastructure.ReservationRepository;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RentingService {
    private final BranchRepository branchRepository;
    private final ReservationRepository reservationRepository;

    public RentingService(BranchRepository branchRepository, ReservationRepository reservationRepository) {
        this.branchRepository = branchRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<Car> getAvailableCars(String branchLocation, String date) throws ParseException {
        Branch selectedBranch = findSelectedBranch(branchLocation);
        Reservation selectedReservation = findReservationByDate(date);
        List<Car> cars = new ArrayList<>();
        findAllReservations().stream()
                .filter(reservation -> reservation.getRentDate().equals(selectedReservation.getRentDate()))
                .filter(reservation -> reservation.getRentingBranch().equals(selectedBranch))
                .forEach(reservation -> cars.add(reservation.getCar()));
        return cars;
    }

    private Reservation findReservationByDate(String date) throws ParseException {
        Date dateToCheck = StringToDateConverter.convertStringToDate(date);
        List<Reservation> reservations = findAllReservations();
        return reservations.stream()
                .filter(reservation -> reservation.getReservationDate().equals(dateToCheck))
                .findFirst()
                .get();
    }

    private Branch findSelectedBranch(String branchLocation) {
        return findAllBranches().stream()
                .filter(branch -> branch.getAddress().equals(branchLocation))
                .findFirst()
                .get();
    }

    private List<Branch> findAllBranches() {
        return branchRepository.findAll();
    }

    private List<Reservation> findAllReservations() {
        return reservationRepository.findAll();
    }
}
