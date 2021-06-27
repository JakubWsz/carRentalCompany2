package pl.kuba.domain;

import org.springframework.stereotype.Service;
import pl.kuba.entities.Branch;
import pl.kuba.entities.Car;
import pl.kuba.entities.Reservation;
import pl.kuba.entities.Worker;
import pl.kuba.infrastructure.BranchRepository;
import pl.kuba.infrastructure.CarRepository;
import pl.kuba.infrastructure.ReservationRepository;
import pl.kuba.infrastructure.WorkerRepository;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BranchService {
    private final BranchRepository branchRepository;
    private final WorkerRepository workerRepository;
    private final CarRepository carRepository;
    private final ReservationRepository reservationRepository;
    private List<Branch> branches;
    private List<Worker> workers;
    private List<Car> cars;
    private List<Reservation> reservations;

    public BranchService(BranchRepository branchRepository, WorkerRepository workerRepository,
                         CarRepository carRepository, ReservationRepository reservationRepository) {
        this.branchRepository = branchRepository;
        this.workerRepository = workerRepository;
        this.carRepository = carRepository;
        this.reservationRepository = reservationRepository;
    }

    public void hireWorker(String firstname, String lastname, boolean manager, Branch branch) {
        Worker worker = new Worker(firstname, lastname, manager, branch);
        branches = findAllBranches();
        branches.stream()
                .filter(branch1 -> branch1.getId() == branch.getId())
                .findFirst()
                .ifPresent(branch1 -> {
                    List<Worker> workers = branch1.getWorkers();
                    workers.add(worker);
                    branch1.setWorkers(workers);
                });
    }

    public void firedWorker(Branch branch, long workerId) {
        branches = findAllBranches();
        workers = findAllWorkers();
        Optional<Worker> firedWorker = workers.stream().filter(worker -> worker.getId() == workerId)
                .findFirst();
        firedWorker.ifPresent(worker -> branches.stream()
                .filter(branch1 -> branch1.getId() == branch.getId())
                .findFirst()
                .ifPresent(branch1 -> {
                    List<Worker> workers = branch1.getWorkers();
                    workers.remove(worker);
                    branch1.setWorkers(workers);
                }));
    }

    public List<Car> getAvailableCars(String branchLocation, String date) throws ParseException {
        Branch selectedBranch = findSelectedBranch(branchLocation);
        Reservation selectedReservation = findReservationByDate(date);
        return findAllReservations().stream()
                .filter(reservation -> reservation.getRentDate().equals(selectedReservation.getRentDate()))
    }

    private Reservation findReservationByDate(String date) throws ParseException {
        Date dateToCheck = StringToDateConverter.convertStringToDate(date);
        reservations = findAllReservations();
        return reservations.stream()
                .filter(reservation -> reservation.getReservationDate().equals(date))
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
        return branches = branchRepository.findAll();
    }

    private List<Worker> findAllWorkers() {
        return workers = workerRepository.findAll();
    }

    private List<Car> findAllCars() {
        return cars = carRepository.findAll();
    }

    private List<Reservation> findAllReservations() {
        return reservations = reservationRepository.findAll();
    }

}
