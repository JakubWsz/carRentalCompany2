package pl.kuba.api.request.car;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AvailableCarRequest {
    private final String branchLocation;
    private final String date;
}
