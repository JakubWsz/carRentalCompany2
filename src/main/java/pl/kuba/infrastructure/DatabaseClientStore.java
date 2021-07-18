package pl.kuba.infrastructure;

import org.springframework.stereotype.Component;
import pl.kuba.domain.ClientStore;
import pl.kuba.entities.Client;

@Component
public class DatabaseClientStore implements ClientStore {
    private final ClientStore clientRepository;

    public DatabaseClientStore(ClientStore clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client findByEmail(String email) {
        return findByEmail(email);
    }
}
