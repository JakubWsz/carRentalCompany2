package pl.kuba.domain.servises;

import org.springframework.stereotype.Service;
import pl.kuba.api.request.rentalcompany.RentalCompanyUpdateRequest;
import pl.kuba.domain.stores.BranchStore;
import pl.kuba.domain.stores.RentalCompanyStore;
import pl.kuba.entities.Branch;
import pl.kuba.entities.RentalCompany;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RentalCompanyService {
    private final RentalCompanyStore rentalCompanyStore;
    private final BranchStore branchStore;

    public RentalCompanyService(RentalCompanyStore rentalCompanyRepository, BranchStore branchRepository) {
        this.rentalCompanyStore = rentalCompanyRepository;
        this.branchStore = branchRepository;
    }

    public RentalCompany configureRentalCompany(String name, String website, String contactAddress, String owner) {
        RentalCompany rentalCompany = new RentalCompany(name, website, contactAddress, owner);
        validateRentalCompanyData(rentalCompany.getName(),rentalCompany.getWebsite(),rentalCompany.getContactAddress(),
                rentalCompany.getOwner());
        return rentalCompanyStore.save(rentalCompany);
    }

    public Branch openNewBranch(String address) {
        Branch branch = new Branch(address);
        branch.setModificationDate(LocalDateTime.now());
        return branchStore.save(branch);
    }

    public boolean closeBranch(String address) {
        List<Branch> branches = branchStore.findAll();
        Optional<Branch> branchToClose = branches.stream()
                .filter(branch -> branch.getAddress().equals(address))
                .findFirst();
        if (branchToClose.isPresent()) {
            branchToClose.get().setDeleted(true);
            branchStore.save(branchToClose.get());
            return true;
        } else {
            throw new RuntimeException("Branch doesn't exist");

        }
    }

    public RentalCompany updateRentalCompany(String oldName,String newName,String newWebsiteName,
                                             String newContactAddress,String newOwner) {
        Optional<RentalCompany> optionalRentalCompany = rentalCompanyStore.findByName(oldName);
        if (optionalRentalCompany.isPresent()) {
            RentalCompany rentalCompany = optionalRentalCompany.get();
            rentalCompany.setName(newName);
            rentalCompany.setWebsite(newWebsiteName);
            rentalCompany.setContactAddress(newContactAddress);
            rentalCompany.setOwner(newOwner);
            rentalCompany.setModificationDate(LocalDateTime.now());
            validateRentalCompanyData(rentalCompany.getName(),rentalCompany.getWebsite(),
                    rentalCompany.getContactAddress(),rentalCompany.getOwner());
            return rentalCompanyStore.save(rentalCompany);
        } else
            throw new RuntimeException("This rental company doesn't exist");
    }

    private void validateRentalCompanyData(String name, String website, String contactAddress, String owner) {
        if (website == null)
            throw new RuntimeException(("Website is null"));
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
