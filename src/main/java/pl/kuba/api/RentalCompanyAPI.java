package pl.kuba.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kuba.domain.RentalCompanyService;
import pl.kuba.entities.Branch;
import pl.kuba.entities.RentalCompany;

@RestController
@RequestMapping("/RentalCompany")
public class RentalCompanyAPI {
    private final RentalCompanyService rentalCompanyService;

    public RentalCompanyAPI(RentalCompanyService rentalCompanyService) {
        this.rentalCompanyService = rentalCompanyService;
    }

    @PostMapping("/rentalCompany/config")
    public RentalCompany configureRentalCompany(String name, String website, String contactAddress, String owner) {
        return rentalCompanyService.configureRentalCompany(name, website, contactAddress, owner);
    }

    @PostMapping("/rentalCompany/openNewBranch")
    public Branch openNewBranch(String address) {
        return rentalCompanyService.openNewBranch(address);
    }

    @DeleteMapping("/rentalCompany/deleteBranch")
    public void closeBranch(String address) {
        rentalCompanyService.closeBranch(address);
    }
}
