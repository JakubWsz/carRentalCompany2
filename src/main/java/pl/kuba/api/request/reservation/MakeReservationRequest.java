package pl.kuba.api.request.reservation;

import lombok.Getter;
import pl.kuba.entities.Branch;
import pl.kuba.entities.Car;
import pl.kuba.entities.Client;
import pl.kuba.entities.Worker;

@Getter
public class MakeReservationRequest {
    private final Client client;
    private final Car car;
    private final String rentDate;
    private final String returnDate;
    private final Branch rentingBranch;
    private final Branch receivingBranch;
    private final Worker rentingWorker;
    private final String comment;

    public MakeReservationRequest(Client client, Car car, String rentDate, String returnDate, Branch rentingBranch,
                                  Branch receivingBranch, Worker rentingWorker, String comment) {
        this.client = client;
        this.car = car;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.rentingBranch = rentingBranch;
        this.receivingBranch = receivingBranch;
        this.rentingWorker = rentingWorker;
        this.comment = comment;
    }
}
