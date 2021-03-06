package pl.kuba.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kuba.entities.RentalCompany;

import java.util.Optional;

@Repository
public interface RentalCompanyRepository extends JpaRepository<RentalCompany,Long> {
    Optional<RentalCompany> findByName(String name);
}
