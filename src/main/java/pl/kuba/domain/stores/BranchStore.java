package pl.kuba.domain.stores;

import pl.kuba.entities.Branch;

import java.util.List;
import java.util.Optional;

public interface BranchStore {
    List<Branch> findAll();

    Branch save(Branch branch);

    Optional <Branch> findById(long id);

    Optional<Branch> findByAddress(String contactAddress);
}
