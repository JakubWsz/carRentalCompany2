package pl.kuba.domain;

import org.springframework.stereotype.Service;
import pl.kuba.entities.Client;
import pl.kuba.infrastructure.ClientStore;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {
    private final ClientStore clientStore;

    public ClientService(ClientStore clientRepository) {
        this.clientStore = clientRepository;
    }

    public void addNewClient(Client client) {
       if (isEmailExists(client)) {
           clientStore.save(client);
        } else throw new RuntimeException("Account with this email already exist");
    }

    private boolean isEmailExists(Client client) {
        List<Client> clients = new ArrayList<>(clientStore.findByEmail(client.getEmail()));
       return clients.stream()
                .anyMatch(optionalClient -> optionalClient.getEmail().equals(client.getEmail()));
    }
}