package pl.kuba.infrastructure;

import pl.kuba.domain.BranchStore;
import pl.kuba.entities.Branch;

import java.util.List;

public class DatabaseBranchStore implements BranchStore {
    private final BranchStore branchRepository;
    private final DeletedBranchesRepository deletedBranchesRepository;

    public DatabaseBranchStore(BranchStore branchRepository, DeletedBranchesRepository deletedBranchesRepository) {
        this.branchRepository = branchRepository;
        this.deletedBranchesRepository = deletedBranchesRepository;
    }

    @Override
    public List<Branch> findAll() {
        return branchRepository.findAll();
    }

    @Override
    public Branch save(Branch branch) {
        return branchRepository.save(new Branch());
    }

    @Override
    public void delete(Branch branch) {
        deletedBranchesRepository.save(new Branch());
        branchRepository.delete(branch);
    }
}
