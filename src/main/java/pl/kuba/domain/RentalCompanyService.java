package pl.kuba.domain;

import org.springframework.stereotype.Service;
import pl.kuba.entities.Branch;
import pl.kuba.entities.RentalCompany;
import pl.kuba.infrastructure.BranchRepository;
import pl.kuba.infrastructure.RentalCompanyRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        validateRentalCompanyData(name, website, contactAddress, owner);
        return rentalCompanyRepository.save(rentalCompany);
    }

    public Branch openNewBranch(String address) {
        Branch branch = new Branch(address);
        branch.setModificationDate(LocalDateTime.now());
        return branchRepository.save(branch);
    }

    public String closeBranch(String address) {
       // List<Branch> branches = branchRepository.findAll();
        Optional<Branch> branchToClose = branchRepository.findByAddress(address);
//                = branches.stream()
//                .filter(branch -> branch.getAddress().equals(address))
//                .findFirst();
        if (branchToClose.isPresent()) {
            branchRepository.delete(branchToClose.get());
            return "Branch is closed";
        } else {
            throw new RuntimeException("Branch doesn't exist");

        }
    }

    public RentalCompany updateRentalCompany(String finByThisName, String newWebsiteName, String newContactAddress,
                                             String newOwner, String newName) {
        Optional<RentalCompany> optionalRentalCompany = rentalCompanyRepository.findByName(finByThisName);
        if (optionalRentalCompany.isPresent()) {
            RentalCompany rentalCompany = optionalRentalCompany.get();
            rentalCompany.setName(newName);
            rentalCompany.setWebsite(newWebsiteName);
            rentalCompany.setContactAddress(newContactAddress);
            rentalCompany.setOwner(newOwner);
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
