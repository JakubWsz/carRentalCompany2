package pl.kuba.domain.servises;

import org.springframework.stereotype.Service;
import pl.kuba.domain.stores.BranchStore;
import pl.kuba.entities.Branch;
import pl.kuba.entities.RentalCompany;
import pl.kuba.infrastructure.persistence.RentalCompanyRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RentalCompanyService {
    private final RentalCompanyRepository rentalCompanyStore;
    private final BranchStore branchStore;

    public RentalCompanyService(RentalCompanyRepository rentalCompanyRepository, BranchStore branchRepository) {
        this.rentalCompanyStore = rentalCompanyRepository;
        this.branchStore = branchRepository;
    }

    public RentalCompany configureRentalCompany(String name, String website, String contactAddress, String owner) {
        RentalCompany rentalCompany = new RentalCompany(name, website, contactAddress, owner);
        return rentalCompanyStore.save(rentalCompany);
    }

    public Branch openNewBranch(String address) {
        Branch branch = new Branch(address);
        branch.setModificationDate(LocalDateTime.now());
        return branchStore.save(branch);
    }

    public void closeBranch(String address) {
        List<Branch> branches = branchStore.findAll();
        Optional<Branch> branchToClose = branches.stream()
                .filter(branch -> branch.getAddress().equals(address))
                .findFirst();
        if (branchToClose.isPresent()) {
            branchToClose.get().setDeleted(true);
            branchStore.save(branchToClose.get());
        } else {
            throw new RuntimeException("Branch doesn't exist");
        }
    }
}
