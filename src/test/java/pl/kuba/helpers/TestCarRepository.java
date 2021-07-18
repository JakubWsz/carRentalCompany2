package pl.kuba.helpers;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import pl.kuba.entities.AvailabilityStatus;
import pl.kuba.entities.BodyType;
import pl.kuba.entities.Car;
import pl.kuba.infrastructure.persistence.CarRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestCarRepository implements CarRepository {
    final List<Car> cars = new ArrayList<>();
    Car car = new Car("Mercedes", "benc", BodyType.CONVERTIBLE, 1999, "black",
            10000, AvailabilityStatus.AVAILABLE, BigDecimal.valueOf(120L));

    @Override
    public List<Car> findAll() {
        return new ArrayList<>(cars);
    }

    @Override
    public List<Car> findAll(Sort sort) {
        return null;
    }

    @Override
    public List<Car> findAllById(Iterable<Long> iterable) {
        return null;
    }


    @Override
    public <S extends Car> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Car> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public <S extends Car> List<S> saveAllAndFlush(Iterable<S> iterable) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Car> iterable) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> iterable) {

    }


    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Car getOne(Long aLong) {
        return null;
    }

    @Override
    public Car getById(Long aLong) {
        return null;
    }


    @Override
    public <S extends Car> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Car> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public Page<Car> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Car> S save(S s) {
        cars.add(s);
        return s;
    }

    @Override
    public Optional<Car> findById(Long id) {
        return Optional.of(car);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }


    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }


    @Override
    public void delete(Car car) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> iterable) {

    }


    @Override
    public void deleteAll(Iterable<? extends Car> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Car> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Car> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Car> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Car> boolean exists(Example<S> example) {
        return false;
    }
}
