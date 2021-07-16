package pl.kuba.api;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import pl.kuba.infrastructure.BranchRepository;
import pl.kuba.infrastructure.RentalCompanyRepository;

@SpringBootTest
class RentalCompanyAPITest {
    private final BranchRepository branchRepository;
    private final RentalCompanyRepository rentalCompanyRepository;
    private final RentalCompanyAPI rentalCompanyAPI;

    RentalCompanyAPITest(BranchRepository branchRepository,
                         RentalCompanyRepository rentalCompanyRepository, RentalCompanyAPI rentalCompanyAPI) {
        this.branchRepository = branchRepository;
        this.rentalCompanyRepository = rentalCompanyRepository;
        this.rentalCompanyAPI = rentalCompanyAPI;
    }

    @BeforeEach
    void setUp() {

    }
}