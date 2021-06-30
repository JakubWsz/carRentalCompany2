package pl.kuba.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kuba.entities.RentalCompany;

@Repository
public interface RentalCompanyRepository extends JpaRepository<RentalCompany,Integer> {
}