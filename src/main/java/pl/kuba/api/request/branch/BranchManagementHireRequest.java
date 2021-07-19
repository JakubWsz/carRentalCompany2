package pl.kuba.api.request.branch;

import lombok.Getter;
import lombok.Setter;
import pl.kuba.entities.Branch;

@Getter
@Setter
public class BranchManagementHireRequest {
    private final String firstname;
    private final String lastname;
    private final boolean manager;
    private final Branch branch;

    public BranchManagementHireRequest(String firstname, String lastname, boolean manager, Branch branch) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.manager = manager;
        this.branch = branch;
    }
}
