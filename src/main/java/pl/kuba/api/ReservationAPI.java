package pl.kuba.api;

import org.springframework.web.bind.annotation.*;
import pl.kuba.api.request.reservation.MakeReservationRequest;
import pl.kuba.domain.ReservationService;
import pl.kuba.entities.Reservation;

import java.text.ParseException;

@RestController
@RequestMapping("/reservation")
public class ReservationAPI {
    private final ReservationService reservationService;

    public ReservationAPI(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/reserve")
    public Reservation makeReservation(@RequestBody MakeReservationRequest makeReservationRequest) throws ParseException {
        return reservationService.makeReservation(
                makeReservationRequest.getClient(),
                makeReservationRequest.getCar(),
                makeReservationRequest.getRentDate(),
                makeReservationRequest.getReturnDate(),
                makeReservationRequest.getRentingBranch(),
                makeReservationRequest.getReceivingBranch()
        );
    }

    @DeleteMapping("/cancel")
    private void cancelReservation(long reservationId) throws ParseException {
        reservationService.cancelReservation(reservationId);
    }
}
