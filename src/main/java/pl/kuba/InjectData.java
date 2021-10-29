package pl.kuba;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pl.kuba.domain.services.BranchManagementService;
import pl.kuba.domain.services.CarService;
import pl.kuba.domain.services.RentalCompanyService;

@Component
public class InjectData implements ApplicationRunner {
    private final BranchManagementService branchManagementService;
    private final RentalCompanyService rentalCompanyService;
    private final CarService carService;

    public InjectData(BranchManagementService branchManagementService, RentalCompanyService rentalCompanyService,
                      CarService carService) {
        this.branchManagementService = branchManagementService;
        this.rentalCompanyService = rentalCompanyService;
        this.carService = carService;
    }

    @Override
    public void run(ApplicationArguments args) {
    rentalCompanyService.openNewBranch("some branch");
carService.
    }
}
