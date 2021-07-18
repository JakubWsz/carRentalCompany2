package pl.kuba.domain;

import pl.kuba.entities.Branch;

import java.util.List;

public interface BranchStore {
    List<Branch> findAll();

    Branch save(Branch branch);

    void delete(Branch branch);
}
