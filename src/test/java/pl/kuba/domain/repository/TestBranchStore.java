package pl.kuba.domain.repository;

import pl.kuba.domain.stores.BranchStore;
import pl.kuba.entities.Branch;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestBranchStore implements BranchStore {
    private final List<Branch> branches = new ArrayList<>();

    @Override
    public List<Branch> findAll() {
        return new ArrayList<>(branches);
    }

    @Override
    public Branch save(Branch branch) {
        branches.add(branch);
        return branch;
    }

    @Override
    public Optional<Branch> findById(long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Branch> findByAddress(String contactAddress) {
        return branches.stream()
                .filter(branch -> branch.getAddress().equals(contactAddress))
                .findFirst();
    }
}
