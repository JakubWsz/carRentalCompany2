package pl.kuba.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kuba.entities.Branch;

import java.util.Optional;

public interface ClosedBranchRepository extends JpaRepository<Branch,Long> {
    Optional<Branch> findByAddress(String address);
}
