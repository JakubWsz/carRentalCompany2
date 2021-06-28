package pl.kuba.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kuba.entities.Worker;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Integer> {
}
