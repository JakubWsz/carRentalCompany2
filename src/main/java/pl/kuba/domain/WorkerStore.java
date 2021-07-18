package pl.kuba.domain;

import pl.kuba.entities.Worker;

import java.util.List;

public interface WorkerStore {
    Worker save(Worker worker);

    List<Worker> findAll();
}
