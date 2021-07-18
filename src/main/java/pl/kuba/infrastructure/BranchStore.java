package pl.kuba.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kuba.entities.Branch;

@Repository
public interface BranchStore extends JpaRepository<Branch, Integer> {
}
