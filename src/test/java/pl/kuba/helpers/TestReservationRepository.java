package pl.kuba.helpers;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import pl.kuba.entities.*;
import pl.kuba.infrastructure.StringToDateConverter;
import pl.kuba.infrastructure.persistence.ReservationRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestReservationRepository implements ReservationRepository {

    final List<Reservation> reservations = new ArrayList<>();
    Client client = new Client("firstname","lastname","someemail@gmail.com","someAddress");
    Car car = new Car("Mercedes", "benc", BodyType.CONVERTIBLE, 1999, "black",
            10000, AvailabilityStatus.AVAILABLE, BigDecimal.valueOf(120L));
    Branch rentingBranch = new Branch("someAddress2");
    Branch receivingBranch = new Branch("someAddress3");
    Reservation reservation = new Reservation(StringToDateConverter.convertStringToDate("18-07-2021")
            ,client,car,StringToDateConverter.convertStringToDate("18-08-2021"),
            StringToDateConverter.convertStringToDate("22-08-2021" ),
            rentingBranch,receivingBranch,BigDecimal.valueOf(120));
    @Override
    public List<Reservation> findAll() {
        return null;
    }

    @Override
    public List<Reservation> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Reservation> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Reservation> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Reservation reservation) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> iterable) {

    }

    @Override
    public void deleteAll(Iterable<? extends Reservation> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Reservation> S save(S s) {
         reservations.add(s);
        return s;
    }

    @Override
    public <S extends Reservation> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Reservation> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Reservation> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public <S extends Reservation> List<S> saveAllAndFlush(Iterable<S> iterable) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Reservation> iterable) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Reservation getOne(Long aLong) {
        return null;
    }

    @Override
    public Reservation getById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Reservation> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Reservation> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Reservation> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Reservation> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Reservation> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Reservation> boolean exists(Example<S> example) {
        return false;
    }
}
