package pl.kuba.api.request.branch_management;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.kuba.entities.Branch;

@Getter
@AllArgsConstructor
public class BranchManagementFireRequest {
    private final Branch branch;
    private final long workerId;
}
