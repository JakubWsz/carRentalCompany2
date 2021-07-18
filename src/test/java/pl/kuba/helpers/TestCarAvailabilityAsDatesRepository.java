package pl.kuba.helpers;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import pl.kuba.entities.CarAvailabilityAsDates;
import pl.kuba.infrastructure.StringToDateConverter;
import pl.kuba.infrastructure.persistence.CarAvailabilityAsDatesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestCarAvailabilityAsDatesRepository implements CarAvailabilityAsDatesRepository {
    List<CarAvailabilityAsDates> carAvailability = new ArrayList<>();
    CarAvailabilityAsDates carAvailabilityAsDates = new CarAvailabilityAsDates(
            StringToDateConverter.convertStringToDate("18-08-2021"),
            StringToDateConverter.convertStringToDate("22-08-2021" ));

    @Override
    public List<CarAvailabilityAsDates> findAll() {
        return null;
    }

    @Override
    public List<CarAvailabilityAsDates> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<CarAvailabilityAsDates> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<CarAvailabilityAsDates> findAllById(Iterable<Long> iterable) {
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
    public void delete(CarAvailabilityAsDates carAvailabilityAsDates) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> iterable) {

    }

    @Override
    public void deleteAll(Iterable<? extends CarAvailabilityAsDates> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends CarAvailabilityAsDates> S save(S s) {
        carAvailability.add(s);
        return s;
    }

    @Override
    public <S extends CarAvailabilityAsDates> List<S> saveAll(Iterable<S> iterable) {
        return (List<S>) iterable;
    }

    @Override
    public Optional<CarAvailabilityAsDates> findById(Long aLong) {
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
    public <S extends CarAvailabilityAsDates> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public <S extends CarAvailabilityAsDates> List<S> saveAllAndFlush(Iterable<S> iterable) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<CarAvailabilityAsDates> iterable) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public CarAvailabilityAsDates getOne(Long aLong) {
        return null;
    }

    @Override
    public CarAvailabilityAsDates getById(Long aLong) {
        return null;
    }

    @Override
    public <S extends CarAvailabilityAsDates> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends CarAvailabilityAsDates> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends CarAvailabilityAsDates> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends CarAvailabilityAsDates> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends CarAvailabilityAsDates> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends CarAvailabilityAsDates> boolean exists(Example<S> example) {
        return false;
    }
}
