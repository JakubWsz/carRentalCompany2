package pl.kuba.api.dto.request.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.kuba.entities.Branch;
import pl.kuba.entities.Car;
import pl.kuba.entities.Client;
import pl.kuba.entities.Worker;

@Getter
@AllArgsConstructor
public class MakeReservationRequest {
    private final Client client;
    private final Car car;
    private final String rentDate;
    private final String returnDate;
    private final Branch rentingBranch;
    private final Branch receivingBranch;
    private final Worker rentingWorker;
    private final String comment;
}
