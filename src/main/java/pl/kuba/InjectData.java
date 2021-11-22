package pl.kuba;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.kuba.domain.services.ClientService;
import pl.kuba.domain.services.RentalCompanyService;
import pl.kuba.domain.services.ReservationService;
import pl.kuba.domain.services.RevenueService;
import pl.kuba.entities.*;
import pl.kuba.infrastructure.persistence.CarRepository;
import pl.kuba.infrastructure.persistence.ClientRepository;
import pl.kuba.infrastructure.persistence.WorkerRepository;

import java.math.BigDecimal;

@ConditionalOnProperty(name = "init-mock-data", havingValue = "true")
@Component
public class InjectData implements ApplicationRunner {
    private final RevenueService revenueService;
    private final RentalCompanyService rentalCompanyService;
    private final CarRepository carRepository;
    private final ReservationService reservationService;
    private final ClientService clientService;
    private final ClientRepository clientRepository;
    private final WorkerRepository workerRepository;

    public InjectData(RevenueService revenueService, RentalCompanyService rentalCompanyService,
                      CarRepository carRepository, ReservationService reservationService, ClientService clientService,
                      ClientRepository clientRepository, WorkerRepository workerRepository) {
        this.revenueService = revenueService;
        this.rentalCompanyService = rentalCompanyService;
        this.carRepository = carRepository;
        this.reservationService = reservationService;
        this.clientService = clientService;
        this.clientRepository = clientRepository;
        this.workerRepository = workerRepository;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        Branch branch = rentalCompanyService.openNewBranch("branch");
        revenueService.buyCar("xyz", "bndf", "123123123", BodyType.HATCHBACK, 1980,
                "white", 123132, AvailabilityStatus.AVAILABLE, BigDecimal.valueOf(120),
                BigDecimal.valueOf(70000));
        revenueService.buyCar("asfassfasf", "dsdgsdas", "123123123", BodyType.HATCHBACK, 1980,
                "white", 123132, AvailabilityStatus.AVAILABLE, BigDecimal.valueOf(120),
                BigDecimal.valueOf(70000));
        revenueService.buyCar("xgsdgsgyz", "dsdgsdgas", "123123123", BodyType.HATCHBACK, 1980,
                "white", 123132, AvailabilityStatus.AVAILABLE, BigDecimal.valueOf(120),
                BigDecimal.valueOf(70000));
        revenueService.buyCar("xsgsegsegyz", "dasdgsdgs", "123123123", BodyType.HATCHBACK, 1980,
                "white", 123132, AvailabilityStatus.AVAILABLE, BigDecimal.valueOf(120),
                BigDecimal.valueOf(70000));

        branch.setAvailableCars(carRepository.findAll());
        clientService.addNewClient("adsf", "asf", "asd@adw.pl", "pokj");
        Client client = clientRepository.findByEmail("asd@adw.pl").get();
        Worker worker = workerRepository.save(new Worker("asd", "af", false, branch));
        reservationService.makeReservation(client, carRepository.findAll().get(0), "15-08-1999",
                "15-08-1999", branch, branch, worker, 150, worker, "asd",
                "asd");
    }
}
