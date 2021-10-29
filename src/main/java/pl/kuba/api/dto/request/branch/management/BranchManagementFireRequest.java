package pl.kuba.api.dto.request.branch.management;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.kuba.entities.Branch;

@Getter
@AllArgsConstructor
public class BranchManagementFireRequest {
    private final Branch branch;
    private final long workerId;
}
