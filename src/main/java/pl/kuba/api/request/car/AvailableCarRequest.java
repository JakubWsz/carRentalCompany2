package pl.kuba.api.request.car;

import lombok.Getter;

@Getter
public class AvailableCarRequest {
    private final String branchLocation;
    private final String date;

    public AvailableCarRequest(String branchLocation, String date) {
        this.branchLocation = branchLocation;
        this.date = date;
    }
}
