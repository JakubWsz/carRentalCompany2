package pl.kuba.domain.services;

import org.springframework.stereotype.Service;
import pl.kuba.api.dto.request.branch.UpdateBranchRequest;
import pl.kuba.api.dto.response.branch.BranchDetailsView;
import pl.kuba.api.dto.response.branch.BranchView;
import pl.kuba.domain.stores.BranchStore;
import pl.kuba.domain.stores.RentalCompanyStore;
import pl.kuba.entities.Branch;
import pl.kuba.entities.RentalCompany;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
        validateRentalCompanyData(rentalCompany.getName(), rentalCompany.getWebsite(), rentalCompany.getContactAddress()
                , rentalCompany.getOwner());
        rentalCompany.setModificationDate(LocalDateTime.now());
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
                .filter(branch -> !branch.isDeleted())
                .filter(branch -> branch.getAddress().equals(address))
                .findFirst();
        if (branchToClose.isPresent()) {
            branchToClose.get().setDeleted(true);
            branchToClose.get().setModificationDate(LocalDateTime.now());
            branchStore.save(branchToClose.get());
            return true;
        } else {
            throw new RuntimeException("Branch doesn't exist");

        }
    }

    public BranchDetailsView getParticularBranch(long id) {
        return map(getBranchById(id));
    }

    public RentalCompany updateRentalCompany(String oldName, String newName, String newWebsiteName,
                                             String newContactAddress, String newOwner) {
        Optional<RentalCompany> optionalRentalCompany = rentalCompanyStore.findByName(oldName);
        if (optionalRentalCompany.isPresent()) {
            RentalCompany rentalCompany = optionalRentalCompany.get();
            rentalCompany.setName(newName);
            rentalCompany.setWebsite(newWebsiteName);
            rentalCompany.setContactAddress(newContactAddress);
            rentalCompany.setOwner(newOwner);
            rentalCompany.setModificationDate(LocalDateTime.now());
            validateRentalCompanyData(rentalCompany.getName(), rentalCompany.getWebsite(),
                    rentalCompany.getContactAddress(), rentalCompany.getOwner());
            return rentalCompanyStore.save(rentalCompany);
        } else
            throw new RuntimeException("This rental company doesn't exist");
    }

    public List<BranchView> getAllBranchViews() {
        return getAllBranches().stream()
                .map(branch -> new BranchView(branch.getId(), branch.getAddress()))
                .collect(Collectors.toList());
    }

    public BranchDetailsView updateBranch(UpdateBranchRequest updateBranch, long id) {
    Branch branch = getBranchById(id);
    updatePartiallyBranch(branch,updateBranch);
    branchStore.save(branch);
    return map(branch);
    }

    private List<Branch> getAllBranches() {
        return branchStore.findAll();
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

    private Branch getBranchById(long id) {
        return this.branchStore.findById(id)
                .orElseThrow(() -> new RuntimeException("This branch doesn't exist"));
    }

    private BranchDetailsView map(Branch branch) {
        return new BranchDetailsView(branch.getId(), branch.getAddress(), branch.getWorkers(),
                branch.getAvailableCars());
    }

    private void updatePartiallyBranch(Branch primaryBranch, UpdateBranchRequest updateBranch){
        if (updateBranch.getAddress() != null){
            primaryBranch.setAddress(updateBranch.getAddress());
        }
        if (updateBranch.getAvailableCars() != null){
            primaryBranch.setAvailableCars(updateBranch.getAvailableCars());
        }
        if (updateBranch.getWorkers() != null){
            primaryBranch.setWorkers(updateBranch.getWorkers());
        }
    }
}
