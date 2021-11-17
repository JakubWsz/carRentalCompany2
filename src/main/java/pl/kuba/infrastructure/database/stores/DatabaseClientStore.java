package pl.kuba.infrastructure.database.stores;

import org.springframework.stereotype.Component;
import pl.kuba.domain.stores.ClientStore;
import pl.kuba.entities.Client;
import pl.kuba.infrastructure.persistence.ClientRepository;

@Component
public class DatabaseClientStore implements ClientStore {
    private final ClientRepository clientRepository;

    public DatabaseClientStore(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client findByEmail(String email) {
        return clientRepository.findByEmail(email).get();
    }
}
