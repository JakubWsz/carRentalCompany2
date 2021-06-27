package pl.kuba.domain;

import org.springframework.stereotype.Service;
import pl.kuba.entities.BaseEntity;
import pl.kuba.entities.Branch;
import pl.kuba.entities.Worker;
import pl.kuba.infrastructure.BranchRepository;
import pl.kuba.infrastructure.WorkerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BranchService {
    private BranchRepository branchRepository;
    private WorkerRepository workerRepository;
    List<Branch> branches;
    List<Worker> workers;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    public void hireWorker(String firstname, String lastname, boolean manager, Branch branch) {
        Worker worker = new Worker(firstname, lastname, manager, branch);
        branches = findAllBranches();
        branches.stream()
                .filter(branch1 -> branch1.getId() == branch.getId())
                .findFirst()
                .ifPresent(branch1 -> {
                    List<Worker> workers = branch1.getWorkers();
                    workers.add(worker);
                    branch1.setWorkers(workers);
                });
    }

    public void firedWorker(Branch branch, long workerId) {
        branches = findAllBranches();
        workers = findAllWorkers();
        Optional<Worker> firedWorker = workers.stream().filter(worker -> worker.getId() == workerId)
                .findFirst();
        firedWorker.ifPresent(worker -> branches.stream()
                .filter(branch1 -> branch1.getId() == branch.getId())
                .findFirst()
                .ifPresent(branch1 -> {
                    List<Worker> workers = branch1.getWorkers();
                    workers.remove(worker);
                    branch1.setWorkers(workers);
                }));
    }

    private List<Branch> findAllBranches() {
        return branches = branchRepository.findAll();
    }

    private List<Worker> findAllWorkers() {
        return workers = workerRepository.findAll();
    }

}
