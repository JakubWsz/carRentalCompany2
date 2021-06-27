package pl.kuba.domain;

import org.springframework.stereotype.Service;
import pl.kuba.entities.Branch;
import pl.kuba.entities.RentalCompany;
import pl.kuba.infrastructure.BranchRepository;
import pl.kuba.infrastructure.RentalCompanyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RentalCompanyService {
    private final RentalCompanyRepository rentalCompanyRepository;
    private final BranchRepository branchRepository;

    public RentalCompanyService(RentalCompanyRepository rentalCompanyRepository, BranchRepository branchRepository) {
        this.rentalCompanyRepository = rentalCompanyRepository;
        this.branchRepository = branchRepository;
    }

    public RentalCompany configureRentalCompany(String name, String website, String contactAddress, String owner) {
        RentalCompany rentalCompany = new RentalCompany(name, website, contactAddress, owner);
        return rentalCompanyRepository.save(rentalCompany);
    }

    public Branch openNewBranch(String address) {
        Branch branch = new Branch(address);
        return branchRepository.save(branch);
    }

    public void closeBranch(String address) {
        List<Branch> branches = branchRepository.findAll();
        Optional<Branch> branchToClose = branches.stream()
                .filter(branch -> branch.getAddress().equals(address))
                .findFirst();
        if (branchToClose.isPresent()){
            branchRepository.delete(branchToClose.get());
        } else {
            throw new RuntimeException("Branch doesn't exist");
        }
    }
}
