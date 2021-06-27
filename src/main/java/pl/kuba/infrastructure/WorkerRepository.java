package pl.kuba.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kuba.entities.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Integer> {
}
