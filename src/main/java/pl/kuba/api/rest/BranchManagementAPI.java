package pl.kuba.api.rest;

import org.springframework.web.bind.annotation.*;
import pl.kuba.api.dto.request.branch.management.BranchManagementFireRequest;
import pl.kuba.api.dto.request.branch.management.BranchManagementHireRequest;
import pl.kuba.domain.services.BranchManagementService;
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
