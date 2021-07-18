package pl.kuba.infrastructure;

import org.springframework.stereotype.Component;
import pl.kuba.domain.WorkerStore;
import pl.kuba.entities.Worker;

import java.util.List;

@Component
public class DatabaseWorkerStore implements WorkerStore {
   private final WorkerStore workerRepository;

    public DatabaseWorkerStore(WorkerStore workerRepository) {
        this.workerRepository = workerRepository;
    }

    @Override
    public Worker save(Worker worker) {
        return workerRepository.save(worker);
    }

    @Override
    public List<Worker> findAll() {
        return workerRepository.findAll();
    }
}
