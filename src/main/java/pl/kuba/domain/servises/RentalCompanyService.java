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

    public RentalCompany updateRentalCompany(RentalCompanyUpdateRequest rentalCompanyUpdateRequest) {
        Optional<RentalCompany> optionalRentalCompany = rentalCompanyRepository.findByName(rentalCompanyUpdateRequest
                .getOldName());
        if (optionalRentalCompany.isPresent()) {
            RentalCompany rentalCompany = optionalRentalCompany.get();
            rentalCompany.setName(rentalCompanyUpdateRequest.getNewName());
            rentalCompany.setWebsite(rentalCompanyUpdateRequest.getNewWebsiteName());
            rentalCompany.setContactAddress(rentalCompanyUpdateRequest.getNewContactAddress());
            rentalCompany.setOwner(rentalCompanyUpdateRequest.getNewOwner());
            rentalCompany.setModificationDate(LocalDateTime.now());
            return rentalCompanyRepository.save(rentalCompany);
        } else
            throw new RuntimeException("This rental company doesn't exist");
    }

    private void validateRentalCompanyData(String name, String website, String contactAddress, String owner) {
        Pattern pattern = Pattern.compile("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]"
                , Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(website);
        if (!matcher.find()) {
            throw new RuntimeException("URL is invalid");
        }
        if (name == null)
            throw new RuntimeException(("Name is null"));
        if (contactAddress == null)
            throw new RuntimeException(("Contact address is null"));
        if (owner == null)
            throw new RuntimeException(("Owner is null"));
    }
}
