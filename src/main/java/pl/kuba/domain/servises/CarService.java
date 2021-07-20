package pl.kuba.domain.servises;

import org.springframework.stereotype.Service;
import pl.kuba.domain.stores.BranchStore;
import pl.kuba.domain.stores.CarStore;
import pl.kuba.domain.stores.ReservationStore;
import pl.kuba.entities.AvailabilityStatus;
import pl.kuba.entities.Branch;
import pl.kuba.entities.Car;
import pl.kuba.entities.Reservation;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService {
    private final ReservationStore reservationStore;
    private final CarStore carStore;
    private final BranchStore branchStore;


    public CarService(CarStore carStore, BranchStore branchStore, ReservationStore reservationStore) {
        this.carStore = carStore;
        this.branchStore = branchStore;
        this.reservationStore = reservationStore;
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

    public List<Car> getAvailableCars(String branchLocation) {
        return reservationStore.findAll().stream()
                .filter(reservation -> reservation.getRentingBranch().equals(getSelectedBranch(branchLocation)))
                .map(Reservation::getCar)
                .filter(car -> car.getAvailabilityStatus() == AvailabilityStatus.AVAILABLE)
                .collect(Collectors.toList());
    }

    private void throwExceptionThereIsNoCarWithPassedId() {
        throw new RuntimeException(("There is no car with passed id"));
    }

    private Optional<Car> getOptionalCar(long id) {
        return carStore.findById(id);
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

    private Optional<Branch> getOptionalBranch(String branchLocation) {
        return getAllBranches().stream()
                .filter(branch -> branch.getAddress().equals(branchLocation))
                .findFirst();
    }
}