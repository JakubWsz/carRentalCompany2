package pl.kuba.api.request.reservation;

import lombok.Getter;
import pl.kuba.entities.Reservation;
import pl.kuba.entities.Worker;

import java.util.Date;

@Getter
public class ConfirmCarReceiptRequest {
   private final Worker worker;
   private final Date returnDate;
   private final Reservation reservation;
   private final int surcharge;
   private final String comment;

    public ConfirmCarReceiptRequest(Worker worker, Date returnDate, Reservation reservation, int surcharge, String comment) {
        this.worker = worker;
        this.returnDate = returnDate;
        this.reservation = reservation;
        this.surcharge = surcharge;
        this.comment = comment;
    }
}
