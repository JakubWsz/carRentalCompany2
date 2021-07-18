package pl.kuba.infrastructure;

import org.springframework.stereotype.Component;
import pl.kuba.domain.BranchStore;
import pl.kuba.entities.Branch;

import java.util.List;

@Component
public class DatabaseBranchStore implements BranchStore {
    private final BranchStore branchRepository;

    public DatabaseBranchStore(BranchStore branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Override
    public List<Branch> findAll() {
        return branchRepository.findAll();
    }

    @Override
    public Branch save(Branch branch) {
        return branchRepository.save(new Branch());
    }
}
