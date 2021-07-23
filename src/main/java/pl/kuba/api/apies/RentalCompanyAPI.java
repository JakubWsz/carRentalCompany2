package pl.kuba.api.apies;


import org.springframework.web.bind.annotation.*;
import pl.kuba.api.request.rentalcompany.RentalCompanyConfigRequest;
import pl.kuba.api.request.rentalcompany.RentalCompanyUpdateRequest;
import pl.kuba.domain.servises.RentalCompanyService;
import pl.kuba.entities.Branch;
import pl.kuba.entities.RentalCompany;

import java.util.List;

@RestController
@RequestMapping("/rental-company")
public class RentalCompanyAPI {
    private final RentalCompanyService rentalCompanyService;

    public RentalCompanyAPI(RentalCompanyService rentalCompanyService) {
        this.rentalCompanyService = rentalCompanyService;
    }

    @PostMapping("/config")
    public RentalCompany configureRentalCompany(@RequestBody RentalCompanyConfigRequest rentalCompanyConfigRequest) {
        return rentalCompanyService.configureRentalCompany(
                rentalCompanyConfigRequest.getName(),
                rentalCompanyConfigRequest.getWebsite(),
                rentalCompanyConfigRequest.getContactAddress(),
                rentalCompanyConfigRequest.getOwner()
        );
    }

    @GetMapping("get-all-branches")
    public List<Branch> getAllBranch(){
        return rentalCompanyService.getAllBranches();
    }

    @PostMapping("/open-new-branch")
    public Branch openNewBranch(@RequestBody String address) {
        return rentalCompanyService.openNewBranch(address);
    }

    @DeleteMapping("/delete-branch")
    public void closeBranch(@RequestParam String address) {
        rentalCompanyService.closeBranch(address);
    }

    @PutMapping("/update-company")
    public void updateRentalCompany(@RequestBody RentalCompanyUpdateRequest rentalCompanyUpdateRequest) {
        rentalCompanyService.updateRentalCompany(
                rentalCompanyUpdateRequest.getOldName(),
                rentalCompanyUpdateRequest.getNewName(),
                rentalCompanyUpdateRequest.getNewWebsiteName(),
                rentalCompanyUpdateRequest.getNewContactAddress(),
                rentalCompanyUpdateRequest.getNewOwner());
    }
}
