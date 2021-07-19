package pl.kuba.domain;

import org.springframework.stereotype.Service;
import pl.kuba.entities.Car;
import pl.kuba.entities.CarAvailabilityAsDates;
import pl.kuba.entities.Rent;
import pl.kuba.entities.Return;
import pl.kuba.infrastructure.persistence.CarAvailabilityAsDatesRepository;
import pl.kuba.infrastructure.persistence.RentRepository;
import pl.kuba.infrastructure.persistence.ReturnRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
public class CarAvailabilityAsDatesService {
    private final CarAvailabilityAsDatesRepository carAvailabilityAsDatesRepository;
    private final RentRepository rentRepository;
    private final ReturnRepository returnRepository;

    public CarAvailabilityAsDatesService(CarAvailabilityAsDatesRepository carAvailabilityAsDatesRepository,
                                         RentRepository rentRepository, ReturnRepository returnRepository) {
        this.carAvailabilityAsDatesRepository = carAvailabilityAsDatesRepository;
        this.rentRepository = rentRepository;
        this.returnRepository = returnRepository;
    }

    public CarAvailabilityAsDates getDatesRangeCarsPotentialAvailability(long id) {
        LocalDate rentDate;
        LocalDate returnDate;
        Optional<Rent> optionalRent = getSelectedCarRent(id);
        Optional<Return> optionalReturn = getSelectedCarReturn(id);

        if (optionalRent.isPresent()) {
            rentDate = optionalRent.get().getRentDate();
        }else throw new RuntimeException("There is no such rent date");

        if (optionalReturn.isPresent()) {
            returnDate = optionalReturn.get().getReturnDate();
        }else throw new RuntimeException("There is no such return date");

        return carAvailabilityAsDatesRepository.save(new CarAvailabilityAsDates(rentDate, returnDate));
    }

    private Optional<Rent> getSelectedCarRent(long id) {
        return rentRepository.findAll().stream()
                .filter(rent -> rent.getReservation().getCar().getId() == id)
                .findFirst();
    }

    private Optional<Return> getSelectedCarReturn(long id) {
        return returnRepository.findAll().stream()
                .filter(aReturn -> aReturn.getReservation().getCar().getId() == id)
                .findFirst();
    }
}
