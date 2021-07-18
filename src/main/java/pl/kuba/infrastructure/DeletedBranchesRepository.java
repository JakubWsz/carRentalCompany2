package pl.kuba.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kuba.entities.Branch;

public interface DeletedBranchesRepository extends JpaRepository<Branch,Long> {
}
