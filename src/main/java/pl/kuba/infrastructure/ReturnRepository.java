package pl.kuba.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kuba.entities.Return;

@Repository
public interface ReturnRepository extends JpaRepository<Return, Integer> {
}
