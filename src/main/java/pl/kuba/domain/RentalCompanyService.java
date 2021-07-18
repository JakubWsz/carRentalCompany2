package pl.kuba.domain;

import org.springframework.stereotype.Service;
import pl.kuba.entities.Branch;
import pl.kuba.entities.RentalCompany;
import pl.kuba.infrastructure.BranchStore;
import pl.kuba.infrastructure.DeletedBranchesRepository;
import pl.kuba.infrastructure.RentalCompanyRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RentalCompanyService {
    private final RentalCompanyRepository rentalCompanyRepository;
    private final BranchStore branchStore;
    private final DeletedBranchesRepository deletedBranchesRepository;

    public RentalCompanyService(RentalCompanyRepository rentalCompanyRepository, BranchStore branchRepository,
                                DeletedBranchesRepository deletedBranchesRepository) {
        this.rentalCompanyRepository = rentalCompanyRepository;
        this.branchStore = branchRepository;
        this.deletedBranchesRepository = deletedBranchesRepository;
    }

    public RentalCompany configureRentalCompany(String name, String website, String contactAddress, String owner) {
        RentalCompany rentalCompany = new RentalCompany(name, website, contactAddress, owner);
        return rentalCompanyRepository.save(rentalCompany);
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
            deletedBranchesRepository.save(branchToClose.get());
            branchStore.delete(branchToClose.get());
        } else {
            throw new RuntimeException("Branch doesn't exist");
        }
    }
}
