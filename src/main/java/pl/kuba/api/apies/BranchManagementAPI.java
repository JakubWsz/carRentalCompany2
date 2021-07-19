package pl.kuba.api.apies;

import org.springframework.web.bind.annotation.*;
import pl.kuba.api.request.branch.BranchManagementFireRequest;
import pl.kuba.api.request.branch.BranchManagementHireRequest;
import pl.kuba.domain.servises.BranchManagementService;
import pl.kuba.entities.Worker;

@RestController
@RequestMapping("/branch-management")
public class BranchManagementAPI {
private final BranchManagementService branchManagementService;

    public BranchManagementAPI(BranchManagementService branchManagementService) {
        this.branchManagementService = branchManagementService;
    }

    @PostMapping("/hire-worker")
    public Worker hireWorker(@RequestBody BranchManagementHireRequest branchManagementHireRequest){
       return branchManagementService.hireWorker(
                branchManagementHireRequest.getFirstname(),
                branchManagementHireRequest.getLastname(),
                branchManagementHireRequest.isManager(),
                branchManagementHireRequest.getBranch()
        );
    }

    @DeleteMapping("/fire-worker")
    public void fireWorker(@RequestParam BranchManagementFireRequest branchManagementFireRequest){
        branchManagementService.fireWorker(
                branchManagementFireRequest.getBranch(),
                branchManagementFireRequest.getWorkerId()
        );
    }
}
