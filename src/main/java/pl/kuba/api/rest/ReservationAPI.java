package pl.kuba.api.rest;

import org.springframework.web.bind.annotation.*;
import pl.kuba.api.dto.request.reservation.ConfirmCarReceiptRequest;
import pl.kuba.api.dto.request.reservation.MakeReservationRequest;
import pl.kuba.domain.services.ReservationService;
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

    @PostMapping("/reserve")
    public Reservation makeReservation(@RequestBody MakeReservationRequest makeReservationRequest) {
        return reservationService.makeReservation(
                makeReservationRequest.getClient(),
                makeReservationRequest.getCar(),
                makeReservationRequest.getRentDate(),
                makeReservationRequest.getReturnDate(),
                makeReservationRequest.getRentingBranch(),
                makeReservationRequest.getReceivingBranch(),
                makeReservationRequest.getRentingWorker(),
                makeReservationRequest.getSurcharge(),
                makeReservationRequest.getReceiptingWorker(),
                makeReservationRequest.getCommentRent(),
                makeReservationRequest.getCommentReceipt()
        );
    }

    @DeleteMapping("/cancel")
    public void cancelReservation(@RequestParam long reservationId) {
        reservationService.cancelReservation(reservationId);
    }
}