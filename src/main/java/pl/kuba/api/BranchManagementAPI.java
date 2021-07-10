package pl.kuba.api;

import org.springframework.web.bind.annotation.*;
import pl.kuba.api.request.branch_management.BranchManagementFireRequest;
import pl.kuba.api.request.branch_management.BranchManagementHireRequest;
import pl.kuba.domain.BranchManagementService;
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
