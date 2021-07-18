package pl.kuba.domain;

import org.springframework.stereotype.Service;
import pl.kuba.entities.Branch;
import pl.kuba.entities.Worker;

import java.util.List;
import java.util.Optional;

@Service
public class BranchManagementService {
    private final BranchStore branchStore;
    private final WorkerStore workerStore;

    public BranchManagementService(BranchStore branchRepository, WorkerStore workerRepository) {
        this.branchStore = branchRepository;
        this.workerStore = workerRepository;
    }

    public Worker hireWorker(String firstname, String lastname, boolean manager, Branch branch) {
        Worker worker = new Worker(firstname, lastname, manager, branch);
        findAllBranches().stream()
                .filter(branch1 -> branch1.getId() == branch.getId())
                .findFirst()
                .ifPresent(branch1 -> {
                    List<Worker> workers = branch1.getWorkers();
                    workers.add(worker);
                    branch1.setWorkers(workers);
                });
        return workerStore.save(worker);
    }

    public void fireWorker(Branch branch, long workerId) {
        Optional<Worker> firedWorker = findAllWorkers().stream()
                .filter(worker -> worker.getId() == workerId)
                .findFirst();
        firedWorker.ifPresent(worker -> findAllBranches().stream()
                .filter(targetBranch -> targetBranch.getId() == branch.getId())
                .findFirst()
                .ifPresent(targetBranch -> {
                    List<Worker> workersAfterFireWorker = targetBranch.getWorkers();
                    workersAfterFireWorker.remove(worker);
                    targetBranch.setWorkers(workersAfterFireWorker);
                }));
        if (firedWorker.isPresent()) {
            firedWorker.get().setDeleted(true);
            workerStore.save(firedWorker.get());
        } else {
            throw new RuntimeException("Worker doesn't exist");
        }
    }

    private List<Branch> findAllBranches() {
        return branchStore.findAll();
    }

    private List<Worker> findAllWorkers() {
        return workerStore.findAll();
    }
}
