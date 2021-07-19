package pl.kuba.infrastructure.database.stores;

import org.springframework.stereotype.Component;
import pl.kuba.domain.stores.WorkerStore;
import pl.kuba.entities.Worker;
import pl.kuba.infrastructure.persistence.WorkerRepository;

import java.util.List;

@Component
public class DatabaseWorkerStore implements WorkerStore {
   private final WorkerRepository workerRepository;

    public DatabaseWorkerStore(WorkerRepository workerRepository) {
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
