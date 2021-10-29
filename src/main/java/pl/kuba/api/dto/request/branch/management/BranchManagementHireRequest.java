package pl.kuba.api.dto.request.branch.management;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.kuba.entities.Branch;

@Getter
@AllArgsConstructor
public class BranchManagementHireRequest {
    private final String firstname;
    private final String lastname;
    private final boolean manager;
    private final Branch branch;
}
