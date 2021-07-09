package pl.kuba.api.request.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.kuba.entities.Reservation;
import pl.kuba.entities.Worker;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ConfirmCarReceiptRequest {
   private final Worker worker;
   private final Date returnDate;
   private final Reservation reservation;
   private final int surcharge;
   private final String comment;
}
