package pl.kuba.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kuba.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {
    Client findByEmail(String email);
}
