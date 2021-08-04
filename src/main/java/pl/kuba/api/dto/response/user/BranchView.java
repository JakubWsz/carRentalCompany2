package pl.kuba.api.dto.response.user;

public class BranchView {
    private String address;

    public BranchView(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
