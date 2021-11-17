package pl.kuba.domain.services;

import org.springframework.stereotype.Service;
import pl.kuba.domain.stores.CarAvailabilityAsDatesStore;
import pl.kuba.domain.stores.RentStore;
import pl.kuba.domain.stores.ReservationStore;
import pl.kuba.domain.stores.ReturnStore;
import pl.kuba.entities.AvailabilityStatus;
import pl.kuba.entities.CarAvailabilityAsDates;
import pl.kuba.entities.Rent;
import pl.kuba.entities.Return;
import pl.kuba.infrastructure.datehelpers.StringToDateConverter;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CarAvailabilityAsDatesService {
    private final RentStore rentStore;
    private final ReturnStore returnStore;
    private final ReservationStore reservationStore;

    public CarAvailabilityAsDatesService(RentStore rentStore, ReturnStore returnStore, ReservationStore reservationStore) {
        this.rentStore = rentStore;
        this.returnStore = returnStore;
        this.reservationStore = reservationStore;
    }

    public Map<LocalDate, AvailabilityStatus> getCarAvailabilityStatusByParticularDate(long id, String date) {
        Map<LocalDate, AvailabilityStatus> whenCarIsAvailable = new HashMap<>();
        LocalDate particularDate = StringToDateConverter.convertStringToDate(date);

        Optional<CarAvailabilityAsDates> possibleDates = getDatesRangeCarsPotentialAvailability(id);
        if (possibleDates.isEmpty())
            return whenCarIsAvailable;
        CarAvailabilityAsDates dates = possibleDates.get();

        for (LocalDate dateRange = dates.getRentDate(); dateRange.isAfter(dates.getReturnDate()); dateRange = dateRange.plusDays(1)) {
            LocalDate finalDateRange = dateRange;
            reservationStore.findAll().stream()
                    .filter(reservation -> reservation.getReservationDate().equals(particularDate))
                    .filter(reservation -> reservation.getCar().getId() == id)
                    .findFirst()
                    .ifPresent(reservation -> whenCarIsAvailable.put(finalDateRange, reservation.getCar().getAvailabilityStatus()));
        }
        return whenCarIsAvailable;
    }

    private Optional<CarAvailabilityAsDates> getDatesRangeCarsPotentialAvailability(long id) {
        LocalDate rentDate;
        LocalDate returnDate;
        Optional<Rent> optionalRent = getSelectedCarRent(id);
        Optional<Return> optionalReturn = getSelectedCarReturn(id);

        if (optionalRent.isPresent()) {
            rentDate = optionalRent.get().getRentDate();
        } else return Optional.empty();

        if (optionalReturn.isPresent()) {
            returnDate = optionalReturn.get().getReturnDate();
        } else return Optional.empty();

        return Optional.of(new CarAvailabilityAsDates(rentDate, returnDate));
    }

    private Optional<Rent> getSelectedCarRent(long id) {
        return rentStore.findAll().stream()
                .filter(rent -> rent.getReservation().getCar().getId() == id)
                .findFirst();
    }

    private Optional<Return> getSelectedCarReturn(long id) {
        return returnStore.findAll().stream()
                .filter(aReturn -> aReturn.getReservation().getCar().getId() == id)
                .findFirst();
    }
}
