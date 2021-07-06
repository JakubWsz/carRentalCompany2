package pl.kuba.api.request;

import pl.kuba.entities.Branch;

public class BranchManagementRequest {
    private final String firstname;
    private final String lastname;
    private final boolean manager;
    private final Branch branch;

    public BranchManagementRequest(String firstname, String lastname, boolean manager, Branch branch) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.manager = manager;
        this.branch = branch;
    }
}
