package pl.kuba.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kuba.entities.Client;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {
    List<Client> findByEmail(String email);
}
