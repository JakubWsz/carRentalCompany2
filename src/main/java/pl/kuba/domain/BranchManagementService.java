package pl.kuba.domain;

import org.springframework.stereotype.Service;
import pl.kuba.entities.Branch;
import pl.kuba.entities.Worker;
import pl.kuba.infrastructure.BranchRepository;
import pl.kuba.infrastructure.WorkerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BranchManagementService {
    private final BranchRepository branchRepository;
    private final WorkerRepository workerRepository;

    public BranchManagementService(BranchRepository branchRepository, WorkerRepository workerRepository) {
        this.branchRepository = branchRepository;
        this.workerRepository = workerRepository;
    }

    public void hireWorker(String firstname, String lastname, boolean manager, Branch branch) {
        Worker worker = new Worker(firstname, lastname, manager, branch);
        findAllBranches().stream()
                .filter(branch1 -> branch1.getId() == branch.getId())
                .findFirst()
                .ifPresent(branch1 -> {
                    List<Worker> workers = branch1.getWorkers();
                    workers.add(worker);
                    branch1.setWorkers(workers);
                });
    }

    public void fireWorker(Branch branch, long workerId) {
        Optional<Worker> firedWorker = findAllWorkers().stream().filter(worker -> worker.getId() == workerId)
                .findFirst();
        firedWorker.ifPresent(worker -> findAllBranches().stream()
                .filter(targetBranch -> targetBranch.getId() == branch.getId())
                .findFirst()
                .ifPresent(targetBranch -> {
                    List<Worker> workersAfterFireWorker = targetBranch.getWorkers();
                    workersAfterFireWorker.remove(worker);
                    targetBranch.setWorkers(workersAfterFireWorker);
                }));
    }

    private List<Branch> findAllBranches() { return branchRepository.findAll(); }

    private List<Worker> findAllWorkers() {
        return workerRepository.findAll();
    }
}
