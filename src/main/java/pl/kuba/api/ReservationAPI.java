package pl.kuba.api;

import org.springframework.web.bind.annotation.*;
import pl.kuba.api.request.reservation.ConfirmCarReceiptRequest;
import pl.kuba.api.request.reservation.MakeReservationRequest;
import pl.kuba.domain.ReservationService;
import pl.kuba.entities.Reservation;
import pl.kuba.entities.Return;

import java.text.ParseException;

@RestController
@RequestMapping("/reservation")
public class ReservationAPI {
    private final ReservationService reservationService;

    public ReservationAPI(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public Reservation makeReservation(@RequestBody MakeReservationRequest makeReservationRequest) throws ParseException {
        return reservationService.makeReservation(
                makeReservationRequest.getClient(),
                makeReservationRequest.getCar(),
                makeReservationRequest.getRentDate(),
                makeReservationRequest.getReturnDate(),
                makeReservationRequest.getRentingBranch(),
                makeReservationRequest.getReceivingBranch(),
                makeReservationRequest.getRentingWorker(),
                makeReservationRequest.getComment()
        );
    }

    @DeleteMapping
    public void cancelReservation(@RequestParam long reservationId) throws ParseException {
        reservationService.cancelReservation(reservationId);
    }

    @PostMapping
    public Return confirmCarReceipt(@RequestBody ConfirmCarReceiptRequest confirmCarReceiptRequest) {
        return reservationService.confirmCarReceipt(
                confirmCarReceiptRequest.getWorker(),
                confirmCarReceiptRequest.getReturnDate(),
                confirmCarReceiptRequest.getReservation(),
                confirmCarReceiptRequest.getSurcharge(),
                confirmCarReceiptRequest.getComment()
        );
    }
}