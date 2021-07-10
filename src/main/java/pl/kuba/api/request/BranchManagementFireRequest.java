package pl.kuba.api.request;

import lombok.Getter;
import lombok.Setter;
import pl.kuba.entities.Branch;

@Getter
@Setter
public class BranchManagementFireRequest {
    private final Branch branch;
    private final long workerId;

    public BranchManagementFireRequest(Branch branch, long workerId) {
        this.branch = branch;
        this.workerId = workerId;
    }
}
