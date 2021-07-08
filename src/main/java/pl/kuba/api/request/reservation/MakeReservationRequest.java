package pl.kuba.api.request.reservation;

import lombok.Getter;
import lombok.Setter;
import pl.kuba.entities.Branch;
import pl.kuba.entities.Car;
import pl.kuba.entities.Client;

@Getter
@Setter
public class MakeReservationRequest {
  private final Client client;
  private final Car car;
  private final String rentDate;
  private final String returnDate;
  private final Branch rentingBranch;
  private final Branch receivingBranch;

    public MakeReservationRequest(Client client, Car car, String rentDate, String returnDate,
                                  Branch rentingBranch, Branch receivingBranch) {
        this.client = client;
        this.car = car;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.rentingBranch = rentingBranch;
        this.receivingBranch = receivingBranch;
    }
}
