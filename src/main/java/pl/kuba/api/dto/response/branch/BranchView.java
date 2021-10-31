package pl.kuba.api.dto.response.branch;

public class BranchView {
    private long id;
    private String address;

    public BranchView(long id, String address) {
        this.id = id;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public long getId() {
        return id;
    }
}
