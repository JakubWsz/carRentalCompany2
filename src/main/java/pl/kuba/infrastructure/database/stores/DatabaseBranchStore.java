package pl.kuba.infrastructure.database.stores;

import org.springframework.stereotype.Component;
import pl.kuba.domain.stores.BranchStore;
import pl.kuba.entities.Branch;
import pl.kuba.infrastructure.persistence.BranchRepository;

import java.util.List;
import java.util.Optional;

@Component
public class DatabaseBranchStore implements BranchStore {
    private final BranchRepository branchRepository;

    public DatabaseBranchStore(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Override
    public List<Branch> findAll() {
        return branchRepository.findAll();
    }

    @Override
    public Branch save(Branch branch) {
        return branchRepository.save(branch);
    }

    @Override
    public Optional<Branch> findByAddress(String contactAddress) {
        return branchRepository.findByAddress(contactAddress);
    }

    @Override
    public Optional<Branch> findById(long id) {
        return branchRepository.findById(id);
    }
}
